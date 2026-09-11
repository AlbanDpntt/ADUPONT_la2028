package sio.la2028.model;

public class Epreuve {

    private int id;
    private String nom;
    private Sport sport;
    public Epreuve() {
    }

    public Epreuve(int id, String nom, Sport sport) {
        this.id = id;
        this.nom = nom;
        this.sport = sport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
