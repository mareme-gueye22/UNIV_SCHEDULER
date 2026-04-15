package controller;

import model.*;
import service.*;
import java.util.List;

public class AdminController {
    private UtilisateurService userService = new UtilisateurService();
    private SalleService salleService = new SalleService();
    @SuppressWarnings("unused")
	private CoursService coursService = new CoursService();

    public List<Utilisateur> getAllUtilisateurs() { return userService.getAllUtilisateurs(); }
    public boolean createEnseignant(String nom, String email, String pass, String dept, String grade, int tel) {
        Enseignant e = new Enseignant(nom, email, pass, dept, tel);
        e.setGrade(grade);
        return userService.createUtilisateur(e, pass);
    }
    public boolean createEtudiant() { /* à implémenter */ return false; }
    public boolean deleteUtilisateur(int id) { return userService.deleteUtilisateur(id); }
    public int getNombreSalles() { return salleService.getAllSalles().size(); }
}