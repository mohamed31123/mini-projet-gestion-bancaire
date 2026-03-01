package ma.bank.ui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Utilise le design natif de l'ordinateur (Windows, Mac, Linux)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true); // Lance la connexion
        });
    }
}