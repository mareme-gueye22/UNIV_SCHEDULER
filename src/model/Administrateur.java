package model;

import java.util.List;
import java.util.ArrayList;

public class Administrateur extends Utilisateur {
    // Attributs spécifiques à l'administrateur
    private String departement;
    private String poste;
    private List<String> permissions;
    private int niveauAcces; // 1: bas, 2: moyen, 3: élevé
    
    // Constructeur par défaut
    public Administrateur() {
        super();
        this.permissions = new ArrayList<>();
        this.niveauAcces = 3; // Par défaut, accès élevé
        initialiserPermissions();
    }
    
    // Constructeur avec paramètres
    public Administrateur(String nom, String email, String motDePasse, 
                         String departement, String poste) {
        super(nom, email, motDePasse);
        this.departement = departement;
        this.poste = poste;
        this.permissions = new ArrayList<>();
        this.niveauAcces = 3;
        initialiserPermissions();
    }
    
    // Initialiser les permissions par défaut
    private void initialiserPermissions() {
        permissions.add("GESTION_UTILISATEURS");
        permissions.add("GESTION_SALLES");
        permissions.add("GESTION_COURS");
        permissions.add("GENERATION_RAPPORTS");
        permissions.add("CONFIGURATION_SYSTEME");
    }
    
    // Getters et Setters
    public String getDepartement() {
        return departement;
    }
    
    public void setDepartement(String departement) {
        this.departement = departement;
    }
    
    public String getPoste() {
        return poste;
    }
    
    public void setPoste(String poste) {
        this.poste = poste;
    }
    
    public List<String> getPermissions() {
        return permissions;
    }
    
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
    
    public int getNiveauAcces() {
        return niveauAcces;
    }
    
    public void setNiveauAcces(int niveauAcces) {
        this.niveauAcces = niveauAcces;
    }
    
    // Implémentation de la méthode abstraite getRole()
    @Override
    public String getRole() {
        return "Administrateur";
    }
    
    // Méthodes de gestion des utilisateurs
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        System.out.println("Administrateur " + this.nom + " a ajouté: " + utilisateur.getNom());
        // Logique d'ajout en BD
    }
    
    public void supprimerUtilisateur(int idUtilisateur) {
        System.out.println("Administrateur " + this.nom + " a supprimé l'utilisateur ID: " + idUtilisateur);
        // Logique de suppression
    }
    
    public void modifierUtilisateur(Utilisateur utilisateur) {
        System.out.println("Administrateur " + this.nom + " a modifié: " + utilisateur.getNom());
        // Logique de modification
    }
    
    // Méthodes de gestion des salles
    public void ajouterSalle(Salle salle) {
        System.out.println("Salle ajoutée: " + salle.getNumero());
        // Logique d'ajout de salle
    }
    
    public void supprimerSalle(int idSalle) {
        System.out.println("Salle supprimée ID: " + idSalle);
    }
    
    public void modifierSalle(Salle salle) {
        System.out.println("Salle modifiée: " + salle.getNumero());
    }
    
    // Méthodes de gestion des cours
    public void assignerCours(Enseignant enseignant, Cours cours) {
        System.out.println("Cours " + cours.getMatiere() + " assigné à " + enseignant.getNom());
        cours.setEnseignant(enseignant);
    }
    
    // Méthodes de génération de rapports
    public void genererRapportOccupation() {
        System.out.println("Génération du rapport d'occupation des salles...");
        // Logique de génération de rapport
    }
    
    public void genererRapportUtilisateurs() {
        System.out.println("Génération du rapport des utilisateurs...");
    }
    
//     Vérification des permissions
    public boolean aPermission(String permission) {
        return permissions.contains(permission);
    }
    
    // Configuration système
    public void configurerSysteme(String parametre, String valeur) {
        System.out.println("Configuration système: " + parametre + " = " + valeur);
    }
    
    @Override
    public String toString() {
        return "Administrateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", departement='" + departement + '\'' +
                ", poste='" + poste + '\'' +
                ", niveauAcces=" + niveauAcces +
                ", permissions=" + permissions +
                '}';
    }
}