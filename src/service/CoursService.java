package service;

import dao.CoursDAO;
import model.Cours;
import java.util.List;

public class CoursService {
    private CoursDAO dao;
    public CoursService() { try { dao = new CoursDAO(); } catch(Exception e) { e.printStackTrace(); } }

    public boolean addCours(Cours c) { return dao.ajouterCours(c); }
    public List<Cours> getAllCours() { return dao.findAll(); }
    public List<Cours> getCoursByEnseignant(int id) { return dao.findByEnseignant(id); }
    public List<Cours> getCoursByNiveau(String niveau) { return dao.findByNiveau(niveau); }
    public boolean deleteCours(int id) { return dao.delete(id); }
    public boolean hasConflict(Cours c) {
        List<Cours> cours = dao.findByEnseignant(c.getEnseignantId());

        for (Cours existing : cours) {
            if (existing.getJour().equals(c.getJour()) &&
                existing.getHeureDebut().equals(c.getHeureDebut())) {
                return true;
            }
        }

        return false;
    }
}