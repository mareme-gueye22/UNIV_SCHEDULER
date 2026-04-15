package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.Etudiant;

@SuppressWarnings("unused")
public class EtudiantDashboard extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private Etudiant etudiantConnecte;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    public EtudiantDashboard(Etudiant etudiant) {
        this.etudiantConnecte = etudiant;
        initUI();
    }
    
    private void initUI() {
        setTitle("UNIV-SCHEDULER - Étudiant : " + etudiantConnecte.getNom());
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel menuPanel = createMenuPanel();
        add(menuPanel, BorderLayout.WEST);
        
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(createEmploiDuTempsPanel(), "EMPLOI");
        contentPanel.add(createRechercheSallePanel(), "RECHERCHE");
        contentPanel.add(createNotesPanel(), "NOTES");
        contentPanel.add(createProfilPanel(), "PROFIL");
        add(contentPanel, BorderLayout.CENTER);
        
        cardLayout.show(contentPanel, "EMPLOI");
    }
    
    @SuppressWarnings("unused")
	private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(155, 89, 182));
        panel.setPreferredSize(new Dimension(200, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        JLabel title = new JLabel("ÉTUDIANT");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(title, gbc);
        
        String[] items = {"📅 Emploi du temps", "🔍 Rechercher salle", "📚 Mes notes", "👤 Profil"};
        String[] cards = {"EMPLOI", "RECHERCHE", "NOTES", "PROFIL"};
        for (int i = 0; i < items.length; i++) {
            JButton btn = new JButton(items[i]);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(155, 89, 182));
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
    
    private JPanel createEmploiDuTempsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        
        JLabel info = new JLabel("Classe: " + etudiantConnecte.getFiliere() + " " + etudiantConnecte.getNiveau() + " - Groupe " + etudiantConnecte.getGroupe());
        info.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(info, BorderLayout.NORTH);
        
        String[] colonnes = {"Jour", "Horaire", "Matière", "Enseignant", "Salle"};
        Object[][] donnees = {
            {"Lundi", "08h-10h", "Java", "Dr. Martin", "A101"},
            {"Lundi", "10h-12h", "BD", "Dr. Bernard", "A105"},
            {"Mardi", "08h-10h", "Réseaux", "Dr. Dubois", "B201"}
        };
        JTable table = new JTable(donnees, colonnes);
        table.setRowHeight(35);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createRechercheSallePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        JLabel titre = new JLabel("Rechercher une salle libre");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(titre, BorderLayout.NORTH);
        
        JPanel search = new JPanel(new FlowLayout());
        search.add(new JLabel("Date:"));
        search.add(new JTextField("08/03/2026", 8));
        search.add(new JButton("Rechercher"));
        panel.add(search, BorderLayout.CENTER);
        
        String[] colonnes = {"Salle", "Bâtiment", "Disponibilité"};
        Object[][] donnees = {
            {"A108", "A", "8h-20h"},
            {"B215", "B", "14h-18h"},
            {"Bibliothèque", "C", "8h-22h"}
        };
        JTable table = new JTable(donnees, colonnes);
        panel.add(new JScrollPane(table), BorderLayout.SOUTH);
        return panel;
    }
    
    private JPanel createNotesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        JLabel titre = new JLabel("Mes notes");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(titre, BorderLayout.NORTH);
        
        String[] colonnes = {"Matière", "Note", "Appréciation"};
        Object[][] donnees = {
            {"Java", "15/20", "Bien"},
            {"BD", "12/20", "Assez bien"},
            {"Réseaux", "08/20", "Insuffisant"}
        };
        JTable table = new JTable(donnees, colonnes);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createProfilPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel avatar = new JLabel("👨‍🎓");
        avatar.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 3;
        panel.add(avatar, gbc);
        
        gbc.gridheight = 1; gbc.gridx = 1; gbc.gridy = 0;
        panel.add(new JLabel("Nom: " + etudiantConnecte.getNom()), gbc);
        gbc.gridy = 1;
        panel.add(new JLabel("Numéro: " + etudiantConnecte.getNumeroEtudiant()), gbc);
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
    
    private void deconnexion() {
        int choix = JOptionPane.showConfirmDialog(this, "Déconnexion ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choix == JOptionPane.YES_OPTION) {
            dispose();
            new LoginInterface().setVisible(true);
        }
    }
}