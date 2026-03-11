package controller;

import model.Utilisateur;
import service.AuthService;
import view.*;

public class LoginController {
    
    private AuthService authService;
    
    public LoginController() {
        this.authService = new AuthService();
    }
    
    /**
     * Traiter la tentative de connexion
     */
    public boolean handleLogin(String email, String password) {
        return authService.login(email, password) != null;
    }
    
    /**
     * Rediriger vers le dashboard approprié selon le rôle
     */
    public void redirectToDashboard(Utilisateur utilisateur) {
        if (utilisateur == null) return;
        
        switch (utilisateur.getRole()) {
            case "ADMIN":
                new AdminDashboard().setVisible(true);
                break;
            case "GESTIONNAIRE":
                new GestionnaireDashboard().setVisible(true);
                break;
            case "ENSEIGNANT":
                // new EnseignantDashboard((Enseignant) utilisateur).setVisible(true);
                break;
            case "ETUDIANT":
                // new EtudiantDashboard((Etudiant) utilisateur).setVisible(true);
                break;
            default:
                System.out.println("Rôle inconnu: " + utilisateur.getRole());
        }
    }
    
    /**
     * Réinitialiser le mot de passe
     */
    public String resetPassword(String email) {
        return authService.resetPassword(email);
    }
}
