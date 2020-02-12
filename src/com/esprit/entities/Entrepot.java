/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author asus
 */
public class Entrepot {
    int id_entrepot;
    String adresse_entrepot;
    int num_fiscale;
    int id_max;

    public Entrepot(int id_entrepot, String adresse_entrepot, int num_fiscale, int id_max) {
        this.id_entrepot = id_entrepot;
        this.adresse_entrepot = adresse_entrepot;
        this.num_fiscale = num_fiscale;
        this.id_max = id_max;
    }

    public Entrepot(String adresse_entrepot, int num_fiscale, int id_max) {
        this.adresse_entrepot = adresse_entrepot;
        this.num_fiscale = num_fiscale;
        this.id_max = id_max;
    }

    public int getId_entrepot() {
        return id_entrepot;
    }

    public String getAdresse_entrepot() {
        return adresse_entrepot;
    }

    public int getNum_fiscale() {
        return num_fiscale;
    }

    public int getId_max() {
        return id_max;
    }

    public void setId_entrepot(int id_entrepot) {
        this.id_entrepot = id_entrepot;
    }

    public void setAdresse_entrepot(String adresse_entrepot) {
        this.adresse_entrepot = adresse_entrepot;
    }

    public void setNum_fiscale(int num_fiscale) {
        this.num_fiscale = num_fiscale;
    }

    public void setId_max(int id_max) {
        this.id_max = id_max;
    }

    @Override
    public String toString() {
        return "Entrepot{" + "id_entrepot=" + id_entrepot + ", adresse_entrepot=" + adresse_entrepot + ", num_fiscale=" + num_fiscale + ", id_max=" + id_max + '}';
    }

   
    
}
