package model;

public class Gestionnaire extends Utilisateur {
    private String departement;
    private String service;

    public Gestionnaire() {
        this.role = "GESTIONNAIRE";
    }

    public Gestionnaire(String nom, String email, String motDePasse, String departement, String service) {
        super(nom, email, motDePasse);
        this.departement = departement;
        this.service = service;
        this.role = "GESTIONNAIRE";
    }

    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }

    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    @Override
    public String getRole() { return "GESTIONNAIRE"; }
}