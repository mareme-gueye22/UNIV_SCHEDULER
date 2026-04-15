package dao;

import model.Cours;
import model.Enseignant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class CoursDAO {
    private Connection connection;
    private EnseignantDAO enseignantDAO;

    public CoursDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
        this.enseignantDAO = new EnseignantDAO();
    }

    public List<Cours> findAll() {
        List<Cours> list = new ArrayList<>();
        String sql = "SELECT * FROM cours";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Cours> findByEnseignant(int enseignantId) {
        List<Cours> list = new ArrayList<>();
        String sql = "SELECT * FROM cours WHERE enseignant_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, enseignantId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Cours> findByNiveau(String niveau) {
        List<Cours> list = new ArrayList<>();
        String sql = "SELECT * FROM cours WHERE niveau = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, niveau);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean ajouterCours(Cours cours) {
        String sql = "INSERT INTO cours (code, intitule, description, credits, heures, niveau, semestre, enseignant_id) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cours.getCode());
            stmt.setString(2, cours.getIntitule());
            stmt.setString(3, cours.getDescription());
            stmt.setInt(4, cours.getCredits());
            stmt.setInt(5, cours.getHeures());
            stmt.setString(6, cours.getNiveau());
            stmt.setString(7, cours.getSemestre());
            stmt.setObject(8, cours.getEnseignant() != null ? cours.getEnseignant().getId() : null, Types.INTEGER);
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) cours.setId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM cours WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean verifierConflit(Cours nouveauCours) {
        // Implémentation simplifiée (toujours faux)
        return false;
    }

    private Cours map(ResultSet rs) throws SQLException {
        Cours c = new Cours();
        c.setId(rs.getInt("id"));
        c.setCode(rs.getString("code"));
        c.setIntitule(rs.getString("intitule"));
        c.setDescription(rs.getString("description"));
        c.setCredits(rs.getInt("credits"));
        c.setHeures(rs.getInt("heures"));
        c.setNiveau(rs.getString("niveau"));
        c.setSemestre(rs.getString("semestre"));
        int ensId = rs.getInt("enseignant_id");
        if (!rs.wasNull()) c.setEnseignant(enseignantDAO.findById(ensId));
        return c;
    }
}