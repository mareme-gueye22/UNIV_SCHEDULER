package dao;

import model.Cours;
import model.Enseignant;
import model.Salle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO {
    
    private Connection connection;
    private EnseignantDAO enseignantDAO;
    private SalleDAO salleDAO;
    
    public CoursDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
        this.enseignantDAO = new EnseignantDAO();
        this.salleDAO = new SalleDAO();
    }
    
    // CREATE - Ajouter un cours
    public boolean ajouterCours(Cours cours) {
        String query = "INSERT INTO cours (matiere, enseignant_id, salle_id, classe, groupe, jour, heure_debut, duree, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, cours.getMatiere());
            
            if (cours.getEnseignant() != null) {
                pstmt.setInt(2, cours.getEnseignant().getId());
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            
            if (cours.getSalle() != null) {
                pstmt.setInt(3, cours.getSalle().getId());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            
            pstmt.setString(4, cours.getClasse());
            pstmt.setString(5, cours.getGroupe());
            pstmt.setString(6, cours.getJour());
            pstmt.setString(7, cours.getHeureDebut());
            pstmt.setInt(8, cours.getDuree());
            pstmt.setString(9, cours.getDescription());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    cours.setId(rs.getInt(1));
                }
                System.out.println("✅ Cours ajouté : " + cours.getMatiere());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout du cours : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // READ - Récupérer tous les cours
    public List<Cours> findAll() {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT * FROM cours ORDER BY jour, heure_debut";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Cours cours = extractCoursFromResultSet(rs);
                coursList.add(cours);
            }
            System.out.println("📋 " + coursList.size() + " cours trouvés");
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des cours : " + e.getMessage());
            e.printStackTrace();
        }
        return coursList;
    }
    
    // READ - Trouver un cours par ID
    public Cours findById(int id) {
        String query = "SELECT * FROM cours WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractCoursFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche du cours ID " + id + " : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    // READ - Cours par enseignant
    public List<Cours> findByEnseignant(int enseignantId) {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT * FROM cours WHERE enseignant_id = ? ORDER BY jour, heure_debut";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, enseignantId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                coursList.add(extractCoursFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche par enseignant : " + e.getMessage());
            e.printStackTrace();
        }
        return coursList;
    }
    
    // READ - Cours par salle
    public List<Cours> findBySalle(int salleId) {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT * FROM cours WHERE salle_id = ? ORDER BY jour, heure_debut";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, salleId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                coursList.add(extractCoursFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche par salle : " + e.getMessage());
            e.printStackTrace();
        }
        return coursList;
    }
    
    // READ - Cours par jour
    public List<Cours> findByJour(String jour) {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT * FROM cours WHERE jour = ? ORDER BY heure_debut";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, jour);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                coursList.add(extractCoursFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche par jour : " + e.getMessage());
            e.printStackTrace();
        }
        return coursList;
    }
    
    // READ - Cours par classe
    public List<Cours> findByClasse(String classe) {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT * FROM cours WHERE classe = ? ORDER BY jour, heure_debut";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, classe);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                coursList.add(extractCoursFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche par classe : " + e.getMessage());
            e.printStackTrace();
        }
        return coursList;
    }
    
    // UPDATE - Mettre à jour un cours
    public boolean update(Cours cours) {
        String query = "UPDATE cours SET matiere = ?, enseignant_id = ?, salle_id = ?, classe = ?, groupe = ?, jour = ?, heure_debut = ?, duree = ?, description = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, cours.getMatiere());
            
            if (cours.getEnseignant() != null) {
                pstmt.setInt(2, cours.getEnseignant().getId());
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            
            if (cours.getSalle() != null) {
                pstmt.setInt(3, cours.getSalle().getId());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            
            pstmt.setString(4, cours.getClasse());
            pstmt.setString(5, cours.getGroupe());
            pstmt.setString(6, cours.getJour());
            pstmt.setString(7, cours.getHeureDebut());
            pstmt.setInt(8, cours.getDuree());
            pstmt.setString(9, cours.getDescription());
            pstmt.setInt(10, cours.getId());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("✅ Cours mis à jour : " + cours.getMatiere());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour du cours : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // DELETE - Supprimer un cours
    public boolean delete(int id) {
        String query = "DELETE FROM cours WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("✅ Cours supprimé, ID : " + id);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression du cours : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // Méthodes spécifiques
    public List<Cours> findSallesDisponibles(String jour, String heure, int duree) {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT * FROM cours WHERE jour = ? AND heure_debut <= ? AND ADDTIME(heure_debut, SEC_TO_TIME(duree*3600)) > ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, jour);
            pstmt.setString(2, heure);
            pstmt.setString(3, heure);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                coursList.add(extractCoursFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche de disponibilité : " + e.getMessage());
            e.printStackTrace();
        }
        return coursList;
    }
    
    // Vérifier les conflits
    public boolean verifierConflit(Cours nouveauCours) {
        String query = "SELECT COUNT(*) FROM cours WHERE salle_id = ? AND jour = ? AND ((heure_debut <= ? AND ADDTIME(heure_debut, SEC_TO_TIME(duree*3600)) > ?) OR (heure_debut >= ? AND heure_debut < ADDTIME(?, SEC_TO_TIME(?*3600))))";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, nouveauCours.getSalle().getId());
            pstmt.setString(2, nouveauCours.getJour());
            pstmt.setString(3, nouveauCours.getHeureDebut());
            pstmt.setString(4, nouveauCours.getHeureDebut());
            pstmt.setString(5, nouveauCours.getHeureDebut());
            pstmt.setString(6, nouveauCours.getHeureDebut());
            pstmt.setInt(7, nouveauCours.getDuree());
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la vérification des conflits : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // Méthode utilitaire pour extraire un cours du ResultSet
    private Cours extractCoursFromResultSet(ResultSet rs) throws SQLException {
        Cours cours = new Cours();
        cours.setId(rs.getInt("id"));
        cours.setMatiere(rs.getString("matiere"));
        cours.setClasse(rs.getString("classe"));
        cours.setGroupe(rs.getString("groupe"));
        cours.setJour(rs.getString("jour"));
        cours.setHeureDebut(rs.getString("heure_debut"));
        cours.setDuree(rs.getInt("duree"));
        cours.setDescription(rs.getString("description"));
        
        // Récupérer l'enseignant
        int enseignantId = rs.getInt("enseignant_id");
        if (!rs.wasNull()) {
            Enseignant enseignant = enseignantDAO.findById(enseignantId);
            cours.setEnseignant(enseignant);
        }
        
        // Récupérer la salle
        int salleId = rs.getInt("salle_id");
        if (!rs.wasNull()) {
            Salle salle = salleDAO.findById(salleId);
            cours.setSalle(salle);
        }
        
        return cours;
    }

	public List<Cours> findByNiveau(String niveau) {
		// TODO Auto-generated method stub
		return null;
	}
}