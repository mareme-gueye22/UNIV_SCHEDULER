package controller;

import model.*;
import service.*;
import java.util.List;

public class EnseignantController {
    private CoursService coursService = new CoursService();
    private Enseignant enseignant;

    public EnseignantController(Enseignant e) { this.enseignant = e; }
    public List<Cours> getMesCours() { return coursService.getCoursByEnseignant(enseignant.getId()); }
    public boolean planifierCours(Cours c) {
        if (coursService.hasConflict(c)) return false;
        return coursService.addCours(c);
    }
}