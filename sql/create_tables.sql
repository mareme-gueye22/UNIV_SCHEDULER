-- ===========================================
-- Script de création des tables
-- UNIV-SCHEDULER - Gestion des salles
-- ===========================================

CREATE DATABASE IF NOT EXISTS univ_scheduler;
USE univ_scheduler;

-- Table des utilisateurs (classe parente)
CREATE TABLE utilisateurs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    role ENUM('Administrateur', 'Gestionnaire', 'Enseignant', 'Etudiant') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table des enseignants (spécifique)
CREATE TABLE enseignants (
    id INT PRIMARY KEY,
    departement VARCHAR(100),
    grade VARCHAR(50),
    telephone INT (10),
    FOREIGN KEY (id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- Table des étudiants
CREATE TABLE etudiants (
    id INT PRIMARY KEY,
    numero_etudiant VARCHAR(20) UNIQUE NOT NULL,
    niveau VARCHAR(10),
    filiere VARCHAR(100),
    groupe VARCHAR(20),
    FOREIGN KEY (id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

	ALTER TABLE etudiants ADD telephone INT;
-- Table des administrateurs
CREATE TABLE administrateurs (
    id INT PRIMARY KEY,
    poste VARCHAR(100),
    departement VARCHAR(100),
    niveau_acces INT DEFAULT 3,
    FOREIGN KEY (id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- Table des bâtiments
CREATE TABLE batiments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    adresse VARCHAR(255),
    nombre_etages INT DEFAULT 1
);

-- Table des salles
CREATE TABLE salles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(20) NOT NULL,
    capacite INT NOT NULL,
    type VARCHAR(50),
    batiment_id INT,
    etage INT DEFAULT 0,
    disponible BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (batiment_id) REFERENCES batiments(id) ON DELETE SET NULL
);

-- Table des équipements
CREATE TABLE equipements (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    salle_id INT,
    FOREIGN KEY (salle_id) REFERENCES salles(id) ON DELETE CASCADE
);

-- Table des cours
CREATE TABLE cours (
    id INT PRIMARY KEY AUTO_INCREMENT,
    matiere VARCHAR(100) NOT NULL,
    enseignant_id INT,
    salle_id INT,
    classe VARCHAR(50) NOT NULL,
    groupe VARCHAR(50),
    jour VARCHAR(20),
    heure_debut TIME,
    duree INT,
    description TEXT,
    statut VARCHAR(20) DEFAULT 'Planifié',
    FOREIGN KEY (enseignant_id) REFERENCES enseignants(id) ON DELETE SET NULL,
    FOREIGN KEY (salle_id) REFERENCES salles(id) ON DELETE SET NULL
);

-- Table des réservations ponctuelles
CREATE TABLE reservations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    salle_id INT NOT NULL,
    utilisateur_id INT NOT NULL,
    motif VARCHAR(255),
    date_reservation DATE,
    heure_debut TIME,
    heure_fin TIME,
    statut VARCHAR(20) DEFAULT 'En attente',
    FOREIGN KEY (salle_id) REFERENCES salles(id) ON DELETE CASCADE,
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- Index pour améliorer les performances
CREATE INDEX idx_cours_jour ON cours(jour);
CREATE INDEX idx_cours_enseignant ON cours(enseignant_id);
CREATE INDEX idx_cours_salle ON cours(salle_id);
CREATE INDEX idx_reservations_date ON reservations(date_reservation);