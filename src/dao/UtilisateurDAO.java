package dao;

import model.Utilisateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {
    
    private Connection connection;  // ✅ Déclaration de la connection
    
    public UtilisateurDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();  // ✅ Initialisation
        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion à la base de données: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // ==================== AUTHENTIFICATION ====================
    
    public boolean authenticate(String email, String password) {
        // À implémenter avec PasswordUtils
        return false;
    }
    
    public void updateLastConnection(int id) {
        String sql = "UPDATE utilisateurs SET derniere_connexion = NOW() WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // ==================== RECHERCHE ====================
    
    public Utilisateur findByEmail(String email) {
        String sql = "SELECT * FROM utilisateurs WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUtilisateur(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Utilisateur findById(int id) {
        String sql = "SELECT * FROM utilisateurs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUtilisateur(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Utilisateur> findAll() {
        List<Utilisateur> list = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs ORDER BY nom";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToUtilisateur(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Utilisateur> findByRole(String role) {
        List<Utilisateur> list = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs WHERE role = ? ORDER BY nom";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, role);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToUtilisateur(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // ==================== CRUD ====================
    
    public boolean save(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateurs (nom, email, mot_de_passe, salt, role, statut) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getEmail());
            stmt.setString(3, utilisateur.getMotDePasse());
            stmt.setString(4, utilisateur.getSalt());
            stmt.setString(5, utilisateur.getRole());
            stmt.setString(6, utilisateur.getStatut() != null ? utilisateur.getStatut() : "ACTIF");
            
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    utilisateur.setId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updatePassword(int userId, String newHashedPassword, String newSalt) {
        String sql = "UPDATE utilisateurs SET mot_de_passe = ?, salt = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newHashedPassword);
            stmt.setString(2, newSalt);
            stmt.setInt(3, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM utilisateurs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // ==================== GESTION DU STATUT ====================
    
    public boolean activate(int id) throws SQLException {
        String sql = "UPDATE utilisateurs SET statut = 'ACTIF' WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {  // ✅ Utilisation de this.connection
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean deactivate(int id) throws SQLException {
        String sql = "UPDATE utilisateurs SET statut = 'INACTIF' WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {  // ✅ Correction
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean suspendre(int id) throws SQLException {
        String sql = "UPDATE utilisateurs SET statut = 'SUSPENDU' WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {  // ✅ Correction
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    // ==================== MÉTHODE UTILITAIRE ====================
    
    private Utilisateur mapResultSetToUtilisateur(ResultSet rs) throws SQLException {
        // À implémenter selon votre hiérarchie de classes
        // Pour l'instant, retourne un Utilisateur de base
        Utilisateur utilisateur = new Utilisateur() {

			@Override
			public String getRole() {
				// TODO Auto-generated method stub
				return null;
			}
            // Classe anonyme - à remplacer par la vraie instanciation selon le rôle
        };
        utilisateur.setId(rs.getInt("id"));
        utilisateur.setNom(rs.getString("nom"));
        utilisateur.setEmail(rs.getString("email"));
        utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
        utilisateur.setSalt(rs.getString("salt"));
        utilisateur.setRole(rs.getString("role"));
        utilisateur.setStatut(rs.getString("statut"));
        utilisateur.setDateCreation(rs.getTimestamp("date_creation"));
        utilisateur.setDerniereConnexion(rs.getTimestamp("derniere_connexion"));
        return utilisateur;
    }
}