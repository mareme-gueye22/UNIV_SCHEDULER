package dao;

import utils.DatabaseConfig;  // ⚠️ Attention: utils est dans quel package ?
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
        String[] createTableQueries = {};

        try (Statement stmt = connection.createStatement()) {
            for (String query : createTableQueries) {
                stmt.execute(query);
            }
            System.out.println("✅ Tables vérifiées/créées avec succès");
        }
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔌 Connexion fermée");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}