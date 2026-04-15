package controller;

import model.*;
import service.*;
import java.util.List;

public class EtudiantController {
    private CoursService coursService = new CoursService();
    private Etudiant etudiant;

    public EtudiantController(Etudiant e) { this.etudiant = e; }
    public List<Cours> getEmploiDuTemps() { return coursService.getCoursByNiveau(etudiant.getNiveau()); }
}