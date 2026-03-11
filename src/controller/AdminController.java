package controller;

import model.*;
import service.UtilisateurService;
import service.SalleService;
import service.CoursService;
import java.util.List;

public class AdminController {
    
    private UtilisateurService utilisateurService;
    private SalleService salleService;
    private CoursService coursService;
    
    public AdminController() {
        this.utilisateurService = new UtilisateurService();
        this.salleService = new SalleService();
        this.coursService = new CoursService();
    }
    
    // ========== GESTION DES UTILISATEURS ==========
    
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }
    
    public List<Utilisateur> getUtilisateursByRole(String role) {
        return utilisateurService.getUtilisateursByRole(role);
    }
    
    public boolean createEnseignant(String nom, String email, String password, 
                                    String departement, String grade, int telephone) {
        Enseignant enseignant = new Enseignant(nom, email, password, departement, grade, telephone);
        return utilisateurService.createUtilisateur(enseignant, password);
    }
    
    public boolean createEtudiant(String nom, String email, String password,
                                  String numero, String filiere, String niveau, 
                                  String groupe, int annee, int telephone) {
        Etudiant etudiant = new Etudiant(nom, email, password, numero, filiere, niveau, groupe, annee, telephone);
        return utilisateurService.createUtilisateur(etudiant, password);
    }
    
    public boolean toggleUtilisateurStatut(int id, boolean activer) {
        return utilisateurService.setUtilisateurStatut(id, activer ? "ACTIF" : "INACTIF");
    }
    
    public boolean deleteUtilisateur(int id) {
        return utilisateurService.deleteUtilisateur(id);
    }
    
    // ========== GESTION DES SALLES ==========
    
    public List<Salle> getAllSalles() {
        return salleService.getAllSalles();
    }
    
    public boolean createSalle(String code, String nom, String batiment, int etage, 
                              int capacite, String type, String equipements) {
        Salle salle = new Salle(code, nom, batiment, etage, capacite, type, equipements);
        return salleService.addSalle(salle);
    }
    
    public boolean updateSalleStatut(int id, String statut) {
        return salleService.updateStatutSalle(id, statut);
    }
    
    // ========== STATISTIQUES ==========
    
    public int getNombreUtilisateurs() {
        return getAllUtilisateurs().size();
    }
    
    public int getNombreSalles() {
        return getAllSalles().size();
    }
    
    public int getNombreCours() {
        return coursService.getAllCours().size();
    }
}
