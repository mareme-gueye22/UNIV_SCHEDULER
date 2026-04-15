-- Supprimer toutes les tables (dans l'ordre inverse des dépendances)
DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS cours;
DROP TABLE IF EXISTS equipements;
DROP TABLE IF EXISTS salles;
DROP TABLE IF EXISTS batiments;
DROP TABLE IF EXISTS administrateurs;
DROP TABLE IF EXISTS etudiants;
DROP TABLE IF EXISTS enseignants;
DROP TABLE IF EXISTS utilisateurs;
DROP DATABASE IF EXISTS univ_scheduler;