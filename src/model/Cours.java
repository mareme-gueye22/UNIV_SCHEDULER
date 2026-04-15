package model;

public class Cours {
    private int id;
    private String code;
    private String intitule;
    private String description;
    private int credits;
    private int heures;
    private String niveau;
    private String semestre;
    private Enseignant enseignant;
    private Salle salle;
    // Ajout pour compatibilité avec votre code existant
    private String matiere;
    private String classe;
    private String groupe;
    private String jour;
    private String heureDebut;
    private int duree;
    private String statut;

    public Cours() {}

    // Getters / Setters (tous)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getIntitule() { return intitule; }
    public void setIntitule(String intitule) { this.intitule = intitule; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public int getHeures() { return heures; }
    public void setHeures(int heures) { this.heures = heures; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }

    public Enseignant getEnseignant() { return enseignant; }
    public void setEnseignant(Enseignant enseignant) { this.enseignant = enseignant; }

    public Salle getSalle() { return salle; }
    public void setSalle(Salle salle) { this.salle = salle; }

    public String getMatiere() { return matiere; }
    public void setMatiere(String matiere) { this.matiere = matiere; }

    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }

    public String getGroupe() { return groupe; }
    public void setGroupe(String groupe) { this.groupe = groupe; }

    public String getJour() { return jour; }
    public void setJour(String jour) { this.jour = jour; }

    public String getHeureDebut() { return heureDebut; }
    public void setHeureDebut(String heureDebut) { this.heureDebut = heureDebut; }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

	public int getEnseignantId() {
		// TODO Auto-generated method stub
		return 0;
	}
}