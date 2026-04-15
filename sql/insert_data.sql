USE univ_scheduler;

-- Insertion des utilisateurs
INSERT INTO utilisateurs (nom, email, mot_de_passe, role) VALUES
('Admin Principal', 'admin@univ.fr', 'admin123', 'Administrateur'),
('Martin Dupuis', 'martin@univ.fr', 'gestion123', 'Gestionnaire'),
('Dr. Bernard', 'bernard@univ.fr', 'prof123', 'Enseignant'),
('Jean Dupont', 'jean.dupont@etudiant.univ.fr', 'etudiant123', 'Etudiant');

-- Insertion des bâtiments
INSERT INTO batiments (nom, adresse, nombre_etages) VALUES
('Bâtiment A', 'Campus Nord', 3),
('Bâtiment B', 'Campus Sud', 2),
('Bâtiment C', 'Campus Est', 1);

-- Insertion des salles
INSERT INTO salles (numero, capacite, type, batiment_id, etage) VALUES
('A101', 150, 'Amphi', 1, 1),
('A105', 40, 'TD', 1, 1),
('B201', 30, 'TP', 2, 2);