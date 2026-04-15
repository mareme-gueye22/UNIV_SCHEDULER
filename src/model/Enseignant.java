package model;

public class Enseignant extends Utilisateur {
    private String departement;
    private String grade;
    private int telephone;

    public Enseignant() {
        this.role = "ENSEIGNANT";
    }

    public Enseignant(String nom, String email, String motDePasse, String departement, int telephone) {
        super(nom, email, motDePasse);
        this.departement = departement;
        this.telephone = telephone;
        this.grade = "Maître de conférences";
        this.role = "ENSEIGNANT";
    }

    // Getters / Setters
    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public int getTelephone() { return telephone; }
    public void setTelephone(int telephone) { this.telephone = telephone; }

    @Override
    public String getRole() { return "ENSEIGNANT"; }
}