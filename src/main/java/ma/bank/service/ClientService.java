package ma.bank.service;

import ma.bank.dao.ClientDAO;
import ma.bank.model.Client;
import java.util.List;

public class ClientService {

    private final ClientDAO clientDAO = new ClientDAO();

    // 1️⃣ Ajouter un nouveau client - CORRIGÉ !
    public void ajouterClient(String nom, String categorie, String ville) {
        if (nom == null || nom.trim().isEmpty())
            throw new IllegalArgumentException("Nom client obligatoire");

        // ✅ Utilisation du constructeur SANS id (auto-incrémentation)
        Client c = new Client(nom, categorie, ville);
        clientDAO.save(c);
    }

    // 2️⃣ Lister tous les clients
    public List<Client> listerClients() {
        return clientDAO.findAll();
    }

    // 3️⃣ Lister clients par catégorie
    public List<Client> listerClientsParCategorie(String categorie) {
        if (categorie == null || categorie.isEmpty())
            throw new IllegalArgumentException("Catégorie invalide");

        return clientDAO.findByCategorie(categorie);
    }

    // 4️⃣ Rechercher un client par ID
    public Client chercherClient(int id) {
        return clientDAO.findById(id);
    }

    // 5️⃣ Vérifier si client existe
    public boolean clientExiste(int clientId) {
        return clientDAO.findById(clientId) != null;
    }

    // 6️⃣ Supprimer un client
    public void supprimerClient(int id) {
        clientDAO.delete(id);
    }

    // 7️⃣ Modifier un client
    public void modifierClient(int id, String nom, String categorie, String ville) {
        Client c = clientDAO.findById(id);
        if (c != null) {
            c.setNom(nom);
            c.setCategorie(categorie);
            c.setVille(ville);
            clientDAO.update(c);
        } else {
            throw new IllegalArgumentException("Client introuvable avec l'ID: " + id);
        }
    }
}