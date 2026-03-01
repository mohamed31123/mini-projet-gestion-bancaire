package ma.bank.model;

import java.time.LocalDate;

public class Pret {
    private int id;
    private Client client;
    private double montant;
    private double taux;
    private LocalDate dateDebut;

    public Pret() {}

    public Pret(int id, Client client, double montant, double taux, LocalDate dateDebut) {
        this.id = id;
        this.client = client;
        this.montant = montant;
        this.taux = taux;
        this.dateDebut = dateDebut;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public double getTaux() { return taux; }
    public void setTaux(double taux) { this.taux = taux; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
}