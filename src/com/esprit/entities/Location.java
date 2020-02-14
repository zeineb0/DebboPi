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
    double prix_location;
    int FK_id_entrepot;
    int FK_id_user;

    public Location(int id_location, Date date_location, double prix_location, int FK_id_entrepot, int FK_id_user) {
        this.id_location = id_location;
        this.date_location = date_location;
        this.prix_location = prix_location;
        this.FK_id_entrepot = FK_id_entrepot;
        this.FK_id_user = FK_id_user;
    }
    public Location(Date date_location, double prix_location, int FK_id_entrepot, int FK_id_user) {
        this.date_location = date_location;
        this.prix_location = prix_location;
        this.FK_id_entrepot = FK_id_entrepot;
        this.FK_id_user = FK_id_user;
    }

    public int getId_location() {
        return id_location;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    public Date getDate_location() {
        return date_location;
    }

    public void setDate_location(Date date_location) {
        this.date_location = date_location;
    }

    public double getPrix_location() {
        return prix_location;
    }

    public void setPrix_location(double prix_location) {
        this.prix_location = prix_location;
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

    @Override
    public String toString() {
        return "Location{" + "id_location=" + id_location + ", date_location=" + date_location + ", prix_location=" + prix_location + ", FK_id_entrepot=" + FK_id_entrepot + ", FK_id_user=" + FK_id_user + '}';
    }
    
    
}
