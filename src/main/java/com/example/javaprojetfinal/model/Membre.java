package com.example.javaprojetfinal.model;

public class Membre {

    private int id;
    private String nom;
    private String email;
    private String telephone;

    public Membre() {}

    public Membre(int id, String nom, String email, String telephone) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setEmail(String email) { this.email = email; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
}
