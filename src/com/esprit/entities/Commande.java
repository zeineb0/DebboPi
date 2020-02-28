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
public class Commande {
    
    private int id;
    private Date date_debut,date_fin;
    private double montant;
    private int id_user;

    public Commande() {
    }
    
    

    public Commande(int id, Date date_debut, Date date_fin, double montant, int id_user) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.montant = montant;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_debut() {
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

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", montant=" + montant + ", id_user=" + id_user + '}';
    }
    
    
    
}
