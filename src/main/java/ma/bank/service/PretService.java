package ma.bank.service;




import ma.bank.dao.PretDAO;

public class PretService {
    private PretDAO dao = new PretDAO();

    public void accorderPret(int clientId, double montant, double taux) {
        dao.accorderPret(clientId, montant, taux);
    }

    public double suivreEncoursClient(int clientId) {
        return dao.encoursClient(clientId);
    }
}
