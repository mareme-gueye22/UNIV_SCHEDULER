package dao;

import model.Salle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleDAO {
    
    public List<Salle> findAll() {
        List<Salle> salles = new ArrayList<>();
        String query = "SELECT * FROM salles";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Salle salle = new Salle();
                salle.setId(rs.getInt("id"));
                salle.setNumero(rs.getString("numero"));
                salle.setCapacite(rs.getInt("capacite"));
                salle.setBatiment(rs.getString("batiment"));
                salles.add(salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salles;
    }
    
    public Salle findById(int id) {
        String query = "SELECT * FROM salles WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Salle salle = new Salle();
                salle.setId(rs.getInt("id"));
                salle.setNumero(rs.getString("numero"));
                salle.setCapacite(rs.getInt("capacite"));
                salle.setBatiment(rs.getString("batiment"));
                return salle;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void save(Salle salle) {
        String query = "INSERT INTO salles (numero, capacite, batiment) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, salle.getNumero());
            pstmt.setInt(2, salle.getCapacite());
            pstmt.setString(3, salle.getBatiment());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public List<Salle> rechercher1(Integer capaciteMin, String type, String batiment) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean verifierDisponibilite(int salleId, String date, String heureDebut, String heureFin) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateStatut(int id, String statut) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Salle> rechercher(Integer capaciteMin, String type, String batiment) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean ajouterSalle(Salle salle) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Salle> findDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}
	
}