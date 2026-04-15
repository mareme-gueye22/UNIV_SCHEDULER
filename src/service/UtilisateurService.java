package service;

import dao.UtilisateurDAO;
import model.Utilisateur;
import utils.PasswordUtils;
import java.util.List;

public class UtilisateurService {
    private UtilisateurDAO dao = new UtilisateurDAO();

    public boolean createUtilisateur(Utilisateur u, String plainPassword) {
        if (dao.findByEmail(u.getEmail()) != null) return false;
        String salt = PasswordUtils.generateSalt();
        String hash = PasswordUtils.hashPassword(plainPassword, salt);
        u.setMotDePasse(hash);
        u.setSalt(salt);
        return dao.save(u);
    }

    public List<Utilisateur> getAllUtilisateurs() { return dao.findAll(); }
    public List<Utilisateur> getUtilisateursByRole(String role) { return dao.findByRole(role); }
    public boolean setUtilisateurStatut(int id, String statut) {
        return "ACTIF".equals(statut) ? dao.activate(id) : dao.deactivate(id);
    }
    public boolean deleteUtilisateur(int id) { return dao.delete(id); }
}