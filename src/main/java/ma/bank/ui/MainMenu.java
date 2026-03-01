package ma.bank.ui;

import ma.bank.service.ClientService;
import ma.bank.service.PretService;
import ma.bank.service.RemboursementService;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    private ClientService clientService = new ClientService();
    private PretService pretService = new PretService();
    private RemboursementService rembService = new RemboursementService();

    public MainMenu() {
        setTitle("Système de Gestion Bancaire v2.0");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Utilisation d'un JTabbedPane pour naviguer entre les entités
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Ajout des différents modules
        tabs.addTab("👥 Gestion Clients", createClientPanel());
        tabs.addTab("💰 Prêts & Crédits", createPretPanel());
        tabs.addTab("📉 Remboursements", createRemboursementPanel());

        // Header simple
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(41, 128, 185));
        JLabel title = new JLabel(" BANK MANAGEMENT SYSTEM", JLabel.LEFT);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setPreferredSize(new Dimension(0, 50));
        header.add(title, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
    }

    // --- MODULE CLIENTS (Réutilisation de votre logique ClientForm) ---
    private JPanel createClientPanel() {
        // On peut simplement instancier le contenu de votre ClientForm ici
        // Pour faire propre, on l'encapsule dans un JPanel
        return new ClientPanel(clientService);
    }

    // --- MODULE PRÊTS ---
    private JPanel createPretPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Accorder un nouveau prêt"));

        JTextField txtClientId = new JTextField();
        JTextField txtMontant = new JTextField();
        JTextField txtTaux = new JTextField();
        JButton btnAccorder = new JButton("Valider le Prêt");
        btnAccorder.setBackground(new Color(46, 204, 113));
        btnAccorder.setForeground(Color.WHITE);

        form.add(new JLabel("ID Client :")); form.add(txtClientId);
        form.add(new JLabel("Montant (DH) :")); form.add(txtMontant);
        form.add(new JLabel("Taux (%) :")); form.add(txtTaux);
        form.add(new JLabel("")); form.add(btnAccorder);

        btnAccorder.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtClientId.getText());
                double mt = Double.parseDouble(txtMontant.getText());
                double tx = Double.parseDouble(txtTaux.getText());
                pretService.accorderPret(id, mt, tx);
                JOptionPane.showMessageDialog(this, "Prêt accordé avec succès !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        panel.add(form, BorderLayout.NORTH);
        return panel;
    }

    // --- MODULE REMBOURSEMENTS ---
    private JPanel createRemboursementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Enregistrer un paiement"));

        JTextField txtPretId = new JTextField();
        JTextField txtMontantRemb = new JTextField();
        JButton btnPayer = new JButton("Enregistrer le Remboursement");
        btnPayer.setBackground(new Color(52, 152, 219));
        btnPayer.setForeground(Color.WHITE);

        form.add(new JLabel("ID Prêt :")); form.add(txtPretId);
        form.add(new JLabel("Montant à payer :")); form.add(txtMontantRemb);
        form.add(new JLabel("")); form.add(btnPayer);

        btnPayer.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtPretId.getText());
                double mt = Double.parseDouble(txtMontantRemb.getText());
                rembService.enregistrerRemboursement(id, mt);
                double reste = rembService.consulterEncoursPret(id);
                JOptionPane.showMessageDialog(this, "Paiement réussi ! Reste : " + reste + " DH");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        panel.add(form, BorderLayout.NORTH);
        return panel;
    }
}