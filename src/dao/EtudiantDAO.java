package dao;

import model.Etudiant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO {
    private Connection connection;

    public EtudiantDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Etudiant> findAll() {
        List<Etudiant> list = new ArrayList<>();
        String sql = "SELECT * FROM etudiants";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Etudiant findById(int id) {
        String sql = "SELECT * FROM etudiants WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return map(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    private Etudiant map(ResultSet rs) throws SQLException {
        Etudiant e = new Etudiant();
        e.setId(rs.getInt("id"));
        e.setNom(rs.getString("nom"));
        e.setEmail(rs.getString("email"));
        e.setMotDePasse(rs.getString("mot_de_passe"));
        e.setNumeroEtudiant(rs.getString("numero_etudiant"));
        e.setFiliere(rs.getString("filiere"));
        e.setNiveau(rs.getString("niveau"));
        e.setGroupe(rs.getString("groupe"));
        e.setAnneeEntree(rs.getInt("annee_entree"));
        e.setTelephone(rs.getInt("telephone"));
        e.setMoyenne(rs.getDouble("moyenne"));
        return e;
    }
}