package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.*;

@SuppressWarnings("unused")
public class GestionnaireDashboard extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private JTabbedPane tabbedPane;
    private JTable tableCours;
    private DefaultTableModel tableModelCours;
    private JTable tableSalles;
    private DefaultTableModel tableModelSalles;
    private JTable tableEnseignants;
    private DefaultTableModel tableModelEnseignants;
    
    public GestionnaireDashboard() {
        initUI();
        chargerDonnees();
    }
    
    @SuppressWarnings("unused")
	private void initUI() {
        setTitle("UNIV-SCHEDULER - Dashboard Gestionnaire");
        setSize(1300, 850);
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
        
        JMenu editionMenu = new JMenu("Édition");
        JMenuItem nouveauCoursItem = new JMenuItem("Nouveau cours");
        JMenuItem nouvelleSalleItem = new JMenuItem("Nouvelle salle");
        editionMenu.add(nouveauCoursItem);
        editionMenu.add(nouvelleSalleItem);
        
        JMenu affichageMenu = new JMenu("Affichage");
        JMenuItem vueJourItem = new JMenuItem("Vue jour");
        JMenuItem vueSemaineItem = new JMenuItem("Vue semaine");
        JMenuItem vueMoisItem = new JMenuItem("Vue mois");
        affichageMenu.add(vueJourItem);
        affichageMenu.add(vueSemaineItem);
        affichageMenu.add(vueMoisItem);
        
        JMenu aideMenu = new JMenu("Aide");
        JMenuItem aproposItem = new JMenuItem("À propos");
        aideMenu.add(aproposItem);
        
        menuBar.add(fichierMenu);
        menuBar.add(editionMenu);
        menuBar.add(affichageMenu);
        menuBar.add(aideMenu);
        setJMenuBar(menuBar);
        
        // Actions des menus
        deconnexionItem.addActionListener(e -> deconnexion());
        quitterItem.addActionListener(e -> System.exit(0));
        aproposItem.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, 
                "UNIV-SCHEDULER v1.0\nModule Gestionnaire\nGestion des emplois du temps", 
                "À propos", 
                JOptionPane.INFORMATION_MESSAGE)
        );
        
        nouveauCoursItem.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Formulaire d'ajout de cours")
        );
        
        nouvelleSalleItem.addActionListener(e -> 
            tabbedPane.setSelectedIndex(1)
        );
        
        // Panel principal avec onglets
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("📅 Planification", createPlanificationPanel());
        tabbedPane.addTab("🏢 Gestion des salles", createSallesPanel());
        tabbedPane.addTab("👨‍🏫 Enseignants", createEnseignantsPanel());
        tabbedPane.addTab("⚠️ Conflits", createConflitsPanel());
        tabbedPane.addTab("📊 Rapports", createRapportsPanel());
        tabbedPane.addTab("🗓️ Calendrier", createCalendrierPanel());
        
        add(tabbedPane);
    }
    
    private JPanel createPlanificationPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Barre d'outils supérieure
        JPanel topToolbar = new JPanel(new BorderLayout());
        topToolbar.setBackground(new Color(240, 240, 240));
        topToolbar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Sélecteur de vue
        JPanel viewSelector = new JPanel(new FlowLayout(FlowLayout.LEFT));
        viewSelector.setBackground(new Color(240, 240, 240));
        viewSelector.add(new JLabel("Vue:"));
        String[] vues = {"Jour", "Semaine", "Mois", "Liste"};
        JComboBox<String> vueCombo = new JComboBox<>(vues);
        viewSelector.add(vueCombo);
        
        // Navigation
        JPanel navPanel = new JPanel(new FlowLayout());
        navPanel.setBackground(new Color(240, 240, 240));
        navPanel.add(new JButton("◀ Précédent"));
        navPanel.add(new JLabel("Semaine du 09/03/2026"));
        navPanel.add(new JButton("Suivant ▶"));
        
        // Actions rapides
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBackground(new Color(240, 240, 240));
        actionPanel.add(new JButton("➕ Nouveau cours"));
        actionPanel.add(new JButton("🔄 Vérifier conflits"));
        actionPanel.add(new JButton("📥 Exporter"));
        
        topToolbar.add(viewSelector, BorderLayout.WEST);
        topToolbar.add(navPanel, BorderLayout.CENTER);
        topToolbar.add(actionPanel, BorderLayout.EAST);
        
        panel.add(topToolbar, BorderLayout.NORTH);
        
        // Tableau des cours
        String[] colonnes = {"Matière", "Classe", "Groupe", "Enseignant", "Jour", "Horaire", "Salle", "Statut", "Actions"};
        Object[][] donnees = {
            {"Programmation Java", "L3 Info", "Groupe A", "Dr. Martin", "Lundi", "08h-10h", "A101", "✅ Confirmé", "✏️ 🗑️"},
            {"Base de données", "L3 Info", "Groupe A", "Dr. Bernard", "Lundi", "10h-12h", "A105", "✅ Confirmé", "✏️ 🗑️"},
            {"Réseaux", "M1 Info", "Groupe unique", "Dr. Dubois", "Lundi", "14h-16h", "B201", "⏳ En attente", "✏️ 🗑️"},
            {"Algorithmique", "L2 Info", "Groupe B", "Dr. Petit", "Mardi", "08h-10h", "C103", "✅ Confirmé", "✏️ 🗑️"},
            {"Anglais", "L3 Info", "Groupes A+B", "Dr. Smith", "Mardi", "10h-12h", "A205", "✅ Confirmé", "✏️ 🗑️"},
            {"Systèmes d'exploitation", "L3 Info", "Groupe B", "Dr. Martin", "Mercredi", "08h-10h", "B205", "⏳ En attente", "✏️ 🗑️"},
            {"Projet", "L3 Info", "Groupes A+B", "Dr. Bernard", "Jeudi", "14h-18h", "TP Info", "✅ Confirmé", "✏️ 🗑️"}
        };
        
        tableModelCours = new DefaultTableModel(donnees, colonnes);
        tableCours = new JTable(tableModelCours);
        tableCours.setRowHeight(35);
        tableCours.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableCours.getTableHeader().setBackground(new Color(240, 240, 240));
        tableCours.setSelectionBackground(new Color(210, 230, 250));
        
        JScrollPane scrollPane = new JScrollPane(tableCours);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel d'information et filtres
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setBackground(new Color(240, 240, 240));
        filterPanel.add(new JLabel("Filtrer:"));
        filterPanel.add(new JTextField(15));
        filterPanel.add(new JButton("🔍 Rechercher"));
        
        JLabel countLabel = new JLabel("Total: 7 cours • 5 confirmés • 2 en attente");
        countLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        bottomPanel.add(filterPanel, BorderLayout.WEST);
        bottomPanel.add(countLabel, BorderLayout.EAST);
        
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createSallesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Formulaire d'ajout rapide
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150)),
            "Ajouter une nouvelle salle"
        ));
        
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
        String[] batiments = {"A", "B", "C", "D", "E"};
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
        String[] types = {"Amphi", "TD", "TP", "Laboratoire", "Salle de réunion", "Bibliothèque"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        formPanel.add(typeCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 4;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        JButton ajouterBtn = new JButton("➕ Ajouter la salle");
        ajouterBtn.setBackground(new Color(46, 204, 113));
        ajouterBtn.setForeground(Color.WHITE);
        
        JButton annulerBtn = new JButton("✖ Annuler");
        annulerBtn.setBackground(new Color(231, 76, 60));
        annulerBtn.setForeground(Color.WHITE);
        
        buttonPanel.add(ajouterBtn);
        buttonPanel.add(annulerBtn);
        formPanel.add(buttonPanel, gbc);
        
        panel.add(formPanel, BorderLayout.NORTH);
        
        // Filtres
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setBackground(new Color(240, 240, 240));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        filterPanel.add(new JLabel("Filtrer par:"));
        String[] filterTypes = {"Toutes", "Disponibles", "Occupées", "Maintenance"};
        JComboBox<String> filterCombo = new JComboBox<>(filterTypes);
        filterPanel.add(filterCombo);
        
        filterPanel.add(new JLabel("Capacité min:"));
        JSpinner minCapacite = new JSpinner(new SpinnerNumberModel(0, 0, 500, 10));
        filterPanel.add(minCapacite);
        
        filterPanel.add(new JLabel("Équipement:"));
        String[] equipements = {"Tous", "VidéoProjecteur", "Climatisation", "PC", "Tableau interactif"};
        JComboBox<String> equipCombo = new JComboBox<>(equipements);
        filterPanel.add(equipCombo);
        
        filterPanel.add(new JButton("🔍 Appliquer"));
        filterPanel.add(new JButton("🔄 Réinitialiser"));
        
        panel.add(filterPanel, BorderLayout.CENTER);
        
        // Tableau des salles
        String[] colonnes = {"ID", "Salle", "Bâtiment", "Étage", "Capacité", "Type", "Équipements", "État", "Occupation", "Actions"};
        Object[][] donnees = {
            {"1", "A101", "A", "1", "150", "Amphi", "VidéoProj, Clim, Tableau", "🟢 Disponible", "45%", "📅 ✏️ 🗑️"},
            {"2", "A105", "A", "1", "40", "TD", "Tableau blanc, VidéoProj", "🔴 Occupée", "100%", "📅 ✏️ 🗑️"},
            {"3", "B201", "B", "2", "30", "TP", "24 PC, VidéoProj", "🟡 Maintenance", "0%", "📅 ✏️ 🗑️"},
            {"4", "B205", "B", "2", "25", "TD", "Tableau blanc", "🟢 Disponible", "20%", "📅 ✏️ 🗑️"},
            {"5", "C001", "C", "0", "20", "Salle réunion", "VidéoProj, Tableau", "🟢 Disponible", "10%", "📅 ✏️ 🗑️"},
            {"6", "D101", "D", "1", "50", "Bibliothèque", "Tables, Prises, WiFi", "🟢 Disponible", "60%", "📅 ✏️ 🗑️"},
            {"7", "A205", "A", "2", "60", "Amphi", "VidéoProj, Clim", "🔴 Occupée", "80%", "📅 ✏️ 🗑️"},
            {"8", "B105", "B", "1", "35", "TP", "20 PC, Tableau", "🟢 Disponible", "30%", "📅 ✏️ 🗑️"}
        };
        
        tableModelSalles = new DefaultTableModel(donnees, colonnes);
        tableSalles = new JTable(tableModelSalles);
        tableSalles.setRowHeight(35);
        tableSalles.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableSalles.getTableHeader().setBackground(new Color(240, 240, 240));
        tableSalles.setSelectionBackground(new Color(210, 230, 250));
        
        JScrollPane scrollPane = new JScrollPane(tableSalles);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createEnseignantsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Barre d'outils
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(new Color(240, 240, 240));
        
        toolBar.add(new JButton("➕ Assigner un cours"));
        toolBar.addSeparator();
        toolBar.add(new JButton("📅 Voir emploi du temps"));
        toolBar.addSeparator();
        toolBar.add(new JButton("📊 Disponibilités"));
        toolBar.addSeparator(new Dimension(20, 10));
        toolBar.add(new JLabel("Rechercher:"));
        toolBar.add(new JTextField(15));
        toolBar.add(new JButton("🔍"));
        
        panel.add(toolBar, BorderLayout.NORTH);
        
        // Tableau des enseignants
        String[] colonnes = {"ID", "Nom", "Email", "Département", "Grade", "Cours assignés", "Disponibilité", "Actions"};
        Object[][] donnees = {
            {"1", "Dr. Martin", "martin@univ.fr", "Informatique", "Professeur", "4", "80%", "📅 ✏️"},
            {"2", "Dr. Bernard", "bernard@univ.fr", "Informatique", "MCF", "3", "60%", "📅 ✏️"},
            {"3", "Dr. Dubois", "dubois@univ.fr", "Réseaux", "Professeur", "5", "90%", "📅 ✏️"},
            {"4", "Dr. Petit", "petit@univ.fr", "Algorithmique", "MCF", "2", "40%", "📅 ✏️"},
            {"5", "Dr. Smith", "smith@univ.fr", "Langues", "Professeur", "3", "70%", "📅 ✏️"},
            {"6", "Dr. Legrand", "legrand@univ.fr", "Mathématiques", "Professeur", "4", "85%", "📅 ✏️"}
        };
        
        tableModelEnseignants = new DefaultTableModel(donnees, colonnes);
        tableEnseignants = new JTable(tableModelEnseignants);
        tableEnseignants.setRowHeight(35);
        tableEnseignants.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableEnseignants.getTableHeader().setBackground(new Color(240, 240, 240));
        tableEnseignants.setSelectionBackground(new Color(210, 230, 250));
        
        JScrollPane scrollPane = new JScrollPane(tableEnseignants);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de sélection de cours
        JPanel assignPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        assignPanel.setBackground(new Color(240, 240, 240));
        assignPanel.setBorder(BorderFactory.createTitledBorder("Assigner un cours"));
        
        assignPanel.add(new JLabel("Enseignant sélectionné:"));
        assignPanel.add(new JLabel("Dr. Martin"));
        assignPanel.add(new JLabel("Cours:"));
        String[] cours = {"Programmation Java", "Base de données", "Réseaux", "Algorithmique"};
        assignPanel.add(new JComboBox<>(cours));
        assignPanel.add(new JLabel("Classe:"));
        String[] classes = {"L3 Info", "L2 Info", "M1 Info"};
        assignPanel.add(new JComboBox<>(classes));
        assignPanel.add(new JButton("✅ Assigner"));
        
        panel.add(assignPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createConflitsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // En-tête avec alertes
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(255, 240, 240));
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.RED, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        JLabel warningIcon = new JLabel("⚠️");
        warningIcon.setFont(new Font("Segoe UI", Font.PLAIN, 32));
        
        JLabel warningText = new JLabel("5 conflits détectés nécessitant une résolution urgente");
        warningText.setFont(new Font("Segoe UI", Font.BOLD, 16));
        warningText.setForeground(Color.RED);
        
        JButton resolveAllBtn = new JButton("🔄 Résoudre automatiquement");
        resolveAllBtn.setBackground(new Color(46, 204, 113));
        resolveAllBtn.setForeground(Color.WHITE);
        
        headerPanel.add(warningIcon, BorderLayout.WEST);
        headerPanel.add(warningText, BorderLayout.CENTER);
        headerPanel.add(resolveAllBtn, BorderLayout.EAST);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        
        // Tableau des conflits
        String[] colonnes = {"Type", "Description", "Date/Horaire", "Salle", "Enseignant", "Priorité", "Action"};
        Object[][] donnees = {
            {"Salle", "Double réservation: Java et Réseaux", "Lundi 08h-10h", "A101", "Dr. Martin / Dr. Dubois", "🔴 Haute", "Résoudre"},
            {"Salle", "Double réservation: BD et Anglais", "Mardi 10h-12h", "A105", "Dr. Bernard / Dr. Smith", "🔴 Haute", "Résoudre"},
            {"Enseignant", "Dr. Martin indisponible (déjà en cours)", "Mercredi 08h-10h", "B201", "Dr. Martin", "🟡 Moyenne", "Résoudre"},
            {"Groupe", "L3 Info déjà en cours à cette heure", "Jeudi 14h-16h", "C103", "Dr. Petit", "🟡 Moyenne", "Résoudre"},
            {"Équipement", "Salle sans VidéoProjecteur requis", "Vendredi 10h-12h", "B205", "Dr. Dubois", "🟢 Basse", "Résoudre"}
        };
        
        JTable conflitsTable = new JTable(donnees, colonnes);
        conflitsTable.setRowHeight(35);
        conflitsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(conflitsTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Suggestions
        JPanel suggestionsPanel = new JPanel();
        suggestionsPanel.setBackground(new Color(240, 255, 240));
        suggestionsPanel.setBorder(BorderFactory.createTitledBorder("Solutions proposées"));
        suggestionsPanel.setLayout(new BoxLayout(suggestionsPanel, BoxLayout.Y_AXIS));
        
        suggestionsPanel.add(new JLabel("• Déplacer 'Réseaux' vers B205 (disponible)"));
        suggestionsPanel.add(new JLabel("• Déplacer 'Anglais' vers C103 (disponible)"));
        suggestionsPanel.add(new JLabel("• Remplacer Dr. Martin par Dr. Bernard pour le cours de Mercredi"));
        
        panel.add(suggestionsPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createRapportsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Titre
        JLabel titre = new JLabel("Rapports de gestion");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titre, gbc);
        
        // Cartes de rapports
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        
        // Carte 1
        JPanel card1 = createReportCard(
            "📊 Occupation des salles",
            "Taux d'occupation, heures d'utilisation, salles critiques",
            "Générer",
            new Color(52, 152, 219)
        );
        gbc.gridx = 0;
        panel.add(card1, gbc);
        
        // Carte 2
        JPanel card2 = createReportCard(
            "👨‍🏫 Activité enseignants",
            "Heures effectuées, répartition par département",
            "Générer",
            new Color(46, 204, 113)
        );
        gbc.gridx = 1;
        panel.add(card2, gbc);
        
        // Carte 3
        JPanel card3 = createReportCard(
            "📚 Suivi des cours",
            "Cours planifiés vs réalisés, annulations",
            "Générer",
            new Color(155, 89, 182)
        );
        gbc.gridx = 2;
        panel.add(card3, gbc);
        
        // Deuxième ligne
        gbc.gridy = 2;
        
        JPanel card4 = createReportCard(
            "⚠️ Rapport des conflits",
            "Historique des conflits et résolutions",
            "Générer",
            new Color(230, 126, 34)
        );
        gbc.gridx = 0;
        panel.add(card4, gbc);
        
        JPanel card5 = createReportCard(
            "🔧 Utilisation équipements",
            "Taux d'utilisation, maintenance",
            "Générer",
            new Color(52, 73, 94)
        );
        gbc.gridx = 1;
        panel.add(card5, gbc);
        
        JPanel card6 = createReportCard(
            "📈 Statistiques globales",
            "KPIs, tendances, prévisions",
            "Générer",
            new Color(192, 57, 43)
        );
        gbc.gridx = 2;
        panel.add(card6, gbc);
        
        // Rapport personnalisé
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        JPanel customPanel = new JPanel(new FlowLayout());
        customPanel.setBackground(Color.WHITE);
        customPanel.setBorder(BorderFactory.createTitledBorder("Rapport personnalisé"));
        
        customPanel.add(new JLabel("Période:"));
        String[] periodes = {"Aujourd'hui", "Cette semaine", "Ce mois", "Ce semestre", "Cette année"};
        customPanel.add(new JComboBox<>(periodes));
        
        customPanel.add(new JLabel("Format:"));
        String[] formats = {"PDF", "Excel", "CSV", "HTML"};
        customPanel.add(new JComboBox<>(formats));
        
        customPanel.add(new JButton("📥 Générer"));
        
        panel.add(customPanel, gbc);
        
        return panel;
    }
    
    private JPanel createReportCard(String title, String description, String buttonText, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(color);
        
        JLabel descLabel = new JLabel("<html>" + description + "</html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JButton actionBtn = new JButton(buttonText);
        actionBtn.setBackground(color);
        actionBtn.setForeground(Color.WHITE);
        actionBtn.setFocusPainted(false);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(descLabel, BorderLayout.CENTER);
        card.add(actionBtn, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JPanel createCalendrierPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Barre de navigation
        JToolBar navBar = new JToolBar();
        navBar.setFloatable(false);
        navBar.setBackground(new Color(240, 240, 240));
        navBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        navBar.add(new JButton("◀"));
        navBar.add(new JLabel("Mars 2026"));
        navBar.add(new JButton("▶"));
        navBar.addSeparator(new Dimension(20, 10));
        navBar.add(new JButton("Aujourd'hui"));
        navBar.add(new JButton("Semaine"));
        navBar.add(new JButton("Mois"));
        
        panel.add(navBar, BorderLayout.NORTH);
        
        // Calendrier simplifié
        String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
        String[][] semaines = {
            {"2", "3", "4", "5", "6", "7", "8"},
            {"9", "10", "11", "12", "13", "14", "15"},
            {"16", "17", "18", "19", "20", "21", "22"},
            {"23", "24", "25", "26", "27", "28", "29"},
            {"30", "31", "1", "2", "3", "4", "5"}
        };
        
        JPanel calendarPanel = new JPanel(new GridLayout(6, 7, 2, 2));
        calendarPanel.setBackground(Color.WHITE);
        
        // En-têtes des jours
        for (String jour : jours) {
            JLabel jourLabel = new JLabel(jour, SwingConstants.CENTER);
            jourLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            jourLabel.setBackground(new Color(52, 152, 219));
            jourLabel.setForeground(Color.WHITE);
            jourLabel.setOpaque(true);
            jourLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            calendarPanel.add(jourLabel);
        }
        
        // Cases du calendrier
        for (String[] semaine : semaines) {
            for (String jour : semaine) {
                JPanel dayPanel = new JPanel(new BorderLayout());
                dayPanel.setBackground(Color.WHITE);
                dayPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
                
                JLabel dayLabel = new JLabel(jour, SwingConstants.RIGHT);
                dayLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
                dayLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
                dayPanel.add(dayLabel, BorderLayout.NORTH);
                
                // Indicateurs de cours
                if (jour.equals("9") || jour.equals("10") || jour.equals("11")) {
                    JPanel eventsPanel = new JPanel(new GridLayout(0, 1));
                    eventsPanel.setBackground(Color.WHITE);
                    eventsPanel.add(new JLabel("• Java (08h)"));
                    eventsPanel.add(new JLabel("• BD (10h)"));
                    dayPanel.add(eventsPanel, BorderLayout.CENTER);
                } else if (jour.equals("16")) {
                    JLabel event = new JLabel("• Réunion");
                    event.setFont(new Font("Segoe UI", Font.PLAIN, 9));
                    dayPanel.add(event, BorderLayout.CENTER);
                }
                
                calendarPanel.add(dayPanel);
            }
        }
        
        panel.add(calendarPanel, BorderLayout.CENTER);
        
        // Légende
        JPanel legendPanel = new JPanel(new FlowLayout());
        legendPanel.setBackground(new Color(240, 240, 240));
        legendPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        legendPanel.add(new JLabel("🟢 Disponible"));
        legendPanel.add(new JLabel("🔴 Occupé"));
        legendPanel.add(new JLabel("🟡 Partiellement"));
        legendPanel.add(new JLabel("📅 Événement spécial"));
        
        panel.add(legendPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void chargerDonnees() {
        System.out.println("Chargement des données gestionnaire...");
    }
    
    private void deconnexion() {
        int choix = JOptionPane.showConfirmDialog(this, 
            "Voulez-vous vous déconnecter ?",
            "Déconnexion",
            JOptionPane.YES_NO_OPTION);
        
        if (choix == JOptionPane.YES_OPTION) {
            dispose();
            new LoginInterface().setVisible(true);
        }
    }
}