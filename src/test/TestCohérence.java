package test;

import model.*;

public class TestCohérence {
    public static void main(String[] args) {
        // ✅ CORRIGÉ: "Enseignant" au lieu de "t"
        Enseignant prof = new Enseignant("Dr. Martin", "martin@univ.fr", "pass", "Informatique", 762442516);
        prof.setGrade("Professeur");
        prof.setTelephone(123456789);
        
        System.out.println("Enseignant: " + prof.getNom() + " - " + prof.getGrade());
        
        // Test Salle
        Salle salle = new Salle("A101", 150, "A");
        salle.setType("Amphi");
        salle.setDisponible(true);
         
        System.out.println("Salle: " + salle.getNumero() + " - " + salle.getCapacite() + " places");
        
        // Test Cours - Ajoutez le constructeur par défaut dans Cours.java
        Cours cours = new Cours();  // ← Assurez-vous d'avoir ce constructeur
        cours.setMatiere("Programmation Java");
        cours.setClasse("L3 Info");
        cours.setGroupe("Groupe A");
        cours.setEnseignant(prof);
        cours.setSalle(salle);
        cours.setJour("Lundi");
        cours.setHeureDebut("08:00");
        cours.setDuree(2);
        cours.setStatut("Confirmé");
        
        System.out.println("Cours: " + cours.getMatiere() + " - " + cours.getClasse());
        System.out.println("  Enseignant: " + cours.getEnseignant().getNom());
        System.out.println("  Salle: " + cours.getSalle().getNumero());
        
        // Test Etudiant
        Etudiant etu = new Etudiant("Jean", "jean@univ.fr", "pass", "2024001", "L3", "Info");
        etu.setGroupe("A");
        
        System.out.println("\nÉtudiant: " + etu.getNom());
        System.out.println("  Numéro: " + etu.getNumeroEtudiant());
        System.out.println("  Classe: " + etu.getFiliere() + " " + etu.getNiveau() + " - Groupe " + etu.getGroupe());
        
        // Test Administrateur
        Administrateur admin = new Administrateur("Admin", "admin@univ.fr", "pass", "Direction", "Chef");
        System.out.println("\nAdmin: " + admin.getNom() + " - " + admin.getPoste());
        System.out.println("  Permissions: " + admin.getPermissions());
    }
}