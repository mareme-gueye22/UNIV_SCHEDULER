package model;

import java.sql.Timestamp;

public abstract class Utilisateur {
    protected int id;
    protected String nom;
    protected String email;
    protected String motDePasse;
    protected String salt;
    protected String role;
    protected String statut;
    protected Timestamp dateCreation;
    protected Timestamp derniereConnexion;

    public Utilisateur() {}

    public Utilisateur(String nom, String email, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.statut = "ACTIF";
    }

    // Getters / Setters (tous présents)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getSalt() { return salt; }
    public void setSalt(String salt) { this.salt = salt; }

    public abstract String getRole();  // implémenté dans les sous-classes
    public void setRole(String role) { this.role = role; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Timestamp getDateCreation() { return dateCreation; }
    public void setDateCreation(Timestamp dateCreation) { this.dateCreation = dateCreation; }

    public Timestamp getDerniereConnexion() { return derniereConnexion; }
    public void setDerniereConnexion(Timestamp derniereConnexion) { this.derniereConnexion = derniereConnexion; }
}