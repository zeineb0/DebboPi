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
    String nom_entrepot;
    String adresse_entrepot;
    int num_fiscale;
    int quantite_max;

    public Entrepot(int id_entrepot,String nom_entrepot, String adresse_entrepot, int num_fiscale, int quantite_max) {
        this.id_entrepot = id_entrepot;
        this.nom_entrepot = nom_entrepot;
        this.adresse_entrepot = adresse_entrepot;
        this.num_fiscale = num_fiscale;
        this.quantite_max = quantite_max;
    }

    public Entrepot(String nom_entrepot, String adresse_entrepot, int num_fiscale, int id_max) {
        this.nom_entrepot = nom_entrepot;
         this.adresse_entrepot = adresse_entrepot;
        this.num_fiscale = num_fiscale;
        this.quantite_max = quantite_max;
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

    public int getQuantite_max() {
        return quantite_max;
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

    public void setQuantite_max(int quantite_max) {
        this.quantite_max = quantite_max;
    }

    public String getNom_entrepot() {
        return nom_entrepot;
    }

    public void setNom_entrepot(String nom_entrepot) {
        this.nom_entrepot = nom_entrepot;
    }
    

    @Override
    public String toString() {
        return "Entrepot{" + "id_entrepot=" + id_entrepot + ", nom_entrepot=" + nom_entrepot + ", adresse_entrepot=" + adresse_entrepot + ", num_fiscale=" + num_fiscale + ", quantite_max=" + quantite_max + '}';
    }

 
    
   
    
}
