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
   private int id_entrepot;
   private String adress,etat,entreprise;
   private int num_fisc;
   private long quantite_max;

    public Entrepot() {
    }

   
   
    public Entrepot(int id_entrepot, String adress, String etat, String entreprise, int num_fisc, long quantite_max) {
        this.id_entrepot = id_entrepot;
        this.adress = adress;
        this.etat = etat;
        this.entreprise = entreprise;
        this.num_fisc = num_fisc;
        this.quantite_max = quantite_max;
    }

    public int getId_entrepot() {
        return id_entrepot;
    }

    public void setId_entrepot(int id_entrepot) {
        this.id_entrepot = id_entrepot;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public int getNum_fisc() {
        return num_fisc;
    }

    public void setNum_fisc(int num_fisc) {
        this.num_fisc = num_fisc;
    }

    public long getQuantite_max() {
        return quantite_max;
    }

    public void setQuantite_max(long quantite_max) {
        this.quantite_max = quantite_max;
    }

    @Override
    public String toString() {
        return "Entrepot{" + "id_entrepot=" + id_entrepot + ", adress=" + adress + ", etat=" + etat + ", entreprise=" + entreprise + ", num_fisc=" + num_fisc + ", quantite_max=" + quantite_max + '}';
    }
   
   
   
   
 
}
