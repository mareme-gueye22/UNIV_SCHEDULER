package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import model.Enseignant;
import model.Etudiant;
import model.Utilisateur;
import service.AuthService;


public class LoginInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new LoginInterface().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginInterface() {
        initUI();
    }
    
    
    @SuppressWarnings("unused")
	private void initUI() {
        setTitle("UNIV-SCHEDULER - Connexion");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Panel principal avec fond personnalisé
        JPanel mainPanel = new JPanel() {
            private static final long serialVersionUID = 1L;
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                Color darkBlue = new Color(8, 15, 30);
                Color midBlue = new Color(25, 60, 110);
                GradientPaint gradient = new GradientPaint(0, 0, darkBlue, w * 0.7f, h * 0.3f, midBlue);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, w, h);
                g2d.setColor(new Color(90, 180, 255, 40));
                g2d.setStroke(new BasicStroke(1.5f));
                int spacing = 40;
                for (int i = -h; i < w + h; i += spacing) {
                    g2d.drawLine(i, 0, i + h, h);
                }
                g2d.setColor(new Color(70, 150, 255, 60));
                g2d.fillOval(-50, -30, 200, 200);
                g2d.setColor(new Color(120, 200, 255, 50));
                g2d.fillOval(w - 180, h - 150, 250, 250);
                g2d.dispose();
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255, 220));
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 255, 255, 150), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Emoji livre
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel bookEmoji = new JLabel("📚");
        bookEmoji.setFont(new Font("Segoe UI", Font.PLAIN, 60));
        bookEmoji.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(bookEmoji, gbc);
        
        // Titre
        gbc.gridy = 1;
        JLabel titleLabel = new JLabel("CAHIER DE TEXTE NUMÉRIQUE");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(titleLabel, gbc);
        
        // Sous-titre
        gbc.gridy = 2;
        JLabel subtitleLabel = new JLabel("Plateforme de gestion pédagogique");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(102, 102, 102));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(subtitleLabel, gbc);
        
        // Champ Utilisateur
        gbc.gridy = 3; gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 10, 5, 10);
        JLabel userLabel = new JLabel(" Utilisateur");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        userLabel.setForeground(new Color(102, 102, 102));
        contentPanel.add(userLabel, gbc);
        
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 10, 15, 10);
        userField = new JTextField(20);
        userField.setPreferredSize(new Dimension(300, 40));
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        contentPanel.add(userField, gbc);
        
        // Champ Mot de passe
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 10, 5, 10);
        JLabel passLabel = new JLabel("Mot de passe");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        passLabel.setForeground(new Color(102, 102, 102));
        contentPanel.add(passLabel, gbc);
        
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 10, 20, 10);
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(300, 40));
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        contentPanel.add(passwordField, gbc);
        
        // Bouton Se connecter
        gbc.gridy = 7;
        gbc.insets = new Insets(10, 10, 20, 10);
        loginButton = new JButton("Se connecter");
        loginButton.setPreferredSize(new Dimension(300, 45));
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setForeground(Color.BLACK);
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(60, 120, 170));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(70, 130, 180));
            }
        });
        
        // Action du bouton de connexion (corrigée)
        loginButton.addActionListener(e -> handleLogin());
        contentPanel.add(loginButton, gbc);
        
        // Connexion rapide
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 10, 10, 10);
        JLabel quickLoginLabel = new JLabel("Connexion rapide :");
        quickLoginLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        quickLoginLabel.setForeground(new Color(102, 102, 102));
        contentPanel.add(quickLoginLabel, gbc);
        
        gbc.gridy = 9;
        JPanel rolePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        rolePanel.setBackground(new Color(255, 255, 255, 0));
        rolePanel.setOpaque(false);
        
        String[][] roles = {
            {" Administrateur", "admin@univ.fr", "admin123"},
            {"‍ Enseignant", "martin@univ.fr", "pass"},
            {" Gestionnaire", "gestionnaire@univ.fr", "gestion123"},
            {"‍ Étudiant", "jean.dupont@etudiant.fr", "etudiant123"}
        };
        
        Color[] colors = {
            new Color(0x07, 0x17, 0x39),
            new Color(40, 167, 69),
            new Color(220, 53, 69),
            new Color(0x5C, 0x3B, 0x2A)
        };
        
        for (int i = 0; i < roles.length; i++) {
            JButton roleButton = new JButton(roles[i][0]);
            roleButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            roleButton.setForeground(colors[i]);
            roleButton.setBackground(Color.WHITE);
            roleButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            roleButton.setFocusPainted(false);
            roleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            final String email = roles[i][1];
            final String pwd = roles[i][2];
            roleButton.addActionListener(ev -> {
                userField.setText(email);
                passwordField.setText(pwd);
                messageLabel.setText(" ");
            });
            rolePanel.add(roleButton);
        }
        contentPanel.add(rolePanel, gbc);
        
        // Message d'erreur/succès
        gbc.gridy = 10;
        messageLabel = new JLabel(" ");
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        messageLabel.setForeground(Color.RED);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(messageLabel, gbc);
        
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(contentPanel, mainGbc);
        add(mainPanel);
        getRootPane().setDefaultButton(loginButton);
    }
    
    @SuppressWarnings("unused")
    private void handleLogin() {
        String email = userField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        System.out.println("=== TEST CONNEXION ===");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        
        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Champs vides");
            messageLabel.setText("❌ Veuillez remplir tous les champs");
            return;
        }
        
        AuthService authService = new AuthService();
        Utilisateur utilisateur = authService.login(email, password);
        
        System.out.println("Utilisateur trouvé: " + (utilisateur != null));
        
        if (utilisateur != null) {
            System.out.println("Rôle: " + utilisateur.getRole());
            System.out.println("Tentative d'ouverture du dashboard...");
            dispose();
            redirectToDashboard(utilisateur);
        } else {
            System.out.println("Échec authentification");
            messageLabel.setText("❌ Email ou mot de passe incorrect");
        }
    }
    
    private void redirectToDashboard(Utilisateur u) {
        if (u == null) return;
        switch (u.getRole()) {
            case "ADMIN":
            case "Administrateur":
                new AdminDashboard().setVisible(true);
                break;
            case "GESTIONNAIRE":
                new GestionnaireDashboard().setVisible(true);
                break;
            case "ENSEIGNANT":
                if (u instanceof Enseignant) {
                    new EnseignantDashboard((Enseignant) u).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur de casting Enseignant");
                }
                break;
            case "ETUDIANT":
                if (u instanceof Etudiant) {
                    new EtudiantDashboard((Etudiant) u).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur de casting Étudiant");
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Rôle non reconnu : " + u.getRole());
        }
    }
}