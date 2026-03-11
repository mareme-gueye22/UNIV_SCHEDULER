package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginInterface extends JFrame {
    private static final long serialVersionUID = 1L; // Pour éviter l'avertissement de sérialisation
    
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    
    public LoginInterface() {
        initUI();
    }
    
    @SuppressWarnings("unused")
	private void initUI() {
        setTitle("Cahier de texte numérique - Connexion");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // --- Panel principal avec fond personnalisé ---
        JPanel mainPanel = new JPanel() {
            private static final long serialVersionUID = 1L; // Pour éviter l'avertissement
            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                // Dégradé de fond
                Color darkBlue = new Color(8, 15, 30);
                Color midBlue = new Color(25, 60, 110);
                GradientPaint gradient = new GradientPaint(0, 0, darkBlue, w * 0.7f, h * 0.3f, midBlue);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, w, h);

                // Lignes décoratives
                g2d.setColor(new Color(90, 180, 255, 40));
                g2d.setStroke(new BasicStroke(1.5f));

                int spacing = 40;
                for (int i = -h; i < w + h; i += spacing) {
                    g2d.drawLine(i, 0, i + h, h);
                }

                // Cercles décoratifs
                g2d.setColor(new Color(70, 150, 255, 60));
                g2d.fillOval(-50, -30, 200, 200);
                g2d.setColor(new Color(120, 200, 255, 50));
                g2d.fillOval(w - 180, h - 150, 250, 250);

                g2d.dispose();
            }
        };
        
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Panel de contenu blanc avec transparence
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
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
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
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 10, 5, 10);
        JLabel userLabel = new JLabel("👤 Utilisateur");
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
        JLabel passLabel = new JLabel("🔒 Mot de passe");
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
        loginButton = new JButton("🔑 Se connecter");
        loginButton.setPreferredSize(new Dimension(300, 45));
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setForeground(Color.black);
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Utilisation d'un seul MouseAdapter pour éviter les avertissements
        MouseAdapter buttonHover = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(60, 120, 170));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(70, 130, 180));
            }
        };
        loginButton.addMouseListener(buttonHover);
        
        loginButton.addActionListener(this::handleLogin);
        contentPanel.add(loginButton, gbc);
        
        // Label pour la section "Connexion rapide"
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 10, 10, 10);
        JLabel quickLoginLabel = new JLabel("Connexion rapide :");
        quickLoginLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        quickLoginLabel.setForeground(new Color(102, 102, 102));
        contentPanel.add(quickLoginLabel, gbc);
        
        // Panel pour les boutons de rôle
        gbc.gridy = 9;
        JPanel rolePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        rolePanel.setBackground(new Color(255, 255, 255, 0));
        rolePanel.setOpaque(false);
        
        // Rôles avec émojis
        String[][] roles = {
            {"👔 Chef département", "chef.departement", "chef123"},
            {"👨‍🏫 Enseignant", "enseignant", "prof123"},
            {"📋 Responsable classe", "responsable.classe", "resp123"},
            {"👨‍🎓 Étudiant", "etudiant", "etudiant123"}
        };
        
        Color[] colors = {
            new Color(0x07, 0x17, 0x39),  // #071739
            new Color(40, 167, 69),        // Vert
            new Color(220, 53, 69),        // Rouge
            new Color(0x5C, 0x3B, 0x2A)    // #5C3B2A
        };
        
        for (int i = 0; i < roles.length; i++) {
            JButton roleButton = new JButton(roles[i][0]);
            roleButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            roleButton.setForeground(colors[i]);
            roleButton.setBackground(Color.WHITE);
            roleButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            roleButton.setFocusPainted(false);
            roleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            final String username = roles[i][1];
            final String password = roles[i][2];
            
            roleButton.addActionListener(e -> {
                userField.setText(username);
                passwordField.setText(password);
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
        
        // Ajout du contentPanel au mainPanel
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(contentPanel, mainGbc);
        
        add(mainPanel);
        getRootPane().setDefaultButton(loginButton);
    }
    
    private void handleLogin(ActionEvent e) {
        String username = userField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("❌ Veuillez remplir tous les champs");
            return;
        }
        
        messageLabel.setForeground(new Color(34, 139, 34));
        messageLabel.setText("✅ Connexion réussie!");
        
        // Redirection après 1 seconde
        @SuppressWarnings("unused")
		Timer timer = new Timer(1000, ev -> {
            dispose();
            openDashboard(username);
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private void openDashboard(String username) {
        JFrame dashboard;
        
        if (username.contains("chef")) {
            dashboard = new AdminDashboard(); // Chef département = Admin
        } else if (username.contains("enseignant")) {
            // Créer un objet Enseignant (à adapter selon votre constructeur)
            // dashboard = new EnseignantDashboard(new Enseignant(...));
            dashboard = new JFrame("Dashboard Enseignant - À implémenter");
            dashboard.setSize(800, 600);
        } else if (username.contains("responsable")) {
            dashboard = new GestionnaireDashboard(); // Responsable classe = Gestionnaire
        } else if (username.contains("etudiant")) {
            // dashboard = new EtudiantDashboard(new Etudiant(...));
            dashboard = new JFrame("Dashboard Étudiant - À implémenter");
            dashboard.setSize(800, 600);
        } else {
            dashboard = new JFrame("Tableau de bord");
            dashboard.setSize(800, 600);
        }
        
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboard.setLocationRelativeTo(null);
        dashboard.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Silencieux - pas d'affichage d'exception
            }
            new LoginInterface().setVisible(true);
        });
    }
}
//private void handleLogin() {
//    String email = userField.getText().trim();
//    String password = new String(passwordField.getPassword());
//    
//    AuthService authService = new AuthService();
//    Utilisateur user = authService.login(email, password);
//    
//    if (user != null) {
//        messageLabel.setForeground(Color.GREEN);
//        messageLabel.setText("✅ Connexion réussie!");
//        
//        Timer timer = new Timer(1000, e -> {
//            dispose();
//            if (user instanceof Enseignant) {
//                new EnseignantDashboard((Enseignant) user).setVisible(true);
//            } else if (user instanceof Etudiant) {
//                new EtudiantDashboard((Etudiant) user).setVisible(true);
//            } else if (user instanceof Administrateur) {
//                new AdminDashboard().setVisible(true);
//            } else if (user instanceof Gestionnaire) {
//                new GestionnaireDashboard().setVisible(true);
//            }
//        });
//        timer.setRepeats(false);
//        timer.start();
//    } else {
//        messageLabel.setForeground(Color.RED);
//        messageLabel.setText("❌ Email ou mot de passe incorrect");
//    }
//}