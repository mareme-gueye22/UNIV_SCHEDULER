package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JTable tableUtilisateurs;
    private DefaultTableModel tableModelUtilisateurs;
    private JTable tableSalles;
    private DefaultTableModel tableModelSalles;
    
    public AdminDashboard() {
        initUI();
        chargerDonnees();
    }
    
    private void initUI() {
        setTitle("UNIV-SCHEDULER - Administrateur");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel menuPanel = createMenuPanel();
        add(menuPanel, BorderLayout.WEST);
        
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(createDashboardPanel(), "DASHBOARD");
        contentPanel.add(createUsersPanel(), "USERS");
        contentPanel.add(createBuildingsPanel(), "BUILDINGS");
        contentPanel.add(createRoomsPanel(), "ROOMS");
        contentPanel.add(createReportsPanel(), "REPORTS");
        contentPanel.add(createConfigPanel(), "CONFIG");
        add(contentPanel, BorderLayout.CENTER);
        
        cardLayout.show(contentPanel, "DASHBOARD");
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistiques globales"));
        statsPanel.add(createStatCard("🏢 Salles", "45", new Color(52, 152, 219)));
        statsPanel.add(createStatCard("👥 Utilisateurs", "128", new Color(46, 204, 113)));
        statsPanel.add(createStatCard("📚 Cours", "234", new Color(155, 89, 182)));
        statsPanel.add(createStatCard("📊 Taux d'occupation", "78%", new Color(230, 126, 34)));
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 0.4;
        panel.add(statsPanel, gbc);
        
        // Alertes
        JPanel alertsPanel = new JPanel(new BorderLayout());
        alertsPanel.setBackground(Color.WHITE);
        alertsPanel.setBorder(BorderFactory.createTitledBorder("Alertes"));
        JTextArea alertArea = new JTextArea(5, 30);
        alertArea.setEditable(false);
        alertArea.setText("⚠️ 3 conflits de réservation\n🔧 2 salles en maintenance\n✅ 5 demandes en attente");
        alertsPanel.add(new JScrollPane(alertArea), BorderLayout.CENTER);
        
        gbc.gridy = 1; gbc.weighty = 0.3;
        panel.add(alertsPanel, gbc);
        
        return panel;
    }
    
    @SuppressWarnings("unused")
	private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(52, 73, 94));
        panel.setPreferredSize(new Dimension(220, 0));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        JLabel title = new JLabel("ADMINISTRATEUR");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(title, gbc);
        
        String[] items = {"📊 Dashboard", "👥 Utilisateurs", "🏢 Bâtiments", "🚪 Salles", "📈 Rapports", "⚙️ Configuration"};
        String[] cards = {"DASHBOARD", "USERS", "BUILDINGS", "ROOMS", "REPORTS", "CONFIG"};
        for (int i = 0; i < items.length; i++) {
            JButton btn = new JButton(items[i]);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(52, 73, 94));
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            final String card = cards[i];
            btn.addActionListener(e -> cardLayout.show(contentPanel, card));
            panel.add(btn, gbc);
        }
        
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);
        JButton logoutBtn = new JButton("🚪 Déconnexion");
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setBackground(new Color(192, 57, 43));
        logoutBtn.addActionListener(e -> deconnexion());
        panel.add(logoutBtn, gbc);
        
        return panel;
    }
    
    private JPanel createUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(240, 240, 240));
        toolBar.add(new JButton("➕ Ajouter"));
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(new JButton("✏️ Modifier"));
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(new JButton("🗑️ Supprimer"));
        toolBar.addSeparator(new Dimension(20, 10));
        toolBar.add(new JLabel("Rechercher:"));
        toolBar.add(new JTextField(15));
        toolBar.add(new JButton("🔍"));
        panel.add(toolBar, BorderLayout.NORTH);
        
        String[] colonnes = {"ID", "Nom", "Email", "Rôle", "Statut"};
        Object[][] donnees = {
            {"1", "Admin", "admin@univ.fr", "Admin", "🟢 Actif"},
            {"2", "Martin", "martin@univ.fr", "Enseignant", "🟢 Actif"},
            {"3", "Jean", "jean@etudiant.fr", "Étudiant", "🟡 Inactif"}
        };
        tableModelUtilisateurs = new DefaultTableModel(donnees, colonnes);
        tableUtilisateurs = new JTable(tableModelUtilisateurs);
        tableUtilisateurs.setRowHeight(30);
        panel.add(new JScrollPane(tableUtilisateurs), BorderLayout.CENTER);
        
        JPanel info = new JPanel(new FlowLayout(FlowLayout.LEFT));
        info.setBackground(new Color(240, 240, 240));
        info.add(new JLabel("Total: 3 utilisateurs"));
        panel.add(info, BorderLayout.SOUTH);
        return panel;
    }
    
    private JPanel createBuildingsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("🏢 Bâtiment A - 3 étages");
        listModel.addElement("🏢 Bâtiment B - 2 étages");
        listModel.addElement("🏢 Bâtiment C - 1 étage");
        JList<String> buildingList = new JList<>(listModel);
        buildingList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(new JScrollPane(buildingList), BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(new JButton("➕ Ajouter"));
        btnPanel.add(new JButton("✏️ Modifier"));
        btnPanel.add(new JButton("🗑️ Supprimer"));
        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }
    
    private JPanel createRoomsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Formulaire d'ajout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter une salle"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Numéro:"), gbc);
        gbc.gridx = 1;
        JTextField numeroField = new JTextField(10);
        formPanel.add(numeroField, gbc);
        
        gbc.gridx = 2; gbc.gridy = 0;
        formPanel.add(new JLabel("Bâtiment:"), gbc);
        gbc.gridx = 3;
        String[] batiments = {"A", "B", "C"};
        JComboBox<String> batimentCombo = new JComboBox<>(batiments);
        formPanel.add(batimentCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Capacité:"), gbc);
        gbc.gridx = 1;
        JSpinner capaciteSpinner = new JSpinner(new SpinnerNumberModel(30, 10, 500, 10));
        formPanel.add(capaciteSpinner, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1;
        formPanel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 3;
        String[] types = {"Amphi", "TD", "TP", "Salle réunion"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        formPanel.add(typeCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Équipements:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        JTextField equipField = new JTextField(30);
        equipField.setText("VidéoProjecteur, Tableau blanc");
        formPanel.add(equipField, gbc);
        
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 1;
        JButton ajouterBtn = new JButton("➕ Ajouter");
        ajouterBtn.setBackground(new Color(46, 204, 113));
        ajouterBtn.setForeground(Color.WHITE);
        formPanel.add(ajouterBtn, gbc);
        
        panel.add(formPanel, BorderLayout.NORTH);
        
        // Tableau des salles
        String[] colonnes = {"Salle", "Bâtiment", "Capacité", "Type", "Équipements", "État"};
        Object[][] donnees = {
            {"A101", "A", 150, "Amphi", "VidéoProj, Clim", "🟢 Disponible"},
            {"A105", "A", 40, "TD", "Tableau blanc", "🔴 Occupée"},
            {"B201", "B", 30, "TP", "24 PC, VidéoProj", "🟡 Maintenance"}
        };
        tableModelSalles = new DefaultTableModel(donnees, colonnes);
        tableSalles = new JTable(tableModelSalles);
        tableSalles.setRowHeight(35);
        panel.add(new JScrollPane(tableSalles), BorderLayout.CENTER);
        
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBackground(new Color(240, 240, 240));
        actionPanel.add(new JButton("📊 Voir planning"));
        panel.add(actionPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titre = new JLabel("Génération de rapports");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titre, gbc);
        
        String[] types = {"📊 Occupation des salles", "👥 Rapport utilisateurs", "📚 Rapport des cours", "📈 Statistiques globales"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1; panel.add(typeCombo, gbc);
        
        JButton genererBtn = new JButton("📥 Générer PDF");
        genererBtn.setBackground(new Color(46, 204, 113));
        genererBtn.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(genererBtn, gbc);
        
        return panel;
    }
    
    private JPanel createConfigPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titre = new JLabel("Configuration");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titre, gbc);
        
        panel.add(new JLabel("Nom université:"), gbc);
        gbc.gridx = 1; panel.add(new JTextField("Université de Technologie", 20), gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Fuseau horaire:"), gbc);
        gbc.gridx = 1; panel.add(new JComboBox<>(new String[]{"Europe/Paris", "UTC"}), gbc);
        
        JButton saveBtn = new JButton("💾 Enregistrer");
        saveBtn.setBackground(new Color(46, 204, 113));
        saveBtn.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(saveBtn, gbc);
        
        return panel;
    }
    
    // ==================== UTILITAIRES ====================
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(color);
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }
    
    private void chargerDonnees() {
        // Simulation
    }
    
    private void deconnexion() {
        int choix = JOptionPane.showConfirmDialog(this, "Déconnexion ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choix == JOptionPane.YES_OPTION) {
            dispose();
            new LoginInterface().setVisible(true);
        }
    }
}