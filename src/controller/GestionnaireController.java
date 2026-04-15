package controller;

import model.*;
import service.*;
import java.util.List;

public class GestionnaireController {
    private CoursService coursService = new CoursService();
    private SalleService salleService = new SalleService();
    public List<Cours> getAllCours() { return coursService.getAllCours(); }
    public boolean ajouterCours(Cours c) { return coursService.addCours(c); }
    public List<Salle> getSallesDisponibles() { return salleService.getSallesDisponibles(); }
}