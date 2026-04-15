package dao;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {
    private Connection connection;

    public UtilisateurDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Authentification
    public boolean authenticate(String email, String password) {
        // À faire avec PasswordUtils, pour l'instant simple test
        return findByEmail(email) != null;
    }

    public void updateLastConnection(int id) {
        String sql = "UPDATE utilisateurs SET derniere_connexion = NOW() WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Recherches
    public Utilisateur findByEmail(String email) {
        String sql = "SELECT * FROM utilisateurs WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapResultSetToUtilisateur(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Utilisateur findById(int id) {
        String sql = "SELECT * FROM utilisateurs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapResultSetToUtilisateur(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Utilisateur> findAll() {
        List<Utilisateur> list = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(mapResultSetToUtilisateur(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Utilisateur> findByRole(String role) {
        List<Utilisateur> list = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs WHERE role = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, role);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) list.add(mapResultSetToUtilisateur(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // CRUD
    public boolean save(Utilisateur u) {
        String sql = "INSERT INTO utilisateurs (nom, email, mot_de_passe, salt, role, statut) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getMotDePasse());
            stmt.setString(4, u.getSalt());
            stmt.setString(5, u.getRole());
            stmt.setString(6, u.getStatut() != null ? u.getStatut() : "ACTIF");
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) u.setId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean updatePassword(int userId, String newHashedPassword, String newSalt) {
        String sql = "UPDATE utilisateurs SET mot_de_passe = ?, salt = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newHashedPassword);
            stmt.setString(2, newSalt);
            stmt.setInt(3, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean activate(int id) {
        String sql = "UPDATE utilisateurs SET statut = 'ACTIF' WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean deactivate(int id) {
        String sql = "UPDATE utilisateurs SET statut = 'INACTIF' WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private Utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
        String role = rs.getString("role");
        Utilisateur u;
        switch (role) {
            case "ENSEIGNANT": u = new Enseignant(); break;
            case "ETUDIANT":   u = new Etudiant(); break;
            case "ADMIN":      u = new Administrateur(); break;
            case "GESTIONNAIRE": u = new Gestionnaire(); break;
            default: u = null;
        }
        if (u == null) return null;
        u.setId(rs.getInt("id"));
        u.setNom(rs.getString("nom"));
        u.setEmail(rs.getString("email"));
        u.setMotDePasse(rs.getString("mot_de_passe"));
        u.setSalt(rs.getString("salt"));
        u.setRole(role);
        u.setStatut(rs.getString("statut"));
        u.setDateCreation(rs.getTimestamp("date_creation"));
        u.setDerniereConnexion(rs.getTimestamp("derniere_connexion"));
        return u;
    }
}