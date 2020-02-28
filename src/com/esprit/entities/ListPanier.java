/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author ghazi
 */
public class ListPanier {
    private String nom_Produit;
    private int quantité;
    private Float prix;
    private Float prixUnité;

    public ListPanier(String nom_Produit, int quantité, Float prixUnité,Float prix) {
        this.nom_Produit = nom_Produit;
        this.quantité = quantité;
        this.prix = prix;
        this.prixUnité = prixUnité;
    }

    public String getNom_Produit() {
        return nom_Produit;
    }

    public void setNom_Produit(String nom_Produit) {
        this.nom_Produit = nom_Produit;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Float getPrixUnité() {
        return prixUnité;
    }

    public void setPrixUnité(Float prixUnité) {
        this.prixUnité = prixUnité;
    }
    
    
    
    
}
