/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.la2028.model;

import java.util.Date;

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
    public Athlete() {
    }

    public Athlete(int id, String nom, String prenom, String date_de_naissance) {
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

    public void setDob(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
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
