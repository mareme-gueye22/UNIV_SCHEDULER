package model;

import java.sql.Timestamp;

public abstract class Utilisateur {
    protected int id;
    protected String nom;
    protected String email;
    protected String motDePasse;
	@SuppressWarnings("unused")
	private String statut;
	private String salt;
    
    // Constructeurs
    public Utilisateur() {}
    
    public Utilisateur(String nom, String email, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMotDePasse() {
        return motDePasse;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    // Méthode abstraite que les classes filles doivent implémenter
    public abstract String getRole();

	public String getStatut() {
		// TODO Auto-generated method stub
		return null;
	}
	public String setStatut(String statut) {
		return this.statut= statut;
		
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setRole1(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setDateCreation1(Timestamp timestamp) {
		// TODO Auto-generated method stub
		
	}

	public void setDerniereConnexion(Timestamp timestamp) {
		// TODO Auto-generated method stub
		
	}

	public void setDateCreation(Timestamp timestamp) {
		// TODO Auto-generated method stub
		
	}

	public void setRole(String string) {
		// TODO Auto-generated method stub
		
	}
}