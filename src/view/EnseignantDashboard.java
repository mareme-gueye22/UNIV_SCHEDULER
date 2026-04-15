package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.Enseignant;
import service.CoursService;
import model.Cours;

@SuppressWarnings("unused")
public class EnseignantDashboard extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private Enseignant enseignantConnecte;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JTable tableCours;
    private DefaultTableModel tableModel;
    private CoursService coursService;
    
    public EnseignantDashboard(Enseignant enseignant) {
        this.enseignantConnecte = enseignant;
        this.coursService = new CoursService();
        initUI();
        chargerDonnees();
    }
	private void initUI() {
        setTitle("UNIV-SCHEDULER - Enseignant : " + enseignantConnecte.getNom());
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Menu latéral
        JPanel menuPanel = createMenuPanel();
        add(menuPanel, BorderLayout.WEST);
        
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(createMesCoursPanel(), "COURS");
        contentPanel.add(createPlanifierPanel(), "PLANIFIER");
        contentPanel.add(createRechercheSallePanel(), "RECHERCHE");
        contentPanel.add(createProfilPanel(), "PROFIL");
        add(contentPanel, BorderLayout.CENTER);
        
        cardLayout.show(contentPanel, "COURS");
    }
    
    @SuppressWarnings("unused")
	private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(39, 174, 96));
        panel.setPreferredSize(new Dimension(200, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        JLabel title = new JLabel("ENSEIGNANT");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(title, gbc);
        
        String[] items = {"📅 Mes cours", "➕ Planifier", "🔍 Rechercher salle", "👤 Profil"};
        String[] cards = {"COURS", "PLANIFIER", "RECHERCHE", "PROFIL"};
        for (int i = 0; i < items.length; i++) {
            JButton btn = new JButton(items[i]);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(39, 174, 96));
            btn.setBorderPainted(false);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            final String card = cards[i];
            btn.addActionListener(e -> cardLayout.show(contentPanel, card));
            panel.add(btn, gbc);
        }
        gbc.weighty = 1.0;
        panel.add(Box.createVerticalGlue(), gbc);
        JButton logout = new JButton("🚪 Déconnexion");
        logout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logout.setForeground(Color.WHITE);
        logout.setBackground(new Color(192, 57, 43));
        logout.addActionListener(e -> deconnexion());
        panel.add(logout, gbc);
        return panel;
    }
    
    private JPanel createMesCoursPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        
        JLabel titre = new JLabel("Mes cours");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(titre, BorderLayout.NORTH);
        
        String[] colonnes = {"ID", "Matière", "Classe", "Jour", "Horaire", "Salle", "Statut"};
        tableModel = new DefaultTableModel(colonnes, 0);
        tableCours = new JTable(tableModel);
        tableCours.setRowHeight(30);
        panel.add(new JScrollPane(tableCours), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createPlanifierPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titre = new JLabel("Planifier une séance");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titre, gbc);
        
        gbc.gridwidth = 1; gbc.gridy = 1;
        panel.add(new JLabel("Matière:"), gbc);
        gbc.gridx = 1;
        JTextField matiereField = new JTextField(15);
        panel.add(matiereField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Classe:"), gbc);
        gbc.gridx = 1;
        JTextField classeField = new JTextField(15);
        panel.add(classeField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Jour:"), gbc);
        gbc.gridx = 1;
        String[] jours = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi"};
        JComboBox<String> jourCombo = new JComboBox<>(jours);
        panel.add(jourCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Horaire:"), gbc);
        gbc.gridx = 1;
        String[] heures = {"08:00","10:00","14:00","16:00"};
        JComboBox<String> heureCombo = new JComboBox<>(heures);
        panel.add(heureCombo, gbc);
        
        JButton btn = new JButton("✅ Planifier");
        btn.setBackground(new Color(46, 204, 113));
        btn.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(btn, gbc);
        
        btn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Cours planifié (simulation)");
            // Ici vous appelleriez le service pour enregistrer
        });
        return panel;
    }
    
    private JPanel createRechercheSallePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        JLabel titre = new JLabel("Rechercher une salle");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(titre, BorderLayout.NORTH);
        
        JPanel search = new JPanel(new FlowLayout());
        search.add(new JLabel("Date:"));
        search.add(new JTextField("10/03/2026", 8));
        search.add(new JLabel("Capacité:"));
        search.add(new JTextField("30", 5));
        search.add(new JButton("🔍 Rechercher"));
        panel.add(search, BorderLayout.CENTER);
        
        String[] colonnes = {"Salle", "Capacité", "Disponibilité"};
        Object[][] data = {
            {"A101", "150", "08h-12h"},
            {"B205", "40", "14h-18h"},
            {"C103", "60", "Toute journée"}
        };
        JTable table = new JTable(data, colonnes);
        panel.add(new JScrollPane(table), BorderLayout.SOUTH);
        return panel;
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
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 3;
        panel.add(avatar, gbc);
        
        gbc.gridheight = 1; gbc.gridx = 1; gbc.gridy = 0;
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
        // Données factices
        tableModel.addRow(new Object[]{"1", "Java", "L3 Info", "Lundi", "08h-10h", "A101", "✅"});
        tableModel.addRow(new Object[]{"2", "BD", "L3 Info", "Mardi", "10h-12h", "A105", "⏳"});
    }
    
    private void deconnexion() {
        int choix = JOptionPane.showConfirmDialog(this, "Déconnexion ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choix == JOptionPane.YES_OPTION) {
            dispose();
            new LoginInterface().setVisible(true);
        }
    }
}