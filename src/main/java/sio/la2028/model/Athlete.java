/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.la2028.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 *
 * @author zakina
 */
public class Athlete {
    
    private int id;
    private String prenom;
    private String nom ;
    private Pays pays ;
    private String date_de_naissance;
    private Sport sport;
    public Athlete() {
    }

    public Athlete(int id, String nom, String prenom, String date_de_naissance, String sport) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.date_de_naissance = date_de_naissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Sport getSport() {
        return sport;
    }

    public void setDob(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public long getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(date_de_naissance, formatter);
        return ChronoUnit.YEARS.between(date, LocalDate.now());
    }

    public String getDob() {
        if (this.date_de_naissance != null && !this.date_de_naissance.isEmpty()) {
            java.time.LocalDate date = java.time.LocalDate.parse(this.date_de_naissance);
            return date.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return "";
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
    
    
}
