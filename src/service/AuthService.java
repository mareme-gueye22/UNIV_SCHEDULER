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
        Utilisateur u = utilisateurDAO.findByEmail(email);
        if (u == null) return null;
        if (!"ACTIF".equals(u.getStatut())) return null;
        if (PasswordUtils.verifyPassword(password, u.getSalt(), u.getMotDePasse())) {
            utilisateurDAO.updateLastConnection(u.getId());
            return u;
        }
        return null;
    }
}