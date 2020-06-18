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
    int quantite_max;
    String etat;
    String entreprise;
    Float prix_location;
    int fk_id_fournisseur;

    public Entrepot(int id_entrepot, String adresse_entrepot, int num_fiscale, int quantite_max, String etat,Float prix_location, String entreprise,int fk_id_fournisseur) {
        this.id_entrepot = id_entrepot;
        this.adresse_entrepot = adresse_entrepot;
        this.num_fiscale = num_fiscale;
        this.quantite_max = quantite_max;
        this.etat = etat;
        this.entreprise = entreprise;
        this.prix_location=prix_location;
        this.fk_id_fournisseur= fk_id_fournisseur;
    }

    public Entrepot(String adresse_entrepot, int num_fiscale, int quantite_max, String etat, Float prix_location, String entreprise) {
this.adresse_entrepot = adresse_entrepot;
        this.num_fiscale = num_fiscale;
        this.quantite_max = quantite_max;
        this.etat = etat;
        this.entreprise = entreprise;

    }



    public Float getPrix_location() {
        return prix_location;
    }

    public void setPrix_location(Float prix_location) {
        this.prix_location = prix_location;
    }

    public Entrepot(String adresse_entrepot, int num_fiscale, int quantite_max, String etat, String entreprise,Float prix_location, int fk_id_fournisseur) {
        this.adresse_entrepot = adresse_entrepot;
        this.num_fiscale = num_fiscale;
        this.quantite_max = quantite_max;
        this.etat = etat;
        this.entreprise = entreprise;
        this.fk_id_fournisseur= fk_id_fournisseur;

    }

    public Entrepot() {
    }

    public Entrepot(int id_entrepot, String adresse_entrepot, int num_fiscale, int quantite_max, String etat,Float prix_location, String entreprise) {
        this.id_entrepot = id_entrepot;
        this.adresse_entrepot = adresse_entrepot;
        this.num_fiscale = num_fiscale;
        this.quantite_max = quantite_max;
        this.etat = etat;
        this.entreprise = entreprise;
        this.prix_location=prix_location;
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

    public String getEntreprise() {
        return entreprise;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Entrepot{" + "id_entrepot=" + id_entrepot + ", adresse_entrepot=" + adresse_entrepot + ", num_fiscale=" + num_fiscale + ", quantite_max=" + quantite_max + ", etat=" + etat + ", entreprise=" + entreprise + '}';
    }

   

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public int getFk_id_fournisseur() {
        return fk_id_fournisseur;
    }

    public void setFk_id_fournisseur(int fk_id_fournisseur) {
        this.fk_id_fournisseur = fk_id_fournisseur;
    }

  

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entrepot other = (Entrepot) obj;
        if (this.num_fiscale != other.num_fiscale) {
            return false;
        }
        return true;
    }

    


    

 
    

    
 
    
   
    
}
