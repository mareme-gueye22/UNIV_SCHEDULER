package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("unused")
public class GestionnaireDashboard extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JTable tableCours;
    private DefaultTableModel tableModelCours;
    
    public GestionnaireDashboard() {
        initUI();
        chargerDonnees();
    }
    
    private void initUI() {
        setTitle("UNIV-SCHEDULER - Administrateur");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Menu latéral
        JPanel menuPanel = createMenuPanel();
        if (menuPanel == null) {
            throw new IllegalStateException("menuPanel ne peut pas être null");
        }
        add(menuPanel, BorderLayout.WEST);

        // Panneau de contenu avec CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Ajout des vues (vérifier qu'aucune n'est null)
        JPanel dash = createDashboardPanel();
        if (dash == null) throw new IllegalStateException("dashboardPanel null");
        contentPanel.add(dash, "DASHBOARD");

        JPanel users = createUsersPanel();
        if (users == null) throw new IllegalStateException("usersPanel null");
        contentPanel.add(users, "USERS");

        // ... autres vues ...

        add(contentPanel, BorderLayout.CENTER);

        // Vue par défaut
        cardLayout.show(contentPanel, "DASHBOARD");
    }
    
    private JPanel createUsersPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	private JPanel createDashboardPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(41, 128, 185));
        panel.setPreferredSize(new Dimension(220, 0));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        JLabel title = new JLabel("GESTIONNAIRE");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(title, gbc);
        
        String[] items = {"📅 Planification", "🏢 Salles", "👨‍🏫 Enseignants", "⚠️ Conflits", "📊 Rapports", "🗓️ Calendrier"};
        String[] cards = {"PLANIF", "SALLES", "ENSEIGNANTS", "CONFLITS", "RAPPORTS", "CALENDRIER"};
        
        for (int i = 0; i < items.length; i++) {
            JButton btn = new JButton(items[i]);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(41, 128, 185));
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
    
    private JPanel createPlanificationPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(new JButton("➕ Nouveau cours"));
        toolBar.addSeparator();
        toolBar.add(new JButton("✏️ Modifier"));
        toolBar.addSeparator();
        toolBar.add(new JButton("🗑️ Supprimer"));
        panel.add(toolBar, BorderLayout.NORTH);
        
        String[] colonnes = {"Matière", "Classe", "Enseignant", "Jour", "Horaire", "Salle", "Statut"};
        Object[][] donnees = {
            {"Java", "L3 Info", "Dr. Martin", "Lundi", "08h-10h", "A101", "✅ Confirmé"},
            {"BD", "L3 Info", "Dr. Bernard", "Lundi", "10h-12h", "A105", "✅ Confirmé"},
            {"Réseaux", "M1 Info", "Dr. Dubois", "Mardi", "08h-10h", "B201", "⏳ En attente"}
        };
        tableModelCours = new DefaultTableModel(donnees, colonnes);
        tableCours = new JTable(tableModelCours);
        tableCours.setRowHeight(30);
        panel.add(new JScrollPane(tableCours), BorderLayout.CENTER);
        
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.setBackground(new Color(240, 240, 240));
        bottom.add(new JLabel("Total: 3 cours"));
        panel.add(bottom, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createSallesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        String[] colonnes = {"Salle", "Capacité", "Type", "État"};
        Object[][] donnees = {
            {"A101", "150", "Amphi", "🟢 Disponible"},
            {"A105", "40", "TD", "🔴 Occupée"},
            {"B201", "30", "TP", "🟡 Maintenance"}
        };
        JTable table = new JTable(donnees, colonnes);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createEnseignantsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        String[] colonnes = {"Nom", "Email", "Département", "Grade"};
        Object[][] donnees = {
            {"Dr. Martin", "martin@univ.fr", "Info", "Professeur"},
            {"Dr. Bernard", "bernard@univ.fr", "Info", "MCF"},
            {"Dr. Dubois", "dubois@univ.fr", "Réseaux", "Professeur"}
        };
        JTable table = new JTable(donnees, colonnes);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createConflitsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel title = new JLabel("⚠️ Conflits détectés");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(Color.RED);
        panel.add(title, BorderLayout.NORTH);
        
        String[] colonnes = {"Description", "Priorité"};
        Object[][] donnees = {
            {"Salle A101 double réservée (Java/Réseaux)", "Haute"},
            {"Dr. Martin indisponible", "Moyenne"}
        };
        JTable table = new JTable(donnees, colonnes);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        JButton resolve = new JButton("Résoudre automatiquement");
        panel.add(resolve, BorderLayout.SOUTH);
        return panel;
    }
    
    private JPanel createRapportsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        panel.add(new JLabel("Générer un rapport"), gbc);
        gbc.gridy = 1;
        panel.add(new JButton("📥 Générer PDF"), gbc);
        return panel;
    }
    
    private JPanel createCalendrierPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(new JLabel("Calendrier (simulation)", SwingConstants.CENTER), BorderLayout.CENTER);
        return panel;
    }
    
    private void chargerDonnees() {}
    
    private void deconnexion() {
        int choix = JOptionPane.showConfirmDialog(this, "Déconnexion ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choix == JOptionPane.YES_OPTION) {
            dispose();
            new LoginInterface().setVisible(true);
        }
    }
}