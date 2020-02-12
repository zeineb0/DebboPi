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
public class Location {
    int id_location;
    Date date_location;
    int prix_location;
    int FK_id_entrepot;
    int FK_id_client;
    int FK_id_fournisseur;

    public Location(int id_location, Date date_location, int prix_location, int FK_id_entrepot, int FK_id_client, int FK_id_fournisseur) {
        this.id_location = id_location;
        this.date_location = date_location;
        this.prix_location = prix_location;
        this.FK_id_entrepot = FK_id_entrepot;
        this.FK_id_client = FK_id_client;
        this.FK_id_fournisseur = FK_id_fournisseur;
    }
    
       public Location(Date date_location, int prix_location, int FK_id_entrepot, int FK_id_client, int FK_id_fournisseur) {
        this.date_location = date_location;
        this.prix_location = prix_location;
        this.FK_id_entrepot = FK_id_entrepot;
        this.FK_id_client = FK_id_client;
        this.FK_id_fournisseur = FK_id_fournisseur;
    }

    public int getId_location() {
        return id_location;
    }

    public Date getDate_location() {
        return date_location;
    }

    public int getPrix_location() {
        return prix_location;
    }

    public int getFK_id_entrepot() {
        return FK_id_entrepot;
    }

    public int getFK_id_client() {
        return FK_id_client;
    }

    public int getFK_id_fournisseur() {
        return FK_id_fournisseur;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    public void setDate_location(Date date_location) {
        this.date_location = date_location;
    }

    public void setPrix_location(int prix_location) {
        this.prix_location = prix_location;
    }

    public void setFK_id_entrepot(int FK_id_entrepot) {
        this.FK_id_entrepot = FK_id_entrepot;
    }

    public void setFK_id_client(int FK_id_client) {
        this.FK_id_client = FK_id_client;
    }

    public void setFK_id_fournisseur(int FK_id_fournisseur) {
        this.FK_id_fournisseur = FK_id_fournisseur;
    }
    

    
    
}
