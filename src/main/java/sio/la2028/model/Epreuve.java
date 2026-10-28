package sio.la2028.model;

import java.util.ArrayList;

public class Epreuve {

    private int id;
    private String nom;
    private Sport sport;
    private ArrayList<Epreuve_Athlete> epreuveAthletes;
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

    public ArrayList<Epreuve_Athlete> getEpreuveAthletes() {
        return epreuveAthletes;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
