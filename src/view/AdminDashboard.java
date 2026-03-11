package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class AdminDashboard extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private JTabbedPane tabbedPane;
    private JTable tableUtilisateurs;
    private DefaultTableModel tableModelUtilisateurs;
    private JTable tableSalles;
    private DefaultTableModel tableModelSalles;
    
    public AdminDashboard() {
        initUI();
        chargerDonnees();
    }
    
    @SuppressWarnings("unused")
	private void initUI() {
        setTitle("UNIV-SCHEDULER - Dashboard Administrateur");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Barre de menu
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fichierMenu = new JMenu("Fichier");
        JMenuItem deconnexionItem = new JMenuItem("Déconnexion");
        JMenuItem quitterItem = new JMenuItem("Quitter");
        fichierMenu.add(deconnexionItem);
        fichierMenu.addSeparator();
        fichierMenu.add(quitterItem);
        
        JMenu aideMenu = new JMenu("Aide");
        JMenuItem aproposItem = new JMenuItem("À propos");
        aideMenu.add(aproposItem);
        
        menuBar.add(fichierMenu);
        menuBar.add(aideMenu);
        setJMenuBar(menuBar);
        
        // Actions des menus
        deconnexionItem.addActionListener(e -> deconnexion());
        quitterItem.addActionListener(e -> System.exit(0));
        aproposItem.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, 
                "UNIV-SCHEDULER v1.0\nGestion des salles universitaires\nModule Administrateur", 
                "À propos", 
                JOptionPane.INFORMATION_MESSAGE)
        );
        
        // Panel principal avec onglets
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("📊 Dashboard", createDashboardPanel());
        tabbedPane.addTab("👥 Utilisateurs", createUsersPanel());
        tabbedPane.addTab("🏢 Bâtiments", createBuildingsPanel());
        tabbedPane.addTab("🚪 Salles", createRoomsPanel());
        tabbedPane.addTab("📈 Rapports", createReportsPanel());
        tabbedPane.addTab("⚙️ Configuration", createConfigPanel());
        
        add(tabbedPane);
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        
        // Statistiques
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "Statistiques globales",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14)
        ));
        
        statsPanel.add(createStatCard("🏢 Salles", "45", new Color(52, 152, 219)));
        statsPanel.add(createStatCard("👥 Utilisateurs", "128", new Color(46, 204, 113)));
        statsPanel.add(createStatCard("📚 Cours", "234", new Color(155, 89, 182)));
        statsPanel.add(createStatCard("📊 Taux d'occupation", "78%", new Color(230, 126, 34)));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.4;
        panel.add(statsPanel, gbc);
        
        // Alertes et activités récentes
        JPanel alertsPanel = new JPanel(new BorderLayout());
        alertsPanel.setBackground(Color.WHITE);
        alertsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            "Alertes et notifications",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14)
        ));
        
        JPanel alertsListPanel = new JPanel();
        alertsListPanel.setBackground(Color.WHITE);
        alertsListPanel.setLayout(new BoxLayout(alertsListPanel, BoxLayout.Y_AXIS));
        
        JLabel alert1 = new JLabel("⚠️ 3 conflits de réservation à résoudre");
        alert1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        alert1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel alert2 = new JLabel("🔧 2 salles nécessitent une maintenance");
        alert2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        alert2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel alert3 = new JLabel("✅ 5 nouvelles demandes de réservation en attente");
        alert3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        alert3.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel alert4 = new JLabel("📅 8 cours non planifiés cette semaine");
        alert4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        alert4.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        alertsListPanel.add(alert1);
        alertsListPanel.add(alert2);
        alertsListPanel.add(alert3);
        alertsListPanel.add(alert4);
        
        alertsPanel.add(alertsListPanel, BorderLayout.CENTER);
        
        JButton voirToutButton = new JButton("Voir toutes les alertes");
        voirToutButton.setBackground(new Color(52, 152, 219));
        voirToutButton.setForeground(Color.WHITE);
        voirToutButton.setFocusPainted(false);
        alertsPanel.add(voirToutButton, BorderLayout.SOUTH);
        
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        panel.add(alertsPanel, gbc);
        
        // Actions rapides
        JPanel actionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            "Actions rapides",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14)
        ));
        
        JButton addUserBtn = createActionButton("➕ Ajouter un utilisateur", new Color(46, 204, 113));
        JButton addRoomBtn = createActionButton("🏢 Ajouter une salle", new Color(52, 152, 219));
        JButton generateReportBtn = createActionButton("📊 Générer rapport", new Color(155, 89, 182));
        JButton configBtn = createActionButton("⚙️ Configuration", new Color(230, 126, 34));
        
        actionsPanel.add(addUserBtn);
        actionsPanel.add(addRoomBtn);
        actionsPanel.add(generateReportBtn);
        actionsPanel.add(configBtn);
        
        gbc.gridy = 2;
        gbc.weighty = 0.3;
        panel.add(actionsPanel, gbc);
        
        return panel;
    }
    
    private JButton createActionButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return button;
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(color);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Barre d'outils
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(240, 240, 240));
        
        JButton addBtn = new JButton("➕ Ajouter");
        addBtn.setBackground(new Color(46, 204, 113));
        addBtn.setForeground(Color.WHITE);
        
        JButton editBtn = new JButton("✏️ Modifier");
        editBtn.setBackground(new Color(52, 152, 219));
        editBtn.setForeground(Color.WHITE);
        
        JButton deleteBtn = new JButton("🗑️ Supprimer");
        deleteBtn.setBackground(new Color(231, 76, 60));
        deleteBtn.setForeground(Color.WHITE);
        
        toolBar.add(addBtn);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(editBtn);
        toolBar.addSeparator(new Dimension(10, 10));
        toolBar.add(deleteBtn);
        toolBar.addSeparator(new Dimension(20, 10));
        toolBar.add(new JLabel("Rechercher:"));
        toolBar.add(new JTextField(15));
        toolBar.add(new JButton("🔍"));
        
        panel.add(toolBar, BorderLayout.NORTH);
        
        // Tableau des utilisateurs
        String[] colonnes = {"ID", "Nom", "Email", "Rôle", "Département", "Dernière connexion", "Statut"};
        Object[][] donnees = {
            {"1", "Admin Principal", "admin@univ.fr", "Administrateur", "Direction", "08/03/2026", "🟢 Actif"},
            {"2", "Martin Dupuis", "martin@univ.fr", "Gestionnaire", "Scolarité", "07/03/2026", "🟢 Actif"},
            {"3", "Dr. Bernard", "bernard@univ.fr", "Enseignant", "Informatique", "06/03/2026", "🟢 Actif"},
            {"4", "Dr. Dubois", "dubois@univ.fr", "Enseignant", "Mathématiques", "05/03/2026", "🟡 Inactif"},
            {"5", "Jean Dupont", "jean.dupont@etudiant.univ.fr", "Étudiant", "L3 Info", "08/03/2026", "🟢 Actif"},
            {"6", "Marie Curie", "marie.curie@etudiant.univ.fr", "Étudiant", "M1 Physique", "08/03/2026", "🟢 Actif"}
        };
        
        tableModelUtilisateurs = new DefaultTableModel(donnees, colonnes);
        tableUtilisateurs = new JTable(tableModelUtilisateurs);
        tableUtilisateurs.setRowHeight(30);
        tableUtilisateurs.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableUtilisateurs.getTableHeader().setBackground(new Color(240, 240, 240));
        tableUtilisateurs.setSelectionBackground(new Color(210, 230, 250));
        
        JScrollPane scrollPane = new JScrollPane(tableUtilisateurs);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel d'information
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setBackground(new Color(240, 240, 240));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        infoPanel.add(new JLabel("Total: 6 utilisateurs • 4 actifs • 2 inactifs"));
        panel.add(infoPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createBuildingsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Panel gauche - Liste des bâtiments
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createTitledBorder("Bâtiments"));
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("🏢 Bâtiment A - Principal (3 étages, 15 salles)");
        listModel.addElement("🏢 Bâtiment B - Sciences (2 étages, 10 salles)");
        listModel.addElement("🏢 Bâtiment C - Administration (1 étage, 5 salles)");
        listModel.addElement("🏢 Bâtiment D - Bibliothèque (2 étages, 8 salles)");
        listModel.addElement("🏢 Bâtiment E - Sports (1 étage, 4 salles)");
        
        JList<String> buildingList = new JList<>(listModel);
        buildingList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        buildingList.setSelectionBackground(new Color(210, 230, 250));
        
        JScrollPane listScroll = new JScrollPane(buildingList);
        listScroll.setPreferredSize(new Dimension(400, 300));
        leftPanel.add(listScroll, BorderLayout.CENTER);
        
        // Boutons pour les bâtiments
        JPanel buildingButtons = new JPanel(new FlowLayout());
        buildingButtons.add(new JButton("➕ Ajouter"));
        buildingButtons.add(new JButton("✏️ Modifier"));
        buildingButtons.add(new JButton("🗑️ Supprimer"));
        leftPanel.add(buildingButtons, BorderLayout.SOUTH);
        
        panel.add(leftPanel, BorderLayout.WEST);
        
        // Panel droit - Détails du bâtiment sélectionné
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createTitledBorder("Détails du bâtiment"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        rightPanel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1;
        rightPanel.add(new JLabel("Bâtiment A"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        rightPanel.add(new JLabel("Adresse:"), gbc);
        gbc.gridx = 1;
        rightPanel.add(new JLabel("Campus Universitaire, Bâtiment A"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        rightPanel.add(new JLabel("Nombre d'étages:"), gbc);
        gbc.gridx = 1;
        rightPanel.add(new JLabel("3"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        rightPanel.add(new JLabel("Nombre de salles:"), gbc);
        gbc.gridx = 1;
        rightPanel.add(new JLabel("15"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        rightPanel.add(new JLabel("Responsable:"), gbc);
        gbc.gridx = 1;
        rightPanel.add(new JLabel("M. Martin"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        rightPanel.add(new JLabel("Taux d'occupation:"), gbc);
        gbc.gridx = 1;
        rightPanel.add(new JLabel("75%"), gbc);
        
        panel.add(rightPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createRoomsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Filtres
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setBackground(new Color(240, 240, 240));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        filterPanel.add(new JLabel("Bâtiment:"));
        String[] batiments = {"Tous", "A", "B", "C", "D", "E"};
        JComboBox<String> batimentFilter = new JComboBox<>(batiments);
        filterPanel.add(batimentFilter);
        
        filterPanel.add(new JLabel("Type:"));
        String[] types = {"Tous", "Amphi", "TD", "TP", "Salle de réunion", "Laboratoire"};
        JComboBox<String> typeFilter = new JComboBox<>(types);
        filterPanel.add(typeFilter);
        
        filterPanel.add(new JLabel("Capacité min:"));
        JSpinner capaciteFilter = new JSpinner(new SpinnerNumberModel(0, 0, 200, 10));
        filterPanel.add(capaciteFilter);
        
        filterPanel.add(new JButton("🔍 Filtrer"));
        filterPanel.add(new JButton("🔄 Réinitialiser"));
        
        panel.add(filterPanel, BorderLayout.NORTH);
        
        // Tableau des salles
        String[] colonnes = {"Salle", "Bâtiment", "Étage", "Capacité", "Type", "Équipements", "État", "Action"};
        Object[][] donnees = {
            {"A101", "A", "1", "150", "Amphi", "VidéoProj, Clim, Tableau blanc", "🟢 Disponible", "Réserver"},
            {"A105", "A", "1", "40", "TD", "Tableau blanc, VidéoProj", "🔴 Occupée", "Voir"},
            {"B201", "B", "2", "30", "TP", "24 PC, VidéoProj, Tableau", "🟡 Maintenance", "Voir"},
            {"B205", "B", "2", "25", "TD", "Tableau blanc", "🟢 Disponible", "Réserver"},
            {"C001", "C", "0", "20", "Salle réunion", "VidéoProj, Tableau blanc", "🟢 Disponible", "Réserver"},
            {"D101", "D", "1", "50", "Bibliothèque", "Tables, Prises", "🟢 Disponible", "Voir"},
            {"A205", "A", "2", "60", "Amphi", "VidéoProj, Clim", "🔴 Occupée", "Voir"}
        };
        
        tableModelSalles = new DefaultTableModel(donnees, colonnes);
        tableSalles = new JTable(tableModelSalles);
        tableSalles.setRowHeight(35);
        tableSalles.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableSalles.getTableHeader().setBackground(new Color(240, 240, 240));
        tableSalles.setSelectionBackground(new Color(210, 230, 250));
        
        JScrollPane scrollPane = new JScrollPane(tableSalles);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel d'actions
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBackground(new Color(240, 240, 240));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        actionPanel.add(new JButton("➕ Ajouter une salle"));
        actionPanel.add(new JButton("📊 Voir planning"));
        actionPanel.add(new JButton("📥 Exporter la liste"));
        
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
        
        // Titre
        JLabel titre = new JLabel("Génération de rapports");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titre, gbc);
        
        // Type de rapport
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Type de rapport:"), gbc);
        gbc.gridx = 1;
        String[] types = {
            "📊 Rapport d'occupation des salles",
            "👥 Rapport des utilisateurs",
            "📚 Rapport des cours",
            "📈 Statistiques globales",
            "💰 Rapport financier",
            "🔧 Rapport de maintenance"
        };
        JComboBox<String> typeCombo = new JComboBox<>(types);
        typeCombo.setPreferredSize(new Dimension(300, 30));
        panel.add(typeCombo, gbc);
        
        // Période
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Période:"), gbc);
        gbc.gridx = 1;
        String[] periodes = {"Aujourd'hui", "Cette semaine", "Ce mois", "Ce semestre", "Cette année", "Personnalisé"};
        JComboBox<String> periodeCombo = new JComboBox<>(periodes);
        periodeCombo.setPreferredSize(new Dimension(300, 30));
        panel.add(periodeCombo, gbc);
        
        // Format
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Format:"), gbc);
        gbc.gridx = 1;
        JPanel formatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formatPanel.setBackground(Color.WHITE);
        JRadioButton pdfRadio = new JRadioButton("PDF", true);
        JRadioButton excelRadio = new JRadioButton("Excel");
        JRadioButton csvRadio = new JRadioButton("CSV");
        ButtonGroup group = new ButtonGroup();
        group.add(pdfRadio);
        group.add(excelRadio);
        group.add(csvRadio);
        formatPanel.add(pdfRadio);
        formatPanel.add(excelRadio);
        formatPanel.add(csvRadio);
        panel.add(formatPanel, gbc);
        
        // Options supplémentaires
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Options:"), gbc);
        gbc.gridx = 1;
        JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
        optionsPanel.setBackground(Color.WHITE);
        optionsPanel.add(new JCheckBox("Inclure les graphiques"));
        optionsPanel.add(new JCheckBox("Détails par bâtiment"));
        optionsPanel.add(new JCheckBox("Résumé exécutif"));
        panel.add(optionsPanel, gbc);
        
        // Bouton générer
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton genererBtn = new JButton("📥 Générer le rapport");
        genererBtn.setBackground(new Color(46, 204, 113));
        genererBtn.setForeground(Color.WHITE);
        genererBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        genererBtn.setPreferredSize(new Dimension(250, 50));
        genererBtn.setFocusPainted(false);
        panel.add(genererBtn, gbc);
        
        // Historique
        gbc.gridy = 6;
        JLabel historiqueLabel = new JLabel("Rapports récents:");
        historiqueLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(historiqueLabel, gbc);
        
        gbc.gridy = 7;
        String[] recentColumns = {"Date", "Type", "Format", "Taille", "Actions"};
        Object[][] recentData = {
            {"08/03/2026", "Occupation des salles", "PDF", "2.3 MB", "📥 📧 🗑️"},
            {"07/03/2026", "Rapport utilisateurs", "Excel", "1.1 MB", "📥 📧 🗑️"},
            {"06/03/2026", "Statistiques globales", "PDF", "3.5 MB", "📥 📧 🗑️"}
        };
        
        JTable recentTable = new JTable(recentData, recentColumns);
        recentTable.setRowHeight(25);
        JScrollPane recentScroll = new JScrollPane(recentTable);
        recentScroll.setPreferredSize(new Dimension(600, 100));
        panel.add(recentScroll, gbc);
        
        return panel;
    }
    
    private JPanel createConfigPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Titre
        JLabel titre = new JLabel("Configuration du système");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titre, gbc);
        
        // Paramètres généraux
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Nom de l'université:"), gbc);
        gbc.gridx = 1;
        panel.add(new JTextField("Université de Technologie", 20), gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Fuseau horaire:"), gbc);
        gbc.gridx = 1;
        String[] timezones = {"Europe/Paris", "UTC", "Africa/Dakar", "America/New_York"};
        panel.add(new JComboBox<>(timezones), gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Langue par défaut:"), gbc);
        gbc.gridx = 1;
        String[] languages = {"Français", "English", "Español", "العربية"};
        panel.add(new JComboBox<>(languages), gbc);
        
        // Séparateur
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(new JSeparator(), gbc);
        
        // Options de réservation
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Durée max réservation:"), gbc);
        gbc.gridx = 1;
        panel.add(new JSpinner(new SpinnerNumberModel(4, 1, 24, 1)), gbc);
        
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Délai annulation:"), gbc);
        gbc.gridx = 1;
        panel.add(new JSpinner(new SpinnerNumberModel(24, 1, 72, 1)), gbc);
        
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        JCheckBox autoConfirm = new JCheckBox("Confirmation automatique des réservations");
        autoConfirm.setBackground(Color.WHITE);
        panel.add(autoConfirm, gbc);
        
        gbc.gridy = 8;
        JCheckBox notifyEmail = new JCheckBox("Notifications par email");
        notifyEmail.setBackground(Color.WHITE);
        panel.add(notifyEmail, gbc);
        
        // Boutons
        gbc.gridy = 9;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        
        JButton saveBtn = new JButton("💾 Enregistrer");
        saveBtn.setBackground(new Color(46, 204, 113));
        saveBtn.setForeground(Color.WHITE);
        
        JButton resetBtn = new JButton("🔄 Réinitialiser");
        resetBtn.setBackground(new Color(52, 152, 219));
        resetBtn.setForeground(Color.WHITE);
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(resetBtn);
        panel.add(buttonPanel, gbc);
        
        return panel;
    }
    
    private void chargerDonnees() {
        // Simulation de chargement des données
        System.out.println("Chargement des données administrateur...");
    }
    
    private void deconnexion() {
        int choix = JOptionPane.showConfirmDialog(this, 
            "Voulez-vous vraiment vous déconnecter ?",
            "Déconnexion",
            JOptionPane.YES_NO_OPTION);
        
        if (choix == JOptionPane.YES_OPTION) {
            dispose();
            new LoginInterface().setVisible(true);
        }
    }
}