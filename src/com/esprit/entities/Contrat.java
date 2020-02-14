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
public class Contrat {
    
    private int id;
    private Date date_debut,date_fin;
    private int id_user;
    private int id_entrepot;

    public Contrat() {
    }
    
    

    public Contrat(int id, Date date_debut, Date date_fin, int id_user, int id_entrepot) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
        this.id_entrepot = id_entrepot;
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

    @Override
    public String toString() {
        return "Contrat{" + "id=" + id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", id_user=" + id_user + ", id_entrepot=" + id_entrepot + '}';
    }
    
    
    
}
