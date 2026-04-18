package utils;

public class PasswordUtils {
    
    public static String generateSalt() {
        return ""; // salt vide
    }
    
    public static String hashPassword(String password, String salt) {
        return password; // retourne le mot de passe en clair
    }
    
    public static boolean verifyPassword(String password, String salt, String hashed) {
        return password.equals(hashed); // comparaison directe
    }
    
    public static String generateRandomPassword(int length) {
        return "temp123"; // mot de passe temporaire
    }
}