package service;

import dao.EnseignantDAO;
import model.Enseignant;
import java.util.List;

public class EnseignantService {
    private EnseignantDAO dao;
    public EnseignantService() { try { dao = new EnseignantDAO(); } catch(Exception e) { e.printStackTrace(); } }
    public List<Enseignant> getAllEnseignants() { return dao.findAll(); }
    public Enseignant getEnseignantById(int id) { return dao.findById(id); }
}