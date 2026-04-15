package service;

import dao.SalleDAO;
import model.Salle;
import java.util.List;

public class SalleService {
    private SalleDAO dao = new SalleDAO();

    public boolean addSalle(Salle s) { return dao.ajouterSalle(s); }
    public List<Salle> getAllSalles() { return dao.findAll(); }
    public List<Salle> getSallesDisponibles() { return dao.findDisponibles(); }
    public boolean updateStatutSalle(int id, String statut) { return dao.updateStatut(id, statut); }
}