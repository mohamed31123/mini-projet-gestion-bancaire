package ma.bank.ui;

import ma.bank.model.Client;
import ma.bank.service.ClientService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientPanel extends JPanel {
    private JTextField txtNom, txtCategorie, txtVille;
    private JTable table;
    private ClientService service;

    public ClientPanel(ClientService service) {
        this.service = service;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Formulaire ---
        JPanel form = new JPanel(new GridLayout(2, 4, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Informations Client"));

        txtNom = new JTextField();
        txtCategorie = new JTextField();
        txtVille = new JTextField();
        JButton btnAdd = new JButton("Ajouter");
        JButton btnUpdate = new JButton("Modifier");

        form.add(new JLabel("Nom:")); form.add(txtNom);
        form.add(new JLabel("Catégorie:")); form.add(txtCategorie);
        form.add(new JLabel("Ville:")); form.add(txtVille);
        form.add(btnAdd); form.add(btnUpdate);

        // --- Table ---
        table = new JTable();
        refreshTable();

        // --- Actions ---
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDelete = new JButton("Supprimer");
        JButton btnRefresh = new JButton("Actualiser");
        actions.add(btnDelete);
        actions.add(btnRefresh);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);

        // --- Événements ---
        btnAdd.addActionListener(e -> {
            if (!txtNom.getText().trim().isEmpty() && !txtVille.getText().trim().isEmpty()) {
                service.ajouterClient(txtNom.getText(), txtCategorie.getText(), txtVille.getText());
                refreshTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Le nom et la ville sont obligatoires!");
            }
        });

        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                if (!txtNom.getText().trim().isEmpty() && !txtVille.getText().trim().isEmpty()) {
                    int id = (int) table.getValueAt(row, 0);
                    service.modifierClient(
                            id,
                            txtNom.getText(),
                            txtCategorie.getText(),
                            txtVille.getText()
                    );
                    refreshTable();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Le nom et la ville sont obligatoires!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez un client à modifier !");
            }
        });

        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Voulez-vous vraiment supprimer ce client?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    service.supprimerClient((int) table.getValueAt(row, 0));
                    refreshTable();
                    clearFields();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez un client à supprimer !");
            }
        });

        btnRefresh.addActionListener(e -> refreshTable());

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtNom.setText(table.getValueAt(row, 1) != null ? table.getValueAt(row, 1).toString() : "");
                    txtCategorie.setText(table.getValueAt(row, 2) != null ? table.getValueAt(row, 2).toString() : "");
                    txtVille.setText(table.getValueAt(row, 3) != null ? table.getValueAt(row, 3).toString() : "");
                }
            }
        });
    }

    private void refreshTable() {
        List<Client> clients = service.listerClients();
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nom", "Catégorie", "Ville"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Client c : clients) {
            model.addRow(new Object[]{c.getId(), c.getNom(), c.getCategorie(), c.getVille()});
        }
        table.setModel(model);
    }

    private void clearFields() {
        txtNom.setText("");
        txtCategorie.setText("");
        txtVille.setText("");
        table.clearSelection();
    }
}