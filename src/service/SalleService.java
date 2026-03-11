package service;

import dao.SalleDAO;
import model.Salle;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class SalleService {
    
    private SalleDAO salleDAO;
    
    public SalleService() {
        this.salleDAO = new SalleDAO();
    }
    
    /**
     * Ajouter une nouvelle salle
     */
    public boolean addSalle(Salle salle) {
        if (salle == null) return false;
        return salleDAO.ajouterSalle(salle);
    }
    
    /**
     * Récupérer toutes les salles
     */
    public List<Salle> getAllSalles() {
        return salleDAO.findAll();
    }
    
    /**
     * Récupérer les salles disponibles
     */
    public List<Salle> getSallesDisponibles() {
        return salleDAO.findDisponibles();
    }
    
    /**
     * Rechercher des salles par critères
     */
    public List<Salle> rechercherSalles(Integer capaciteMin, String type, String batiment) {
        return salleDAO.rechercher(capaciteMin, type, batiment);
    }
    
    /**
     * Vérifier la disponibilité d'une salle
     */
    public boolean isSalleDisponible(int salleId, String date, String heureDebut, String heureFin) {
        return salleDAO.verifierDisponibilite(salleId, date, heureDebut, heureFin);
    }
    
    /**
     * Mettre à jour le statut d'une salle
     */
    public boolean updateStatutSalle(int id, String statut) {
        return salleDAO.updateStatut(id, statut);
    }
}