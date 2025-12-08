package model;

import java.time.LocalDate;

public class Emprunt {

    private int id;
    private int idLivre;
    private int idMembre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourReelle;

    public Emprunt() {}

    public Emprunt(int id, int idLivre, int idMembre, LocalDate dateEmprunt,
                   LocalDate dateRetourPrevue, LocalDate dateRetourReelle) {
        this.id = id;
        this.idLivre = idLivre;
        this.idMembre = idMembre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourReelle = dateRetourReelle;
    }

    // Getters
    public int getId() { return id; }
    public int getIdLivre() { return idLivre; }
    public int getIdMembre() { return idMembre; }
    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
    public LocalDate getDateRetourReelle() { return dateRetourReelle; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setIdLivre(int idLivre) { this.idLivre = idLivre; }
    public void setIdMembre(int idMembre) { this.idMembre = idMembre; }
    public void setDateEmprunt(LocalDate dateEmprunt) { this.dateEmprunt = dateEmprunt; }
    public void setDateRetourPrevue(LocalDate dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }
    public void setDateRetourReelle(LocalDate dateRetourReelle) { this.dateRetourReelle = dateRetourReelle; }
}
