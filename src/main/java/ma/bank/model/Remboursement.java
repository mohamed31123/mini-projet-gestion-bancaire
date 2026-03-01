package ma.bank.model;



import java.time.LocalDate;

public class Remboursement {
    private int id;
    private Pret pret;
    private LocalDate date;
    private double montant;

    public Remboursement() {}

    public Remboursement(int id, Pret pret, LocalDate date, double montant) {
        this.id = id;
        this.pret = pret;
        this.date = date;
        this.montant = montant;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
}