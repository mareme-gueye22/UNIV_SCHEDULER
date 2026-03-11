package service;

import dao.UtilisateurDAO;
import model.*;
import utils.PasswordUtils;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class UtilisateurService {
    
    private UtilisateurDAO utilisateurDAO;
    
    public UtilisateurService() {
        this.utilisateurDAO = new UtilisateurDAO();
    }
    
    /**
     * Créer un nouvel utilisateur avec mot de passe haché
     */
    public boolean createUtilisateur(Utilisateur utilisateur, String plainPassword) {
        if (utilisateur == null || plainPassword == null || plainPassword.isEmpty()) {
            return false;
        }
        
        // Vérifier si l'email existe déjà
		if (utilisateurDAO.findByEmail(utilisateur.getEmail()) != null) {
		    System.out.println("❌ Email déjà utilisé: " + utilisateur.getEmail());
		    return false;
		}
		
		// Générer sel et hacher le mot de passe
		String salt = PasswordUtils.generateSalt();
		String hashedPassword = PasswordUtils.hashPassword(plainPassword, salt);
		
		utilisateur.setMotDePasse(hashedPassword);
		utilisateur.setSalt(salt);
		
		return utilisateurDAO.save(utilisateur);
    }
    
    /**
     * Récupérer tous les utilisateurs
     */
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurDAO.findAll();
    }
    
    /**
     * Récupérer les utilisateurs par rôle
     */
    public List<Utilisateur> getUtilisateursByRole(String role) {
        return utilisateurDAO.findByRole(role);
    }
    
    /**
     * Rechercher un utilisateur par email
     */
    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurDAO.findByEmail(email);
    }
    
    /**
     * Activer/Désactiver un utilisateur
     */
    public boolean setUtilisateurStatut(int id, String statut) {
        if ("ACTIF".equals(statut)) {
		    return utilisateurDAO.activate(id);  // ✅ Correction: activate() au lieu de activate11()
		} else {
		    return utilisateurDAO.deactivate(id);
		}
    }
    
    /**
     * Supprimer un utilisateur
     */
    public boolean deleteUtilisateur(int id) {
        return utilisateurDAO.delete(id);
    }
}