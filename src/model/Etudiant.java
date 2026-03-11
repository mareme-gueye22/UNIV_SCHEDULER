package model;

import java.util.Date;

public class Etudiant extends Utilisateur {
    // Attributs spécifiques à l'étudiant
    private String numeroEtudiant;
    private String niveau; // L1, L2, L3, M1, M2
    private String filiere;
    private String groupe;
    private double moyenne;
    private int telephone;
	@SuppressWarnings("unused")
	private int AnneeEntree;
    
    // Constructeur par défaut
    public Etudiant() {
        super();
    }
    
    // Constructeur avec paramètres
    public Etudiant(String nom, String email, String motDePasse, 
                    String numeroEtudiant, String niveau, String filiere) {
        super(nom, email, motDePasse);
        this.numeroEtudiant = numeroEtudiant;
        this.niveau = niveau;
        this.filiere = filiere;
    }
    
    // Getters et Setters
    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }
    
    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }
    
    public String getNiveau() {
        return niveau;
    }
    
    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
    
    public String getFiliere() {
        return filiere;
    }
    
    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }
    
    public String getGroupe() {
        return groupe;
    }
    
    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }
    
    public double getMoyenne() {
        return moyenne;
    }
    
    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }
    
    // Implémentation de la méthode abstraite getRole()
    @Override
    public String getRole() {
        return "Etudiant";
    }
    
    // Méthodes spécifiques à l'étudiant
    public boolean estEnDeuxiemeAnnee() {
        return "L2".equals(this.niveau);
    }
    
    public String getEmploiDuTemps() {
        // Logique pour récupérer l'emploi du temps
        return "Emploi du temps de " + this.nom + " (" + this.filiere + " " + this.niveau + ")";
    }
    
    public void consulterNotes() {
        System.out.println("Consultation des notes pour l'étudiant: " + this.nom);
    }
    
    public void reserverSalle(Salle salle) {
        System.out.println("L'étudiant " + this.nom + " a réservé la salle " + salle.getNumero());
    }
    
    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", numeroEtudiant='" + numeroEtudiant + '\'' +
                ", niveau='" + niveau + '\'' +
                ", filiere='" + filiere + '\'' +
                ", groupe='" + groupe + '\'' +
                '}';
    }
    public Date getAnneeEntree(Date AnneeEntree) {
		return AnneeEntree;
    	
    }
	public void setAnneeEntree1( int AnneeEntree) {
		this.AnneeEntree = AnneeEntree;
		// TODO Auto-generated method stub
		
	}
	public int getTelephone() {
		return telephone;
		}
	
	public void setTelephone( int telephone) {
		this.telephone=telephone;
		// TODO Auto-generated method stub
		
	}
	public int getAnneeEntree() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setAnneeEntree(int int1) {
		// TODO Auto-generated method stub
		
	}
}