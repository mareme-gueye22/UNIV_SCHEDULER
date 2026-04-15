package model;

public class Salle {
    private int id;
    private String code;
    private String nom;
    private String batiment;
    private int etage;
    private int capacite;
    private String type;
    private String equipements;
    private String statut;
    private int responsableId;

    public Salle() {
        this.statut = "DISPONIBLE";
    }

    public Salle(String code, String nom, String batiment, int etage, int capacite, String type) {
        this();
        this.code = code;
        this.nom = nom;
        this.batiment = batiment;
        this.etage = etage;
        this.capacite = capacite;
        this.type = type;
    }

    // Getters / Setters (tous)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getBatiment() { return batiment; }
    public void setBatiment(String batiment) { this.batiment = batiment; }

    public int getEtage() { return etage; }
    public void setEtage(int etage) { this.etage = etage; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getEquipements() { return equipements; }
    public void setEquipements(String equipements) { this.equipements = equipements; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public int getResponsableId() { return responsableId; }
    public void setResponsableId(int responsableId) { this.responsableId = responsableId; }

	public String getNumero() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDisponible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}