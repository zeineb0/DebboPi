/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class LocationDetail {

    public LocationDetail() {
    }

    @Override
    public String toString() {
        return "LocationDetail{" + "id_location=" + id_location + ", date_deb_location=" + date_deb_location + ", date_fin_location=" + date_fin_location + ", prix_location=" + prix_location + ", quantite_max=" + quantite_max + ", adresse_entrepot=" + adresse_entrepot + ", nom=" + nom + ", prenom=" + prenom + ", FK_id_entrepot=" + FK_id_entrepot + ", FK_id_user=" + FK_id_user + '}';
    }

    public LocationDetail(int id_location, Date date_deb_location, Date date_fin_location, double prix_location, int quantite_max, String adresse_entrepot, String nom, String prenom) {
        this.id_location = id_location;
        this.date_deb_location = date_deb_location;
        this.date_fin_location = date_fin_location;
        this.prix_location = prix_location;
        this.quantite_max = quantite_max;
        this.adresse_entrepot = adresse_entrepot;
        this.nom = nom;
        this.prenom = prenom;
    }
     int id_location;
               Date date_deb_location ;
               Date date_fin_location ;
               double prix_location ;
               int quantite_max ;
               String adresse_entrepot;
               String nom;
               String prenom;
               int FK_id_entrepot;
               int FK_id_user;

    public int getId_location() {
        return id_location;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    public Date getDate_deb_location() {
        return date_deb_location;
    }

    public void setDate_deb_location(Date date_deb_location) {
        this.date_deb_location = date_deb_location;
    }

    public Date getDate_fin_location() {
        return date_fin_location;
    }

    public void setDate_fin_location(Date date_fin_location) {
        this.date_fin_location = date_fin_location;
    }

    public double getPrix_location() {
        return prix_location;
    }

    public void setPrix_location(double prix_location) {
        this.prix_location = prix_location;
    }

    public int getQuantite_max() {
        return quantite_max;
    }

    public void setQuantite_max(int quantite_max) {
        this.quantite_max = quantite_max;
    }

    public String getAdresse_entrepot() {
        return adresse_entrepot;
    }

    public void setAdresse_entrepot(String adresse_entrepot) {
        this.adresse_entrepot = adresse_entrepot;
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

    public int getFK_id_entrepot() {
        return FK_id_entrepot;
    }

    public void setFK_id_entrepot(int FK_id_entrepot) {
        this.FK_id_entrepot = FK_id_entrepot;
    }

    public int getFK_id_user() {
        return FK_id_user;
    }

    public void setFK_id_user(int FK_id_user) {
        this.FK_id_user = FK_id_user;
    }
               

    public LocationDetail(int id_location, Date date_deb_location, Date date_fin_location, double prix_location, int quantite_max, String adresse_entrepot, String nom, String prenom, int FK_id_entrepot) {
        this.id_location = id_location;
        this.date_deb_location = date_deb_location;
        this.date_fin_location = date_fin_location;
        this.prix_location = prix_location;
        this.quantite_max = quantite_max;
        this.adresse_entrepot = adresse_entrepot;
        this.nom = nom;
        this.prenom = prenom;
        this.FK_id_entrepot = FK_id_entrepot;
      
    }
               
}
