package dao;

import model.Cours;
import model.Enseignant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnseignantDAO {
    
    private Connection connection;
    
    public EnseignantDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
    
    // CREATE - Ajouter un enseignant
    public boolean ajouterEnseignant(Enseignant enseignant) {
        String query = "INSERT INTO enseignants (id, nom, email, mot_de_passe, departement, grade, telephone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, enseignant.getId());
            pstmt.setString(2, enseignant.getNom());
            pstmt.setString(3, enseignant.getEmail());
            pstmt.setString(4, enseignant.getMotDePasse());
            pstmt.setString(5, enseignant.getDepartement());
            pstmt.setString(6, enseignant.getGrade());
            pstmt.setInt(7, enseignant.getTelephone());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("✅ Enseignant ajouté : " + enseignant.getNom());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout de l'enseignant : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // READ - Récupérer tous les enseignants
    public List<Enseignant> findAll() {
        List<Enseignant> enseignants = new ArrayList<>();
        String query = "SELECT * FROM enseignants ORDER BY nom";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Enseignant enseignant = new Enseignant();
                enseignant.setId(rs.getInt("id"));
                enseignant.setNom(rs.getString("nom"));
                enseignant.setEmail(rs.getString("email"));
                enseignant.setMotDePasse(rs.getString("mot_de_passe"));
                enseignant.setDepartement(rs.getString("departement"));
                enseignant.setGrade(rs.getString("grade"));
                enseignant.setTelephone(rs.getInt("telephone"));
                
                enseignants.add(enseignant);
            }
            System.out.println("📋 " + enseignants.size() + " enseignants trouvés");
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des enseignants : " + e.getMessage());
            e.printStackTrace();
        }
        return enseignants;
    }
    
    // READ - Trouver un enseignant par ID
    public Enseignant findById(int id) {
        String query = "SELECT * FROM enseignants WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Enseignant enseignant = new Enseignant();
                enseignant.setId(rs.getInt("id"));
                enseignant.setNom(rs.getString("nom"));
                enseignant.setEmail(rs.getString("email"));
                enseignant.setMotDePasse(rs.getString("mot_de_passe"));
                enseignant.setDepartement(rs.getString("departement"));
                enseignant.setGrade(rs.getString("grade"));
                enseignant.setTelephone(rs.getInt("telephone"));
                
                return enseignant;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche de l'enseignant ID " + id + " : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    // READ - Trouver par email
    public Enseignant findByEmail(String email) {
        String query = "SELECT * FROM enseignants WHERE email = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Enseignant enseignant = new Enseignant();
                enseignant.setId(rs.getInt("id"));
                enseignant.setNom(rs.getString("nom"));
                enseignant.setEmail(rs.getString("email"));
                enseignant.setMotDePasse(rs.getString("mot_de_passe"));
                enseignant.setDepartement(rs.getString("departement"));
                enseignant.setGrade(rs.getString("grade"));
                enseignant.setTelephone(rs.getInt("telephone"));
                
                return enseignant;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche par email : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    // UPDATE - Mettre à jour un enseignant
    public boolean update(Enseignant enseignant) {
        String query = "UPDATE enseignants SET nom = ?, email = ?, mot_de_passe = ?, departement = ?, grade = ?, telephone = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, enseignant.getNom());
            pstmt.setString(2, enseignant.getEmail());
            pstmt.setString(3, enseignant.getMotDePasse());
            pstmt.setString(4, enseignant.getDepartement());
            pstmt.setString(5, enseignant.getGrade());
            pstmt.setInt(6, enseignant.getTelephone());
            pstmt.setInt(7, enseignant.getId());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("✅ Enseignant mis à jour : " + enseignant.getNom());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // DELETE - Supprimer un enseignant
    public boolean delete(int id) {
        String query = "DELETE FROM enseignants WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("✅ Enseignant supprimé, ID : " + id);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // Méthodes spécifiques
    public List<Cours> getCoursEnseignant(int enseignantId) {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT c.* FROM cours c WHERE c.enseignant_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, enseignantId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Cours cours = new Cours();
                cours.setId(rs.getInt("id"));
                cours.setIntitule(rs.getString("intitule"));
                cours.setCode(rs.getString("code"));
                cours.setNiveau(rs.getString("niveau"));
                cours.setCredits(rs.getInt("credits"));
                
                coursList.add(cours);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des cours : " + e.getMessage());
            e.printStackTrace();
        }
        return coursList;
    }
}