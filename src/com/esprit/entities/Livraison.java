/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Date;

/**
 *
 * @author ASUS X550V
 */
public class Livraison {
    public int id_livraison;
    public Date date_livraison;
    public String adresse_livraison;
    public String etat_livraison;
    public float longitude_dest;
    public float altitude_dest;
    public String acceptation;
    public int FK_id_commande;
    public int FK_id_user;

    public Livraison() {
    }

    public Livraison(Date date_livraison, String adresse_livraison, String etat_livraison, float longitude_dest, float altitude_dest, String acceptation) {
        this.date_livraison = date_livraison;
        this.adresse_livraison = adresse_livraison;
        this.etat_livraison = etat_livraison;
        this.longitude_dest = longitude_dest;
        this.altitude_dest = altitude_dest;
        this.acceptation = acceptation;
    }

    public String getAcceptation() {
        return acceptation;
    }

    public void setAcceptation(String acceptation) {
        this.acceptation = acceptation;
    }



    public Livraison(int id_livraison, Date date_livraison, String adresse_livraison, String etat_livraison, float longitude_dest, float altitude_dest, String acceptation, int FK_id_commande, int FK_id_user) {
        this.id_livraison = id_livraison;
        this.date_livraison = date_livraison;
        this.adresse_livraison = adresse_livraison;
        this.etat_livraison = etat_livraison;
        this.longitude_dest = longitude_dest;
        this.altitude_dest = altitude_dest;
        this.acceptation = acceptation;
        this.FK_id_commande = FK_id_commande;
        this.FK_id_user = FK_id_user;
    }

    
    
 

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_livraison=" + id_livraison + ", date_livraison=" + date_livraison + ", adresse_livraison=" + adresse_livraison + ", etat_livraison=" + etat_livraison + ", longitude_dest=" + longitude_dest + ", altitude_dest=" + altitude_dest + ", acceptation=" + acceptation + ", FK_id_commande=" + FK_id_commande + ", FK_id_user=" + FK_id_user + '}';
    }



    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public float getAltitude_dest() {
        return altitude_dest;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public String getEtat_livraison() {
        return etat_livraison;
    }

    public int getFK_id_commande() {
        return FK_id_commande;
    }

    public int getFK_id_user() {
        return FK_id_user;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public float getLongitude_dest() {
        return longitude_dest;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public void setAltitude_dest(float altitude_dest) {
        this.altitude_dest = altitude_dest;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public void setEtat_livraison(String etat_livraison) {
        this.etat_livraison = etat_livraison;
    }

    public void setFK_id_commande(int FK_id_commande) {
        this.FK_id_commande = FK_id_commande;
    }

    public void setFK_id_user(int FK_id_user) {
        this.FK_id_user = FK_id_user;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public void setLongitude_dest(float longitude_dest) {
        this.longitude_dest = longitude_dest;
    }
    
    
    
    
    
    
    
    
    
    
    
}
