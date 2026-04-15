USE univ_scheduler;

-- Insertion des utilisateurs (avec sel et mot de passe haché)
INSERT INTO utilisateurs (id, nom, email, mot_de_passe, salt, role, statut) VALUES
(1, 'Admin Principal', 'admin@univ.fr', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'salt123', 'ADMIN', 'ACTIF'),
(2, 'Dr. Martin', 'martin@univ.fr', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'salt123', 'ENSEIGNANT', 'ACTIF'),
(3, 'Jean Dupont', 'jean.dupont@etudiant.fr', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'salt123', 'ETUDIANT', 'ACTIF'),
(4, 'Gestionnaire', 'gestionnaire@univ.fr', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'salt123', 'GESTIONNAIRE', 'ACTIF');

-- Enseignants
INSERT INTO enseignants (id, departement, grade, telephone, bureau, specialite) VALUES
(2, 'Informatique', 'Professeur', 123456789, 'Bureau 201', 'Programmation Java');

-- Étudiants
INSERT INTO etudiants (id, numero_etudiant, filiere, niveau, groupe, annee_entree, moyenne, telephone) VALUES
(3, '2024001', 'Informatique', 'L3', 'A', 2024, 14.5, 987654321);

-- Administrateurs
INSERT INTO administrateurs (id, departement, poste, permissions, niveau_acces) VALUES
(1, 'Direction', 'Chef de projet', 'GESTION_UTILISATEURS,GESTION_SALLES,GESTION_COURS', 3);

-- Gestionnaires
INSERT INTO gestionnaires (id, departement, service, zone_responsabilite) VALUES
(4, 'Scolarité', 'Planification', 'Campus Nord');

-- Salles
INSERT INTO salles (code, nom, batiment, etage, capacite, type, equipements, statut) VALUES
('A101', 'Amphi A', 'A', 1, 150, 'Amphi', 'VidéoProjecteur, Climatisation, Tableau blanc', 'DISPONIBLE'),
('A105', 'Salle TD A105', 'A', 1, 40, 'TD', 'Tableau blanc, VidéoProjecteur', 'DISPONIBLE'),
('B201', 'Labo Info', 'B', 2, 30, 'TP', '24 PC, VidéoProjecteur', 'DISPONIBLE');

-- Cours
INSERT INTO cours (code, intitule, description, credits, heures, niveau, semestre, enseignant_id) VALUES
('JAVA-L3', 'Programmation Java avancée', 'Cours de Java orienté objet', 6, 36, 'L3', 'S1', 2),
('BD-L3', 'Bases de données', 'Introduction au SQL', 4, 24, 'L3', 'S1', 2);

-- Réservations (exemple)
INSERT INTO reservations (salle_id, enseignant_id, date_reservation, heure_debut, heure_fin, statut, motif) VALUES
(1, 2, CURDATE(), '08:00:00', '10:00:00', 'CONFIRMEE', 'Cours de Java');