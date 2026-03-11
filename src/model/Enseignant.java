package model;

public class Enseignant extends Utilisateur {
    private String departement;
    private String grade;
    private int telephone;
    
    public Enseignant() {
        super();
    }
    
    public Enseignant(String nom, String email, String motDePasse, String departement, int telephone) {
        super(nom, email, motDePasse);
        this.departement = departement;
        this.telephone = telephone;
        this.grade = "Maître de conférences";
    }
   
    
    public String getDepartement() {
        return departement;
    }
    
    public void setDepartement(String departement) {
        this.departement = departement;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    @Override
    public String getRole() {
        return "Enseignant";
    }

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone1(int telephone) {
		this.telephone = telephone;
		
	}

	public void setTelephone(int telephone) {
		// TODO Auto-generated method stub
		
	}
}