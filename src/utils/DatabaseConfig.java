package utils;

import java.io.*;
import java.util.Properties;

public class DatabaseConfig {
    private static Properties props = new Properties();
    
    static {
        // Configuration par défaut pour XAMPP
        props.setProperty("db.url", "jdbc:mysql://localhost:3306/univ_scheduler?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        props.setProperty("db.user", "root");
        props.setProperty("db.password", "");
        props.setProperty("db.driver", "com.mysql.cj.jdbc.Driver");
        
        // Essayer de charger un fichier de config externe s'il existe
        try (InputStream input = new FileInputStream("config.properties")) {
            props.load(input);
        } catch (IOException e) {
            // Utiliser la config par défaut
            System.out.println("Utilisation de la configuration par défaut pour XAMPP");
        }
    }
    
    public static String getUrl() { return props.getProperty("db.url"); }
    public static String getUser() { return props.getProperty("db.user"); }
    public static String getPassword() { return props.getProperty("db.password"); }
    public static String getDriver() { return props.getProperty("db.driver"); }
    
    public static void testConnection() {
        try {
            Class.forName(getDriver());
            System.out.println("✅ Driver MySQL chargé avec succès");
            System.out.println("📊 URL: " + getUrl());
            System.out.println("👤 Utilisateur: " + getUser());
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver MySQL non trouvé. Vérifiez que mysql-connector-java est dans le classpath");
            e.printStackTrace();
        }
    }
}