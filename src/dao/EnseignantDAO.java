package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Enseignant;

public class EnseignantDAO {
    private Connection connection;

    public EnseignantDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Enseignant> findAll() {
        List<Enseignant> list = new ArrayList<>();
        String sql = "SELECT * FROM enseignants";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Enseignant findById(int id) {
        String sql = "SELECT * FROM enseignants WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return map(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Enseignant findByEmail(String email) {
        String sql = "SELECT e.* FROM enseignants e JOIN utilisateurs u ON e.id = u.id WHERE u.email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return map(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    private Enseignant map(ResultSet rs) throws SQLException {
        Enseignant e = new Enseignant();
        e.setId(rs.getInt("id"));
        e.setNom(rs.getString("nom"));
        e.setEmail(rs.getString("email"));
        e.setMotDePasse(rs.getString("mot_de_passe"));
        e.setDepartement(rs.getString("departement"));
        e.setGrade(rs.getString("grade"));
        e.setTelephone(rs.getInt("telephone"));
        return e;
    }
}