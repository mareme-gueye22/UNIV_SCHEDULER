package model;

import java.util.ArrayList;
import java.util.List;

public class Administrateur extends Utilisateur {
    private String departement;
    private String poste;
    private List<String> permissions;
    private int niveauAcces;

    public Administrateur() {
        this.role = "ADMIN";
        this.permissions = new ArrayList<>();
        this.niveauAcces = 3;
        initialiserPermissions();
    }

    public Administrateur(String nom, String email, String motDePasse, String departement, String poste) {
        super(nom, email, motDePasse);
        this.departement = departement;
        this.poste = poste;
        this.permissions = new ArrayList<>();
        this.niveauAcces = 3;
        this.role = "ADMIN";
        initialiserPermissions();
    }

    private void initialiserPermissions() {
        permissions.add("GESTION_UTILISATEURS");
        permissions.add("GESTION_SALLES");
        permissions.add("GESTION_COURS");
    }

    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }

    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }

    public List<String> getPermissions() { return permissions; }
    public void setPermissions(List<String> permissions) { this.permissions = permissions; }

    public int getNiveauAcces() { return niveauAcces; }
    public void setNiveauAcces(int niveauAcces) { this.niveauAcces = niveauAcces; }

    public boolean aPermission(String permission) { return permissions.contains(permission); }

    @Override
    public String getRole() { return "ADMIN"; }
}