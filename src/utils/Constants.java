package utils;

import java.awt.Color;

public class Constants {
    
    // Couleurs
    public static final Color BLEU_FONCE = new Color(0x07, 0x17, 0x39);
    public static final Color MARRON = new Color(0x5C, 0x3B, 0x2A);
    public static final Color BEIGE = new Color(0xA6, 0x88, 0x68);
    public static final Color GRIS = new Color(0xA4, 0xB5, 0xC4);
    public static final Color BEIGE_CLAIR = new Color(0xE3, 0xC3, 0x9D);
    
    // Rôles utilisateur
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_GESTIONNAIRE = "GESTIONNAIRE";
    public static final String ROLE_ENSEIGNANT = "ENSEIGNANT";
    public static final String ROLE_ETUDIANT = "ETUDIANT";
    
    // Statuts
    public static final String STATUT_ACTIF = "ACTIF";
    public static final String STATUT_INACTIF = "INACTIF";
    public static final String STATUT_SUSPENDU = "SUSPENDU";
    
    // Statuts de réservation
    public static final String RESERVATION_CONFIRMEE = "CONFIRMEE";
    public static final String RESERVATION_EN_ATTENTE = "EN_ATTENTE";
    public static final String RESERVATION_ANNULEE = "ANNULEE";
    public static final String RESERVATION_TERMINEE = "TERMINEE";
    
    // Statuts des salles
    public static final String SALLE_DISPONIBLE = "DISPONIBLE";
    public static final String SALLE_OCCUPEE = "OCCUPEE";
    public static final String SALLE_MAINTENANCE = "MAINTENANCE";
    
    // Types de salles
    public static final String TYPE_AMPHI = "Amphi";
    public static final String TYPE_TD = "TD";
    public static final String TYPE_TP = "TP";
    public static final String TYPE_REUNION = "Réunion";
    public static final String TYPE_LABO = "Laboratoire";
    public static final String TYPE_BIBLIOTHEQUE = "Bibliothèque";
    
    // Jours de la semaine
    public static final String[] JOURS = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    
    // Horaires types
    public static final String[] HORAIRES = {"08:00", "09:00", "10:00", "11:00", "12:00", 
                                              "14:00", "15:00", "16:00", "17:00", "18:00"};
}