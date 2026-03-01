package ma.bank.ui;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class LoginFrame extends JFrame {

    // SIGN UP
    private JTextField txtEmailSignUp;
    private JPasswordField txtPasswordSignUp;
    private JButton btnCreate;

    // SIGN IN
    private JTextField txtEmailSignIn;
    private JPasswordField txtPasswordSignIn;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Banque - Connexion / Création de compte");
        setSize(450,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Tabbed Pane pour Sign In / Sign Up
        JTabbedPane tabs = new JTabbedPane();

        // --------------------
        // SIGN UP PANEL
        JPanel panelSignUp = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtEmailSignUp = new JTextField(20);
        txtPasswordSignUp = new JPasswordField(20);
        btnCreate = new JButton("Créer le compte");

        gbc.gridx = 0; gbc.gridy = 0;
        panelSignUp.add(new JLabel("Email :"), gbc);
        gbc.gridx = 1;
        panelSignUp.add(txtEmailSignUp, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelSignUp.add(new JLabel("Mot de passe :"), gbc);
        gbc.gridx = 1;
        panelSignUp.add(txtPasswordSignUp, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panelSignUp.add(btnCreate, gbc);

        tabs.addTab("Sign Up", panelSignUp);

        // --------------------
        // SIGN IN PANEL
        JPanel panelSignIn = new JPanel(new GridBagLayout());
        gbc.gridwidth = 1;

        txtEmailSignIn = new JTextField(20);
        txtPasswordSignIn = new JPasswordField(20);
        btnLogin = new JButton("Se connecter");

        gbc.gridx = 0; gbc.gridy = 0;
        panelSignIn.add(new JLabel("Email :"), gbc);
        gbc.gridx = 1;
        panelSignIn.add(txtEmailSignIn, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelSignIn.add(new JLabel("Mot de passe :"), gbc);
        gbc.gridx = 1;
        panelSignIn.add(txtPasswordSignIn, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panelSignIn.add(btnLogin, gbc);

        tabs.addTab("Sign In", panelSignIn);

        add(tabs);

        // --------------------
        // Action Sign Up
        btnCreate.addActionListener(e -> creerCompte());

        // Action Sign In (simple simulation, à remplacer par vérif DB)
        btnLogin.addActionListener(e -> {
            String email = txtEmailSignIn.getText();
            String pwd = new String(txtPasswordSignIn.getPassword());
            if(email.isEmpty() || pwd.isEmpty()){
                JOptionPane.showMessageDialog(this,"Remplissez tous les champs !");
            } else {
                // Ici tu peux appeler un service pour vérifier l'email/mot de passe
                JOptionPane.showMessageDialog(this,"Connexion réussie !");
                new MainMenu().setVisible(true);
                dispose();
            }
        });
    }

    // =================== SIGN UP
    private void creerCompte() {
        String email = txtEmailSignUp.getText();
        String pwd = new String(txtPasswordSignUp.getPassword());

        if (!email.contains("@") || pwd.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Email ou mot de passe invalide");
            return;
        }

        // 🔹 Envoi email confirmation
        envoyerEmail(email);
        JOptionPane.showMessageDialog(this,"Compte créé. Email envoyé !");

        // 🔹 Redirection vers menu
        new MainMenu().setVisible(true);
        dispose();
    }

    private void envoyerEmail(String to) {
        final String from = "m.eddinari5197@uca.ac.ma";
        final String password = "j h m g u u y s d m m p o x x n";

        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject("Création de compte bancaire");
            msg.setText("Votre compte a été créé avec succès.");

            Transport.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // =================== MAIN pour tester
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}