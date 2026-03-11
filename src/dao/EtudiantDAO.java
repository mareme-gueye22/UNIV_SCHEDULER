package dao;

import model.Etudiant;
import model.Cours;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO {
    
    private Connection connection;
    
    public EtudiantDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
    
    // ==================== CREATE ====================
    
    /**
     * Ajouter un nouvel étudiant
     */
    public boolean ajouterEtudiant(Etudiant etudiant) {
        String query = "INSERT INTO etudiants (id, nom, email, mot_de_passe, numero_etudiant, filiere, niveau, groupe, annee_entree, telephone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, etudiant.getId());
            pstmt.setString(2, etudiant.getNom());
            pstmt.setString(3, etudiant.getEmail());
            pstmt.setString(4, etudiant.getMotDePasse());
            pstmt.setString(5, etudiant.getNumeroEtudiant());
            pstmt.setString(6, etudiant.getFiliere());
            pstmt.setString(7, etudiant.getNiveau());
            pstmt.setString(8, etudiant.getGroupe());
            pstmt.setInt(9, etudiant.getAnneeEntree());
            pstmt.setInt(10, etudiant.getTelephone());  // ✅ int
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("✅ Étudiant ajouté : " + etudiant.getNom());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout de l'étudiant : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // ==================== READ ====================
    
    /**
     * Récupérer tous les étudiants
     */
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT * FROM etudiants ORDER BY nom";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Etudiant etudiant = extractEtudiantFromResultSet(rs);
                etudiants.add(etudiant);
            }
            System.out.println("📋 " + etudiants.size() + " étudiants trouvés");
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des étudiants : " + e.getMessage());
            e.printStackTrace();
        }
        return etudiants;
    }
    
    /**
     * Trouver un étudiant par son ID
     */
    public Etudiant findById(int id) {
        String query = "SELECT * FROM etudiants WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractEtudiantFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche de l'étudiant ID " + id + " : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Trouver un étudiant par son email
     */
    public Etudiant findByEmail(String email) {
        String query = "SELECT * FROM etudiants WHERE email = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractEtudiantFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche par email : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Trouver un étudiant par son numéro étudiant
     */
    public Etudiant findByNumeroEtudiant(String numero) {
        String query = "SELECT * FROM etudiants WHERE numero_etudiant = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, numero);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractEtudiantFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche par numéro étudiant : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Trouver les étudiants par filière
     */
    public List<Etudiant> findByFiliere(String filiere) {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT * FROM etudiants WHERE filiere = ? ORDER BY nom";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, filiere);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                etudiants.add(extractEtudiantFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche par filière : " + e.getMessage());
            e.printStackTrace();
        }
        return etudiants;
    }
    
    /**
     * Trouver les étudiants par niveau
     */
    public List<Etudiant> findByNiveau(String niveau) {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT * FROM etudiants WHERE niveau = ? ORDER BY nom";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, niveau);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                etudiants.add(extractEtudiantFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche par niveau : " + e.getMessage());
            e.printStackTrace();
        }
        return etudiants;
    }
    
    /**
     * Trouver les étudiants par groupe
     */
    public List<Etudiant> findByGroupe(String groupe) {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT * FROM etudiants WHERE groupe = ? ORDER BY nom";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, groupe);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                etudiants.add(extractEtudiantFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche par groupe : " + e.getMessage());
            e.printStackTrace();
        }
        return etudiants;
    }
    
    /**
     * Trouver les étudiants par année d'entrée
     */
    public List<Etudiant> findByAnneeEntree(int annee) {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT * FROM etudiants WHERE annee_entree = ? ORDER BY nom";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, annee);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                etudiants.add(extractEtudiantFromResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche par année : " + e.getMessage());
            e.printStackTrace();
        }
        return etudiants;
    }
    
    // ==================== UPDATE ====================
    
    /**
     * Mettre à jour les informations d'un étudiant
     */
    public boolean update(Etudiant etudiant) {
        String query = "UPDATE etudiants SET nom = ?, email = ?, mot_de_passe = ?, filiere = ?, niveau = ?, groupe = ?, telephone = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, etudiant.getNom());
            pstmt.setString(2, etudiant.getEmail());
            pstmt.setString(3, etudiant.getMotDePasse());
            pstmt.setString(4, etudiant.getFiliere());
            pstmt.setString(5, etudiant.getNiveau());
            pstmt.setString(6, etudiant.getGroupe());
            pstmt.setInt(7, etudiant.getTelephone());  // ✅ int
            pstmt.setInt(8, etudiant.getId());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("✅ Étudiant mis à jour : " + etudiant.getNom());
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour de l'étudiant : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Mettre à jour la moyenne d'un étudiant
     */
    public boolean updateMoyenne(int id, double moyenne) {
        String query = "UPDATE etudiants SET moyenne = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setDouble(1, moyenne);
            pstmt.setInt(2, id);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("✅ Moyenne mise à jour pour l'étudiant ID " + id);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour de la moyenne : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // ==================== DELETE ====================
    
    /**
     * Supprimer un étudiant
     */
    public boolean delete(int id) {
        String query = "DELETE FROM etudiants WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("✅ Étudiant supprimé, ID : " + id);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression de l'étudiant : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // ==================== MÉTHODES SPÉCIFIQUES ====================
    
    /**
     * Récupérer les cours d'un étudiant (via son groupe)
     */
    public List<Cours> getCoursEtudiant(int etudiantId) {
        List<Cours> coursList = new ArrayList<>();
        
        // Récupérer d'abord le groupe de l'étudiant
        Etudiant etudiant = findById(etudiantId);
        if (etudiant == null) return coursList;
        
        String query = "SELECT c.* FROM cours c WHERE c.niveau = ? AND (c.groupe = ? OR c.groupe = 'Groupes A+B')";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, etudiant.getNiveau());
            pstmt.setString(2, etudiant.getGroupe());
            
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
            System.err.println("❌ Erreur lors de la récupération des cours de l'étudiant : " + e.getMessage());
            e.printStackTrace();
        }
        return coursList;
    }
    
    /**
     * Vérifier si un étudiant existe déjà (par email ou numéro étudiant)
     */
    public boolean existeDeja(String email, String numeroEtudiant) {
        String query = "SELECT COUNT(*) FROM etudiants WHERE email = ? OR numero_etudiant = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, numeroEtudiant);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la vérification d'existence : " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Compter le nombre d'étudiants par filière
     */
    public int countByFiliere(String filiere) {
        String query = "SELECT COUNT(*) FROM etudiants WHERE filiere = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, filiere);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors du comptage par filière : " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
    
    // ==================== MÉTHODE UTILITAIRE ====================
    
    /**
     * Extraire un objet Etudiant depuis un ResultSet
     */
    private Etudiant extractEtudiantFromResultSet(ResultSet rs) throws SQLException {
        Etudiant etudiant = new Etudiant();
        
        etudiant.setId(rs.getInt("id"));
        etudiant.setNom(rs.getString("nom"));
        etudiant.setEmail(rs.getString("email"));
        etudiant.setMotDePasse(rs.getString("mot_de_passe"));
        etudiant.setNumeroEtudiant(rs.getString("numero_etudiant"));
        etudiant.setFiliere(rs.getString("filiere"));
        etudiant.setNiveau(rs.getString("niveau"));
        etudiant.setGroupe(rs.getString("groupe"));
        etudiant.setAnneeEntree(rs.getInt("annee_entree"));
        etudiant.setTelephone(rs.getInt("telephone"));  // ✅ int
        etudiant.setMoyenne(rs.getDouble("moyenne"));
        
        return etudiant;
    }
}