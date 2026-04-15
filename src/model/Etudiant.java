package model;

public class Etudiant extends Utilisateur {
    private String numeroEtudiant;
    private String niveau;
    private String filiere;
    private String groupe;
    private int anneeEntree;
    private double moyenne;
    private int telephone;

    public Etudiant() {
        this.role = "ETUDIANT";
    }

    public Etudiant(String nom, String email, String motDePasse, String numeroEtudiant,
                    String niveau, String filiere, int anneeEntree, String groupe, int telephone) {
        super(nom, email, motDePasse);
        this.numeroEtudiant = numeroEtudiant;
        this.niveau = niveau;
        this.filiere = filiere;
        this.anneeEntree = anneeEntree;
        this.groupe = groupe;
        this.telephone = telephone;
        this.role = "ETUDIANT";
    }

    // Getters / Setters
    public String getNumeroEtudiant() { return numeroEtudiant; }
    public void setNumeroEtudiant(String numeroEtudiant) { this.numeroEtudiant = numeroEtudiant; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    public String getFiliere() { return filiere; }
    public void setFiliere(String filiere) { this.filiere = filiere; }

    public String getGroupe() { return groupe; }
    public void setGroupe(String groupe) { this.groupe = groupe; }

    public int getAnneeEntree() { return anneeEntree; }
    public void setAnneeEntree(int anneeEntree) { this.anneeEntree = anneeEntree; }

    public double getMoyenne() { return moyenne; }
    public void setMoyenne(double moyenne) { this.moyenne = moyenne; }

    public int getTelephone() { return telephone; }
    public void setTelephone(int telephone) { this.telephone = telephone; }

    @Override
    public String getRole() { return "ETUDIANT"; }

	public char[] getEmploiDuTemps() {
		// TODO Auto-generated method stub
		return null;
	}
}