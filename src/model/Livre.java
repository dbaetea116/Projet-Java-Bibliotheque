package model;

public class Livre {

    private int id;
    private String titre;
    private String auteur;
    private int annee;
    private String categorie;
    private int stock;

    public Livre() {}

    public Livre(int id, String titre, String auteur, int annee, String categorie, int stock) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.annee = annee;
        this.categorie = categorie;
        this.stock = stock;
    }

    // Getters
    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public int getAnnee() { return annee; }
    public String getCategorie() { return categorie; }
    public int getStock() { return stock; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public void setAnnee(int annee) { this.annee = annee; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public void setStock(int stock) { this.stock = stock; }
}
