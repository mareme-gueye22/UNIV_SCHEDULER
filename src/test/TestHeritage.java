package test;

import model.*;

public class TestHeritage {
    public static void main(String[] args) {
        // Test Etudiant
        Etudiant etudiant = new Etudiant(
            "Jean Dupont", 
            "jean.dupont@universite.fr", 
            "pass123",
            "2024001", 
            "L3", 
            "Informatique"
        );
        etudiant.setGroupe("Groupe A");
        
        System.out.println(etudiant);
        System.out.println("Rôle: " + etudiant.getRole());
        System.out.println(etudiant.getEmploiDuTemps());
        
        // Test Administrateur
        Administrateur admin = new Administrateur(
            "Marie Martin",
            "marie.martin@universite.fr",
            "admin123",
            "Département Informatique",
            "Chef de département"
        );
        
        System.out.println("\n" + admin);
        System.out.println("Rôle: " + admin.getRole());
        System.out.println("A la permission GESTION_SALLES? " + 
                          admin.aPermission("GESTION_SALLES"));
        
        // Test polymorphisme
        Utilisateur user1 = etudiant;
        Utilisateur user2 = admin;
        
        System.out.println("\nPolymorphisme:");
        System.out.println("user1 est un: " + user1.getRole());
        System.out.println("user2 est un: " + user2.getRole());
    }
}