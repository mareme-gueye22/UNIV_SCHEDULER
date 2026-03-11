package model;

public class Salle {
    private int id;
    private String numero;
    private int capacite;
    private String batiment;
    private String type; // TD, TP, Amphi
    private boolean disponible;
    
    // Constructeurs
    public Salle() {}
    
    public Salle(String numero, int capacite, String batiment) {
        this.numero = numero;
        this.capacite = capacite;
        this.batiment = batiment;
        this.disponible = true;
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public int getCapacite() {
        return capacite;
    }
    
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    
    public String getBatiment() {
        return batiment;
    }
    
    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    @Override
    public String toString() {
        return "Salle{" +
                "numero='" + numero + '\'' +
                ", capacite=" + capacite +
                ", batiment='" + batiment + '\'' +
                '}';
    }
}