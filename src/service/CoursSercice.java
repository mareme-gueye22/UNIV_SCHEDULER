package service;

import dao.CoursDAO;
import model.Cours;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class CoursService {  // ✅ Nom corrigé
    
    private CoursDAO coursDAO;
    
    public CoursService() {
        try {
            this.coursDAO = new CoursDAO();
        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion à la base de données: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Ajouter un nouveau cours
     */
    public boolean addCours(Cours cours) {
        if (cours == null) return false;
        try {
            return coursDAO.ajouterCours(cours);
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout du cours: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Récupérer tous les cours
     */
    public List<Cours> getAllCours() {
        try {
            return coursDAO.findAll();
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des cours: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();  // Retourne liste vide en cas d'erreur
        }
    }
    
    /**
     * Récupérer les cours par enseignant
     */
    public List<Cours> getCoursByEnseignant(int enseignantId) {
        try {
            return coursDAO.findByEnseignant(enseignantId);
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des cours par enseignant: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Récupérer les cours par niveau
     */
    public List<Cours> getCoursByNiveau(String niveau) {
        try {
            return coursDAO.findByNiveau(niveau);
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des cours par niveau: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Vérifier les conflits pour un nouveau cours
     */
    public boolean hasConflit(Cours nouveauCours) {
        if (nouveauCours == null) return true;
        try {
            return coursDAO.verifierConflit(nouveauCours);
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la vérification des conflits: " + e.getMessage());
            e.printStackTrace();
            return true;  // Par sécurité, on considère qu'il y a conflit
        }
    }
    
    /**
     * Supprimer un cours
     */
    public boolean deleteCours(int id) {
        try {
            return coursDAO.delete(id);
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression du cours: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}