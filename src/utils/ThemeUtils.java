package utils;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class ThemeUtils {
    
    // Couleurs de l'application
    public static final Color BLEU_FONCE = new Color(0x07, 0x17, 0x39);
    public static final Color MARRON = new Color(0x5C, 0x3B, 0x2A);
    public static final Color BEIGE = new Color(0xA6, 0x88, 0x68);
    public static final Color GRIS = new Color(0xA4, 0xB5, 0xC4);
    public static final Color BEIGE_CLAIR = new Color(0xE3, 0xC3, 0x9D);
    
    public static void applyTheme() {
        UIManager.put("Button.background", new ColorUIResource(BLEU_FONCE));
        UIManager.put("Button.foreground", new ColorUIResource(Color.WHITE));
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 12));
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 12));
        UIManager.put("Label.foreground", new ColorUIResource(Color.BLACK));
        
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 12));
        UIManager.put("PasswordField.font", new Font("Segoe UI", Font.PLAIN, 12));
        UIManager.put("TextArea.font", new Font("Segoe UI", Font.PLAIN, 12));
        
        UIManager.put("Table.font", new Font("Segoe UI", Font.PLAIN, 12));
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 12));
        UIManager.put("TableHeader.background", new ColorUIResource(new Color(240, 240, 240)));
        
        UIManager.put("TabbedPane.font", new Font("Segoe UI", Font.PLAIN, 12));
        UIManager.put("TitledBorder.font", new Font("Segoe UI", Font.BOLD, 12));
        
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 12));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.BOLD, 12));
    }
    
    public static void applyThemeToFrame(JFrame frame) {
        frame.getContentPane().setBackground(Color.WHITE);
    }
    
    public static JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}