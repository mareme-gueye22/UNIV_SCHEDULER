package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.Etudiant;

public class EtudiantDashboard extends JFrame {
	private static final long serialVersionUID = 1L;
    private Etudiant etudiantConnecte;
    private JTabbedPane tabbedPane;
    private DefaultTableModel tableModelEmploi;
    
    public EtudiantDashboard(Etudiant etudiant) {
        this.etudiantConnecte = etudiant;
        initUI();
    }
    
    @SuppressWarnings("unused")
	private void initUI() {
        setTitle("UNIV-SCHEDULER - Dashboard Étudiant : " + etudiantConnecte.getNom());
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fichierMenu = new JMenu("Fichier");
        JMenuItem deconnexionItem = new JMenuItem("Déconnexion");
        fichierMenu.add(deconnexionItem);
        menuBar.add(fichierMenu);
        setJMenuBar(menuBar);
        
        deconnexionItem.addActionListener(e -> deconnexion());
        
        // Onglets
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("📅 Emploi du temps", createEmploiDuTempsPanel());
        tabbedPane.addTab("🔍 Rechercher salle", createRechercheSallePanel());
        tabbedPane.addTab("📚 Mes cours", createMesCoursPanel());
        tabbedPane.addTab("👤 Mon profil", createProfilPanel());
        
        add(tabbedPane);
    }
    
    private JPanel createEmploiDuTempsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // En-tête avec info classe
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel infoClasse = new JLabel("Classe: " + etudiantConnecte.getFiliere() + " " + 
                                       etudiantConnecte.getNiveau() + " - Groupe " + 
                                       etudiantConnecte.getGroupe());
        infoClasse.setFont(new Font("Segoe UI", Font.BOLD, 16));
        headerPanel.add(infoClasse);
        panel.add(headerPanel, BorderLayout.NORTH);
        
        // Tableau emploi du temps
        String[] colonnes = {"Jour", "Horaire", "Matière", "Enseignant", "Salle"};
        Object[][] donnees = {
            {"Lundi", "08h-10h", "Programmation Java", "Dr. Martin", "A101"},
            {"Lundi", "10h-12h", "Base de données", "Dr. Bernard", "A105"},
            {"Mardi", "08h-10h", "Réseaux", "Dr. Dubois", "B201"},
            {"Mercredi", "14h-16h", "Algorithmique", "Dr. Petit", "C103"},
            {"Jeudi", "10h-12h", "Anglais technique", "Dr. Smith", "B205"},
            {"Vendredi", "08h-12h", "Projet", "Dr. Martin", "TP Info"}
        };
        
        tableModelEmploi = new DefaultTableModel(donnees, colonnes);
        JTable table = new JTable(tableModelEmploi);
        table.setRowHeight(35);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createRechercheSallePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel titre = new JLabel("Rechercher une salle libre pour étudier");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(titre, BorderLayout.NORTH);
        
        // Panneau recherche
        JPanel recherchePanel = new JPanel(new FlowLayout());
        recherchePanel.add(new JLabel("Date:"));
        JTextField dateField = new JTextField("04/03/2026", 10);
        recherchePanel.add(dateField);
        
        recherchePanel.add(new JLabel("Période:"));
        String[] periodes = {"Matin (8h-12h)", "Après-midi (14h-18h)", "Soir (18h-20h)"};
        JComboBox<String> periodeCombo = new JComboBox<>(periodes);
        recherchePanel.add(periodeCombo);
        
        JButton rechercherButton = new JButton("Rechercher");
        recherchePanel.add(rechercherButton);
        
        panel.add(recherchePanel, BorderLayout.CENTER);
        
        // Résultats
        String[] colonnes = {"Salle", "Bâtiment", "Capacité", "Disponibilité", "Action"};
        Object[][] donnees = {
            {"A108", "A", "30 places", "8h-20h", "Réserver"},
            {"B215", "B", "20 places", "14h-18h", "Réserver"},
            {"Bibliothèque", "C", "50 places", "8h-22h", "Accès libre"}
        };
        
        JTable resultatsTable = new JTable(donnees, colonnes);
        panel.add(new JScrollPane(resultatsTable), BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createMesCoursPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel titre = new JLabel("Mes notes et absences");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(titre, BorderLayout.NORTH);
        
        // Tableau des notes
        String[] colonnesNotes = {"Matière", "Coefficient", "Note", "Appréciation"};
        Object[][] donneesNotes = {
            {"Programmation Java", "3", "15/20", "Bien"},
            {"Base de données", "2", "12/20", "Assez bien"},
            {"Réseaux", "2", "08/20", "Insuffisant"},
            {"Algorithmique", "3", "14/20", "Bien"}
        };
        
        JTable notesTable = new JTable(donneesNotes, colonnesNotes);
        notesTable.setRowHeight(30);
        
        JScrollPane scrollPane = new JScrollPane(notesTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Statistiques
        JPanel statsPanel = new JPanel(new GridLayout(1, 3));
        statsPanel.add(creerStatCard("Moyenne générale", "12.5/20", new Color(52, 152, 219)));
        statsPanel.add(creerStatCard("Absences", "3", new Color(230, 126, 34)));
        statsPanel.add(creerStatCard("Rang", "8/25", new Color(46, 204, 113)));
        
        panel.add(statsPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createProfilPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Avatar
        JLabel avatar = new JLabel("👨‍🎓");
        avatar.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        panel.add(avatar, gbc);
        
        // Infos
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JLabel("Nom: " + etudiantConnecte.getNom()), gbc);
        
        gbc.gridy = 1;
        panel.add(new JLabel("Numéro étudiant: " + etudiantConnecte.getNumeroEtudiant()), gbc);
        
        gbc.gridy = 2;
        panel.add(new JLabel("Email: " + etudiantConnecte.getEmail()), gbc);
        
        gbc.gridy = 3;
        panel.add(new JLabel("Filière: " + etudiantConnecte.getFiliere()), gbc);
        
        gbc.gridy = 4;
        panel.add(new JLabel("Niveau: " + etudiantConnecte.getNiveau()), gbc);
        
        gbc.gridy = 5;
        panel.add(new JLabel("Groupe: " + etudiantConnecte.getGroupe()), gbc);
        
        return panel;
    }
    
    private JPanel creerStatCard(String titre, String valeur, Color couleur) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setBackground(Color.WHITE);
        
        JLabel titreLabel = new JLabel(titre);
        titreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        titreLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        
        JLabel valeurLabel = new JLabel(valeur);
        valeurLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        valeurLabel.setForeground(couleur);
        valeurLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        
        card.add(titreLabel, BorderLayout.NORTH);
        card.add(valeurLabel, BorderLayout.CENTER);
        
        return card;
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