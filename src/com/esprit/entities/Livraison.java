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
public class Livraison {
    
    private int id_livraison;
    private Date date;
    private String adresse_livaison,etat_livraison,acceptation;
    private int id_commande,id_user;

    public Livraison() {
    }

    
    
    public Livraison(int id_livraison, Date date, String adresse_livaison, String etat_livraison, String acceptation, int id_commande, int id_user) {
        this.id_livraison = id_livraison;
        this.date = date;
        this.adresse_livaison = adresse_livaison;
        this.etat_livraison = etat_livraison;
        this.acceptation = acceptation;
        this.id_commande = id_commande;
        this.id_user = id_user;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAdresse_livaison() {
        return adresse_livaison;
    }

    public void setAdresse_livaison(String adresse_livaison) {
        this.adresse_livaison = adresse_livaison;
    }

    public String getEtat_livraison() {
        return etat_livraison;
    }

    public void setEtat_livraison(String etat_livraison) {
        this.etat_livraison = etat_livraison;
    }

    public String getAcceptation() {
        return acceptation;
    }

    public void setAcceptation(String acceptation) {
        this.acceptation = acceptation;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_livraison=" + id_livraison + ", date=" + date + ", adresse_livaison=" + adresse_livaison + ", etat_livraison=" + etat_livraison + ", acceptation=" + acceptation + ", id_commande=" + id_commande + ", id_user=" + id_user + '}';
    }
    
    
    
    
    
    
}
