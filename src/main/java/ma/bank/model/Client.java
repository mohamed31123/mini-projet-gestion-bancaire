package ma.bank.model;

public class Client {
    private int id;
    private String nom;
    private String categorie;
    private String ville;

    public Client() {}

    public Client(int id, String nom, String categorie, String ville) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.ville = ville;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
}