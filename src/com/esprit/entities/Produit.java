/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class Produit {
private int id;
    private String nom;
    private double prix;
    private double quantite;
    private double reserve;
    private boolean promotion;
    //un produit posséde une catégorie
    private Categorie categorie;

    public Produit(int id, String nom, double prix, double quantite, double reserve, boolean promotion, Categorie categorie) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.reserve = reserve;
        this.promotion = promotion;
        this.categorie = categorie;
    }
    public Produit( String nom, double prix, double quantite, double reserve, boolean promotion, Categorie categorie) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.reserve = reserve;
        this.promotion = promotion;
        this.categorie = categorie;
    }

    public Produit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public double getQuantite() {
        return quantite;
    }

    public double getReserve() {
        return reserve;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public void setReserve(double reserve) {
        this.reserve = reserve;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", quantite=" + quantite + ", reserve=" + reserve + ", promotion=" + promotion + ", categorie=" + categorie + '}';
    }
    
    

}
