package org.example;

public class Utilisateur {
    private String nom;
    private String prenom;
    private String email;
    private int id; // Ajout de l'attribut id

    public Utilisateur() {
        // Constructeur par défaut
    }

    public Utilisateur(String nom, String prenom, String email, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.id = id;
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
