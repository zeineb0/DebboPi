/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Date;

/**
 *
 * @author ghazi
 */
public class Commande {
    
    private int idCommande;
    private Date dateCommande;
    private Date dateExp;
    private String listProduit;
    private float montant;
    private String Type_paiement;
    private int idClient;
    private int nbrProduit;
    
    
    public Commande(){};

    public Commande(Date dateCommande, Date dateExp, String listProduit, float montant, String Type_paiement, int idClient, int nbrProduit) {
        this.dateCommande = dateCommande;
        this.dateExp = dateExp;
        this.listProduit = listProduit;
        this.montant = montant;
        this.Type_paiement = Type_paiement;
        this.idClient = idClient;
        this.nbrProduit = nbrProduit;
    }
    
    public Commande(int idCommande, Date dateCommande, Date dateExp, String listProduit, float montant, String Type_paiement, int idClient, int nbrProduit) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.dateExp = dateExp;
        this.listProduit = listProduit;
        this.montant = montant;
        this.Type_paiement = Type_paiement;
        this.idClient = idClient;
        this.nbrProduit = nbrProduit;
    }

 
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateExp() {
        return dateExp;
    }

    public void setDateExp(Date dateExp) {
        this.dateExp = dateExp;
    }

    public String getListProduit() {
        return listProduit;
    }

    public void setListProduit(String listProduit) {
        this.listProduit = listProduit;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getType_paiement() {
        return Type_paiement;
    }

    public void setType_paiement(String Type_paiement) {
        this.Type_paiement = Type_paiement;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getNbrProduit() {
        return nbrProduit;
    }

    public void setNbrProduit(int nbrPrdouit) {
        this.nbrProduit = nbrPrdouit;
    }
   
    
    
    
}
