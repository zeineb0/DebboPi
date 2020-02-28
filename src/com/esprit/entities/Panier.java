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
public class Panier {
    private int id_panier;
    private int id_client;
    private String listProduit;
    private int nbrProduit;
    private char EtatPanier;
    
    public Panier() {

    }

    public Panier(int id_client, char EtatPanier) {
        this.id_client = id_client;
        this.listProduit = "";
        this.nbrProduit = 0;
        this.EtatPanier = EtatPanier;
    }

    public Panier(int id_panier, int id_client, int nbrProduit, char EtatPanier) {
        this.id_panier = id_panier;
        this.id_client = id_client;
        this.listProduit = "";
        this.nbrProduit = nbrProduit;
        this.EtatPanier = EtatPanier;
    }
    
    public Panier(int id_panier, int id_client, String listProduit, int nbrProduit, char EtatPanier) {
        this.id_panier = id_panier;
        this.id_client = id_client;
        this.listProduit = listProduit;
        this.nbrProduit = nbrProduit;
        this.EtatPanier = EtatPanier;
    }

    public Panier(int id_panier, String listProduit, int nbrProduit, char EtatPanier) {
        this.id_panier = id_panier;
        this.listProduit = listProduit;
        this.nbrProduit = nbrProduit;
        this.EtatPanier = EtatPanier;
    }
    
    

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getListProduit() {
        return listProduit;
    }

    public void setListProduit(String listProduit) {
        this.listProduit = listProduit;
    }

    public int getNbrProduit() {
        return nbrProduit;
    }

    public void setNbrProduit(int nbrProduit) {
        this.nbrProduit = nbrProduit;
    }

    public char getEtatPanier() {
        return EtatPanier;
    }

    public void setEtatPanier(char EtatPanier) {
        this.EtatPanier = EtatPanier;
    }

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", id_client=" + id_client + ", listProduit=" + listProduit + ", nbrProduit=" + nbrProduit + ", EtatPanier=" + EtatPanier + '}';
    }

   
    
    
}
