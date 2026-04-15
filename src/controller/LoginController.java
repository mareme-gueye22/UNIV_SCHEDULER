package controller;

import model.Enseignant;
import model.Etudiant;
import model.Utilisateur;
import service.AuthService;
import view.*;

public class LoginController {
    private AuthService authService = new AuthService();

    public boolean handleLogin(String email, String password) {
        return authService.login(email, password) != null;
    }

    public void redirectToDashboard(Utilisateur u) {
        if (u == null) return;
        switch (u.getRole()) {
            case "ADMIN": new AdminDashboard().setVisible(true); break;
            case "GESTIONNAIRE": new GestionnaireDashboard().setVisible(true); break;
            case "ENSEIGNANT": new EnseignantDashboard((Enseignant) u).setVisible(true); break;
            case "ETUDIANT": new EtudiantDashboard((Etudiant) u).setVisible(true); break;
        }
    }
}