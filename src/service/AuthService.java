package service;

import dao.UtilisateurDAO;
import model.*;
import utils.PasswordUtils;
import java.sql.SQLException;

public class AuthService {
    
    private UtilisateurDAO utilisateurDAO;
    
    public AuthService() {
        this.utilisateurDAO = new UtilisateurDAO();
    }
    
    /**
     * Authentifier un utilisateur par email et mot de passe
     */
    public Utilisateur login(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            System.out.println("❌ Email ou mot de passe vide");
            return null;
        }
        
        // Vérifier si l'utilisateur existe
		Utilisateur utilisateur = utilisateurDAO.findByEmail(email);
		
		if (utilisateur == null) {
		    System.out.println("❌ Utilisateur non trouvé: " + email);
		    return null;
		}
		
		// Vérifier le statut
		if (!"ACTIF".equals(utilisateur.getStatut())) {
		    System.out.println("❌ Compte inactif ou suspendu: " + email);
		    return null;
		}
		
		// Vérifier le mot de passe
		if (PasswordUtils.verifyPassword(password, utilisateur.getSalt(), utilisateur.getMotDePasse())) {
		    System.out.println("✅ Connexion réussie: " + utilisateur.getNom() + " (" + utilisateur.getRole() + ")");
		    
		    // Mettre à jour la dernière connexion
		    utilisateurDAO.updateLastConnection(utilisateur.getId());
		    
		    return utilisateur;
		} else {
		    System.out.println("❌ Mot de passe incorrect pour: " + email);
		    return null;
		}
    }
    
    /**
     * Changer le mot de passe d'un utilisateur
     */
    public boolean changePassword(int userId, String oldPassword, String newPassword) throws SQLException {
        Utilisateur utilisateur = utilisateurDAO.findById(userId);
		
		if (utilisateur == null) {
		    return false;
		}
		
		// Vérifier l'ancien mot de passe
		if (!PasswordUtils.verifyPassword(oldPassword, utilisateur.getSalt(), utilisateur.getMotDePasse())) {
		    return false;
		}
		
		// Générer un nouveau sel et hacher le nouveau mot de passe
		String newSalt = PasswordUtils.generateSalt();
		String newHashedPassword = PasswordUtils.hashPassword(newPassword, newSalt);
		
		// Mettre à jour
		return utilisateurDAO.updatePassword(userId, newHashedPassword, newSalt);
    }
    
    /**
     * Réinitialiser le mot de passe (pour admin)
     */
    public String resetPassword(String email) {
        Utilisateur utilisateur = utilisateurDAO.findByEmail(email);
		
		if (utilisateur == null) {
		    return null;
		}
		
		// Générer un nouveau mot de passe aléatoire
		String newPassword = PasswordUtils.generateRandomPassword(8);
		String newSalt = PasswordUtils.generateSalt();
		String newHashedPassword = PasswordUtils.hashPassword(newPassword, newSalt);
		
		if (utilisateurDAO.updatePassword(utilisateur.getId(), newHashedPassword, newSalt)) {
		    return newPassword;
		}
        return null;
    }
}