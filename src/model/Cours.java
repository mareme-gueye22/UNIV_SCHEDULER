package model;

public class Cours {
    private int id;
    private String matiere;
    private Enseignant enseignant;
    private Salle salle;
    private String classe;
    private String groupe;
    private String jour;
    private String heureDebut;
    private int duree;
    private String description;
    private String statut;
    private String code;
    private String intitule;
    private String niveau;
    private int credits;
    
    // ✅ Constructeur par défaut (TRÈS IMPORTANT)
   
    public Cours() {}  // ← Ajoutez cette ligne
    // ✅ Constructeur avec paramètres
    public Cours(String matiere, String classe, String groupe, String jour, 
                 String heureDebut, int duree) {
        this.matiere = matiere;
        this.classe = classe;
        this.groupe = groupe;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.duree = duree;
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getMatiere() {
        return matiere;
    }
    
    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
    
    public Enseignant getEnseignant() {
        return enseignant;
    }
    
    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
    
    public Salle getSalle() {
        return salle;
    }
    
    public void setSalle(Salle salle) {
        this.salle = salle;
    }
    
    public String getClasse() {
        return classe;
    }
    
    public void setClasse(String classe) {
        this.classe = classe;
    }
    
    public String getGroupe() {
        return groupe;
    }
    
    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }
    
    public String getJour() {
        return jour;
    }
    
    public void setJour(String jour) {
        this.jour = jour;
    }
    
    public String getHeureDebut() {
        return heureDebut;
    }
    
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }
    
    public int getDuree() {
        return duree;
    }
    
    public void setDuree(int duree) {
        this.duree = duree;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public String getCode() { return code; }
    public void setCode(String code) { 
    	this.code = code; }
    
    public String getIntitule() { 
    	return intitule; }
    public void setIntitule(String intitule) { 
    	this.intitule = intitule; }
    
    public String getNiveau() { 
    	return niveau; }
    public void setNiveau(String niveau) {
    	this.niveau = niveau; }
    
    public int getCredits() { return credits; }
    public void setCredits(int credits) { 
    	this.credits = credits; }

	}
