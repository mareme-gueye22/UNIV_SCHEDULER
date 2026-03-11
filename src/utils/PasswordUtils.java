package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {
    
    // Générer un sel aléatoire
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    
    // Hacher le mot de passe avec SHA-256
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashed = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashed);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur de hachage", e);
        }
    }
    
    // Vérifier le mot de passe
    public static boolean verifyPassword(String password, String salt, String hashed) {
        String newHash = hashPassword(password, salt);
        return newHash.equals(hashed);
    }
    
    // Générer un mot de passe aléatoire
    public static String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

	public static boolean verifyPassword(String oldPassword, Object salt, String motDePasse) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean verifyPassword1(String oldPassword, Object salt, String motDePasse) {
		// TODO Auto-generated method stub
		return false;
	}
}
