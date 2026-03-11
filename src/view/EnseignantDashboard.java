package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import model.Enseignant;
import model.Cours;

@SuppressWarnings("unused")
public class EnseignantDashboard extends JFrame {
	
	private static final long serialVersionUID = 1L;
    private Enseignant enseignantConnecte;
    private JTabbedPane tabbedPane;
    private JTable tableCours;
    private DefaultTableModel tableModel;
    private JLabel welcomeLabel;
    
    public EnseignantDashboard(Enseignant enseignant) {
        this.enseignantConnecte = enseignant;
        initUI();
        chargerDonnees();
    }
    
    private void initUI() {
        setTitle("UNIV-SCHEDULER - Dashboard Enseignant : " + enseignantConnecte.getNom());
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
        
        JMenu coursMenu = new JMenu("Cours");
        JMenuItem nouveauCoursItem = new JMenuItem("Nouvelle séance");
        JMenuItem mesCoursItem = new JMenuItem("Mes cours");
        coursMenu.add(nouveauCoursItem);
        coursMenu.add(mesCoursItem);
        
        JMenu aideMenu = new JMenu("Aide");
        JMenuItem aproposItem = new JMenuItem("À propos");
        aideMenu.add(aproposItem);
        
        menuBar.add(fichierMenu);
        menuBar.add(coursMenu);
        menuBar.add(aideMenu);
        setJMenuBar(menuBar);
        
        // Actions des menus
        deconnexionItem.addActionListener(e -> deconnexion());
        quitterItem.addActionListener(e -> System.exit(0));
        nouveauCoursItem.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        mesCoursItem.addActionListener(e -> tabbedPane.setSelectedIndex(0));
        aproposItem.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, 
                "UNIV-SCHEDULER v1.0\nModule Enseignant\nGérez vos cours et séances", 
                "À propos", 
                JOptionPane.INFORMATION_MESSAGE)
        );
        
        // Panneau avec en-tête personnalisé
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        welcomeLabel = new JLabel("Bienvenue, " + enseignantConnecte.getNom() + " (" + enseignantConnecte.getGrade() + ")");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);
        
        JLabel deptLabel = new JLabel("Département: " + enseignantConnecte.getDepartement());
        deptLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        deptLabel.setForeground(Color.WHITE);
        
        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        headerPanel.add(deptLabel, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Panneau avec onglets
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        tabbedPane.addTab("📅 Mes Cours", createMesCoursPanel());
        tabbedPane.addTab("➕ Planifier Séance", createPlanifierPanel());
        tabbedPane.addTab("🔍 Rechercher Salle", createRechercheSallePanel());
        tabbedPane.addTab("📊 Statistiques", createStatistiquesPanel());
        tabbedPane.addTab("👤 Mon Profil", createProfilPanel());
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel createMesCoursPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Panneau de filtre
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setBackground(new Color(240, 240, 240));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        filterPanel.add(new JLabel("Filtrer par:"));
        String[] filterOptions = {"Tous les cours", "Cette semaine", "Ce mois", "À venir", "Passés"};
        JComboBox<String> filterCombo = new JComboBox<>(filterOptions);
        filterPanel.add(filterCombo);
        
        filterPanel.add(new JLabel("Matière:"));
        String[] matieres = {"Toutes", "Programmation Java", "Base de données", "Réseaux", "Algorithmique"};
        JComboBox<String> matiereCombo = new JComboBox<>(matieres);
        filterPanel.add(matiereCombo);
        
        filterPanel.add(new JButton("🔍 Appliquer"));
        
        panel.add(filterPanel, BorderLayout.NORTH);
        
        // Tableau des cours
        String[] colonnes = {"ID", "Matière", "Classe", "Groupe", "Jour", "Horaire", "Salle", "Statut", "Présences"};
        tableModel = new DefaultTableModel(colonnes, 0);
        
        tableCours = new JTable(tableModel);
        tableCours.setRowHeight(35);
        tableCours.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableCours.getTableHeader().setBackground(new Color(240, 240, 240));
        tableCours.setSelectionBackground(new Color(210, 230, 250));
        
        JScrollPane scrollPane = new JScrollPane(tableCours);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panneau de statistiques rapides
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        statsPanel.add(createQuickStat("Cours cette semaine", "8", new Color(52, 152, 219)));
        statsPanel.add(createQuickStat("Heures effectuées", "24h", new Color(46, 204, 113)));
        statsPanel.add(createQuickStat("Étudiants", "120", new Color(155, 89, 182)));
        statsPanel.add(createQuickStat("Taux présence", "85%", new Color(230, 126, 34)));
        
        panel.add(statsPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createQuickStat(String label, String value, Color color) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        valueLabel.setForeground(color);
        
        JLabel labelLabel = new JLabel(label, SwingConstants.CENTER);
        labelLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        panel.add(valueLabel, BorderLayout.CENTER);
        panel.add(labelLabel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createPlanifierPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Titre
        JLabel titre = new JLabel("Planifier une nouvelle séance");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titre, gbc);
        
        // Sous-titre
        JLabel soustitre = new JLabel("Remplissez les informations ci-dessous");
        soustitre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        soustitre.setForeground(Color.GRAY);
        gbc.gridy = 1;
        panel.add(soustitre, gbc);
        
        // Formulaire simplifié
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(new JLabel("Matière:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        String[] matieres = {"Programmation Java", "Base de données", "Réseaux", "Algorithmique"};
        JComboBox<String> matiereCombo = new JComboBox<>(matieres);
        panel.add(matiereCombo, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(new JLabel("Classe:"), gbc);
        gbc.gridx = 1;
        String[] classes = {"L3 Info", "L2 Info", "M1 Info"};
        JComboBox<String> classeCombo = new JComboBox<>(classes);
        panel.add(classeCombo, gbc);
        
        gbc.gridx = 2;
        String[] groupes = {"Groupe A", "Groupe B"};
        JComboBox<String> groupeCombo = new JComboBox<>(groupes);
        panel.add(groupeCombo, gbc);
        
        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(new JLabel("Jour:"), gbc);
        gbc.gridx = 1;
        String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
        JComboBox<String> jourCombo = new JComboBox<>(jours);
        panel.add(jourCombo, gbc);
        
        gbc.gridx = 2;
        panel.add(new JLabel("Heure:"), gbc);
        gbc.gridx = 3;
        String[] heures = {"08:00", "10:00", "14:00", "16:00"};
        JComboBox<String> heureCombo = new JComboBox<>(heures);
        panel.add(heureCombo, gbc);
        
        // Bouton
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        JButton planifierButton = new JButton("✅ Planifier la séance");
        planifierButton.setBackground(new Color(46, 204, 113));
        planifierButton.setForeground(Color.WHITE);
        planifierButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        planifierButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Séance planifiée avec succès!");
            tabbedPane.setSelectedIndex(0);
        });
        panel.add(planifierButton, gbc);
        
        return panel;
    }
    
    private JPanel createRechercheSallePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titre = new JLabel("Rechercher une salle disponible");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel.add(titre, BorderLayout.NORTH);
        
        JPanel recherchePanel = new JPanel(new FlowLayout());
        recherchePanel.setBackground(new Color(240, 240, 240));
        recherchePanel.add(new JLabel("Date:"));
        recherchePanel.add(new JTextField("10/03/2026", 8));
        recherchePanel.add(new JLabel("Capacité:"));
        recherchePanel.add(new JTextField("30", 5));
        recherchePanel.add(new JButton("🔍 Rechercher"));
        
        panel.add(recherchePanel, BorderLayout.CENTER);
        
        String[] colonnes = {"Salle", "Capacité", "Disponibilité"};
        Object[][] donnees = {
            {"A101", "150", "08h-12h, 14h-18h"},
            {"B205", "40", "14h-18h"},
            {"C103", "60", "Toute la journée"}
        };
        
        JTable resultatsTable = new JTable(donnees, colonnes);
        panel.add(new JScrollPane(resultatsTable), BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createStatistiquesPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 20));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(createStatCard("Heures effectuées", "124h", new Color(52, 152, 219)));
        panel.add(createStatCard("Présence moyenne", "87%", new Color(46, 204, 113)));
        panel.add(createStatCard("Cours planifiés", "24", new Color(155, 89, 182)));
        panel.add(createStatCard("Étudiants", "120", new Color(230, 126, 34)));
        
        return panel;
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        valueLabel.setForeground(color);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createProfilPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel avatar = new JLabel("👨‍🏫");
        avatar.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        panel.add(avatar, gbc);
        
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JLabel("Nom: " + enseignantConnecte.getNom()), gbc);
        
        gbc.gridy = 1;
        panel.add(new JLabel("Email: " + enseignantConnecte.getEmail()), gbc);
        
        gbc.gridy = 2;
        panel.add(new JLabel("Département: " + enseignantConnecte.getDepartement()), gbc);
        
        gbc.gridy = 3;
        panel.add(new JLabel("Grade: " + enseignantConnecte.getGrade()), gbc);
        
        return panel;
    }
    
    private void chargerDonnees() {
        tableModel.setRowCount(0);
        Object[][] cours = {
            {"1", "Programmation Java", "L3 Info", "Groupe A", "Lundi", "08h-10h", "A101", "✅ Confirmé", "42/45"},
            {"2", "Base de données", "L3 Info", "Groupe A", "Lundi", "10h-12h", "A105", "✅ Confirmé", "40/45"}
        };
        
        for (Object[] c : cours) {
            tableModel.addRow(c);
        }
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