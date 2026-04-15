package dao;

import model.Salle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleDAO {
    private Connection connection;

    public SalleDAO() {
        try { this.connection = DatabaseConnection.getConnection(); }
        catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Salle> findAll() {
        List<Salle> list = new ArrayList<>();
        String sql = "SELECT * FROM salles";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Salle findById(int id) {
        String sql = "SELECT * FROM salles WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return map(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean ajouterSalle(Salle salle) {
        String sql = "INSERT INTO salles (code, nom, batiment, etage, capacite, type, equipements, statut) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, salle.getCode());
            stmt.setString(2, salle.getNom());
            stmt.setString(3, salle.getBatiment());
            stmt.setInt(4, salle.getEtage());
            stmt.setInt(5, salle.getCapacite());
            stmt.setString(6, salle.getType());
            stmt.setString(7, salle.getEquipements());
            stmt.setString(8, salle.getStatut());
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) salle.setId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List<Salle> findDisponibles() {
        List<Salle> list = new ArrayList<>();
        String sql = "SELECT * FROM salles WHERE statut = 'DISPONIBLE'";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Salle> rechercher(Integer capaciteMin, String type, String batiment) {
        // Simplifié pour l'exemple
        return findAll();
    }

    public boolean verifierDisponibilite(int salleId, String date, String heureDebut, String heureFin) {
        // À implémenter avec table reservations
        return true;
    }

    public boolean updateStatut(int id, String statut) {
        String sql = "UPDATE salles SET statut = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, statut);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private Salle map(ResultSet rs) throws SQLException {
        Salle s = new Salle();
        s.setId(rs.getInt("id"));
        s.setCode(rs.getString("code"));
        s.setNom(rs.getString("nom"));
        s.setBatiment(rs.getString("batiment"));
        s.setEtage(rs.getInt("etage"));
        s.setCapacite(rs.getInt("capacite"));
        s.setType(rs.getString("type"));
        s.setEquipements(rs.getString("equipements"));
        s.setStatut(rs.getString("statut"));
        return s;
    }
}