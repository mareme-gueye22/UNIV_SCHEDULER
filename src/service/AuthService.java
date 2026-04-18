package service;

import dao.UtilisateurDAO;
import model.Utilisateur;
import utils.PasswordUtils;

public class AuthService {
    private UtilisateurDAO utilisateurDAO;

    public AuthService() {
        this.utilisateurDAO = new UtilisateurDAO();
    }

    public Utilisateur login(String email, String password) {
        System.out.println("=== AuthService.login ===");
        
        Utilisateur u = utilisateurDAO.findByEmail(email);
        
        if (u == null) {
            System.out.println("Utilisateur null");
            return null;
        }
        
        System.out.println("Utilisateur trouvé: " + u.getNom());
        System.out.println("Statut: '" + u.getStatut() + "'");
        System.out.println("Salt: '" + u.getSalt() + "'");
        System.out.println("Mot de passe stocké: '" + u.getMotDePasse() + "'");
        
        if (!"ACTIF".equals(u.getStatut())) {
            System.out.println("Statut != ACTIF");
            return null;
        }
        
        boolean verified = PasswordUtils.verifyPassword(password, u.getSalt(), u.getMotDePasse());
        System.out.println("Vérification password: " + verified);
        
        if (verified) {
            utilisateurDAO.updateLastConnection(u.getId());
            System.out.println("Login réussi !");
            return u;
        }
        
        System.out.println("Mot de passe incorrect");
        return null;
    }
}