/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class Produit {
private int id;
    private String libelle;
    private double prix;
    private int reference;
    private String marque;
    //un produit posséde une catégorie
    private String image;
    private Categorie categorie;
    private Entrepot entrepot;
    private int quantite;
    private boolean promotion;
        private User u;

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }


    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }


    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getPrix() {
        return prix;
    }

    public int getReference() {
        return reference;
    }

    public String getMarque() {
        return marque;
    }

    public String getImage() {
        return image;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Entrepot getEntrepot() {
        return entrepot;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", libelle=" + libelle + ", prix=" + prix + ", reference=" + reference + ", marque=" + marque + ", image=" + image + ", categorie=" + categorie + ", entrepot=" + entrepot + ", quantite=" + quantite + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setEntrepot(Entrepot entrepot) {
        this.entrepot = entrepot;
    }


    
    
    

}
