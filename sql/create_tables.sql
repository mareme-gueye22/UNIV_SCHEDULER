-- ===========================================
-- Script de création des tables
-- UNIV-SCHEDULER - Version compatible avec les classes Java
-- ===========================================

CREATE DATABASE IF NOT EXISTS univ_scheduler;
USE univ_scheduler;

-- 1. Table utilisateurs (classe parente)
CREATE TABLE utilisateurs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,              -- 'ADMIN', 'GESTIONNAIRE', 'ENSEIGNANT', 'ETUDIANT'
    statut VARCHAR(20) DEFAULT 'ACTIF',
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    derniere_connexion TIMESTAMP NULL
);

-- 2. Table enseignants
CREATE TABLE enseignants (
    id INT PRIMARY KEY,
    departement VARCHAR(100),
    grade VARCHAR(50),
    telephone INT,
    bureau VARCHAR(50),
    specialite VARCHAR(100),
    FOREIGN KEY (id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- 3. Table etudiants
CREATE TABLE etudiants (
    id INT PRIMARY KEY,
    numero_etudiant VARCHAR(20) UNIQUE NOT NULL,
    filiere VARCHAR(100),
    niveau VARCHAR(50),
    groupe VARCHAR(50),
    annee_entree INT,
    moyenne DECIMAL(4,2) DEFAULT 0.0,
    telephone INT,
    FOREIGN KEY (id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- 4. Table administrateurs
CREATE TABLE administrateurs (
    id INT PRIMARY KEY,
    departement VARCHAR(100),
    poste VARCHAR(100),
    permissions TEXT,                       -- stocke une liste séparée par des virgules
    niveau_acces INT DEFAULT 3,
    FOREIGN KEY (id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- 5. Table gestionnaires
CREATE TABLE gestionnaires (
    id INT PRIMARY KEY,
    departement VARCHAR(100),
    service VARCHAR(100),
    zone_responsabilite VARCHAR(100),
    FOREIGN KEY (id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- 6. Table salles (sans table batiments dédiée)
CREATE TABLE salles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20) UNIQUE NOT NULL,
    nom VARCHAR(100),
    batiment VARCHAR(50),
    etage INT,
    capacite INT NOT NULL,
    type VARCHAR(50),
    equipements TEXT,
    statut VARCHAR(20) DEFAULT 'DISPONIBLE'
);

-- 7. Table cours
CREATE TABLE cours (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20) UNIQUE NOT NULL,
    intitule VARCHAR(200) NOT NULL,
    description TEXT,
    credits INT,
    heures INT,
    niveau VARCHAR(50),
    semestre VARCHAR(20),
    enseignant_id INT,
    FOREIGN KEY (enseignant_id) REFERENCES enseignants(id) ON DELETE SET NULL
);

-- 8. Table réservations
CREATE TABLE reservations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cours_id INT,
    salle_id INT NOT NULL,
    enseignant_id INT,
    etudiant_id INT,
    date_reservation DATE NOT NULL,
    heure_debut TIME NOT NULL,
    heure_fin TIME NOT NULL,
    statut VARCHAR(20) DEFAULT 'EN_ATTENTE',
    motif TEXT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cours_id) REFERENCES cours(id) ON DELETE SET NULL,
    FOREIGN KEY (salle_id) REFERENCES salles(id) ON DELETE CASCADE,
    FOREIGN KEY (enseignant_id) REFERENCES enseignants(id) ON DELETE SET NULL,
    FOREIGN KEY (etudiant_id) REFERENCES etudiants(id) ON DELETE SET NULL
);

-- Index pour les performances
CREATE INDEX idx_utilisateurs_email ON utilisateurs(email);
CREATE INDEX idx_utilisateurs_role ON utilisateurs(role);
CREATE INDEX idx_etudiants_numero ON etudiants(numero_etudiant);
CREATE INDEX idx_cours_enseignant ON cours(enseignant_id);
CREATE INDEX idx_reservations_date ON reservations(date_reservation);
CREATE INDEX idx_reservations_salle ON reservations(salle_id);