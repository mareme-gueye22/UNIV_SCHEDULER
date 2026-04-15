package dao;

import utils.DatabaseConfig;
import java.sql.*;

public class DatabaseConnection {
    private static Connection connection = null;

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(DatabaseConfig.getDriver());
                connection = DriverManager.getConnection(
                    DatabaseConfig.getUrl(),
                    DatabaseConfig.getUser(),
                    DatabaseConfig.getPassword()
                );
                createTablesIfNotExist();
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver JDBC non trouvé", e);
            }
        }
        return connection;
    }

    private static void createTablesIfNotExist() throws SQLException {
        String[] queries = {
            "CREATE TABLE IF NOT EXISTS utilisateurs (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, nom VARCHAR(100) NOT NULL, email VARCHAR(100) UNIQUE NOT NULL, " +
            "mot_de_passe VARCHAR(255) NOT NULL, salt VARCHAR(255) NOT NULL, role VARCHAR(50) NOT NULL, " +
            "statut VARCHAR(20) DEFAULT 'ACTIF', date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "derniere_connexion TIMESTAMP NULL)",

            "CREATE TABLE IF NOT EXISTS enseignants (" +
            "id INT PRIMARY KEY, departement VARCHAR(100), grade VARCHAR(50), telephone INT, " +
            "FOREIGN KEY (id) REFERENCES utilisateurs(id) ON DELETE CASCADE)",

            "CREATE TABLE IF NOT EXISTS etudiants (" +
            "id INT PRIMARY KEY, numero_etudiant VARCHAR(20) UNIQUE NOT NULL, filiere VARCHAR(100), " +
            "niveau VARCHAR(50), groupe VARCHAR(50), annee_entree INT, moyenne DECIMAL(4,2) DEFAULT 0.0, telephone INT, " +
            "FOREIGN KEY (id) REFERENCES utilisateurs(id) ON DELETE CASCADE)",

            "CREATE TABLE IF NOT EXISTS salles (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, code VARCHAR(20) UNIQUE NOT NULL, nom VARCHAR(100), " +
            "batiment VARCHAR(50), etage INT, capacite INT NOT NULL, type VARCHAR(50), equipements TEXT, " +
            "statut VARCHAR(20) DEFAULT 'DISPONIBLE')",

            "CREATE TABLE IF NOT EXISTS cours (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, code VARCHAR(20) UNIQUE NOT NULL, intitule VARCHAR(200) NOT NULL, " +
            "description TEXT, credits INT, heures INT, niveau VARCHAR(50), semestre VARCHAR(20), enseignant_id INT, " +
            "FOREIGN KEY (enseignant_id) REFERENCES enseignants(id))"
        };
        try (Statement stmt = connection.createStatement()) {
            for (String q : queries) stmt.execute(q);
        }
    }

    public static void closeConnection() {
        try { if (connection != null && !connection.isClosed()) connection.close(); }
        catch (SQLException e) { e.printStackTrace(); }
    }
}