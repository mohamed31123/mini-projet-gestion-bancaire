package ma.bank.service;



import ma.bank.dao.PretDAO;
import ma.bank.dao.RemboursementDAO;

public class RemboursementService {

    private final RemboursementDAO remboursementDAO = new RemboursementDAO();
    private final PretDAO pretDAO = new PretDAO();

    // 1️⃣ Enregistrer un remboursement (cas bancaire réel)
    public void enregistrerRemboursement(int pretId, double montant) {

        if (montant <= 0)
            throw new IllegalArgumentException("Montant invalide");

        double encours = pretDAO.encoursClientByPret(pretId);

        if (montant > encours)
            throw new IllegalArgumentException("Montant supérieur à l'encours");

        remboursementDAO.enregistrer(pretId, montant);
    }

    // 2️⃣ Consulter encours restant pour un prêt
    public double consulterEncoursPret(int pretId) {
        return pretDAO.encoursClientByPret(pretId);
    }

    // 3️⃣ Vérifier si prêt est totalement remboursé
    public boolean pretSolde(int pretId) {
        return consulterEncoursPret(pretId) == 0;
    }

    // 4️⃣ Total remboursé pour un prêt (cas réel)
    public double totalRembourse(int pretId) {
        return remboursementDAO.totalRembourseParPret(pretId);
    }
}