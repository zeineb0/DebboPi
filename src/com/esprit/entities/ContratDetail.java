/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.sql.Date;

/**
 *
 * @author Dell
 */
public class ContratDetail {
    
    
    private int id_user,id_entrepot;
    private Date date_debut,date_fin;
    private String nom,prenom,entreprise;

    public ContratDetail() {
    }

   

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_entrepot() {
        return id_entrepot;
    }

    public void setId_entrepot(int id_entrepot) {
        this.id_entrepot = id_entrepot;
    }

    public Date getDate_deb() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

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

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    @Override
    public String toString() {
        return "ContratDetail{" +  ", id_user=" + id_user + ", id_entrepot=" + id_entrepot + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", nom=" + nom + ", prenom=" + prenom + ", entreprise=" + entreprise + '}';
    }
    
    
    
    
    
    
    
}
