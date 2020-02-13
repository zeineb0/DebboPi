/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.entities.Categorie;
import com.esprit.entities.Produit;
import java.util.List;

/**
 *
 * @author Zeineb_yahiaoui
 */
public interface IProduitService {
     public int ajouterProduit(Produit produit);
    public int supprimerProduit(Produit produit);
    public Produit consulterProduit(Produit produit);
    public Produit modiferProduit(Produit produit);
    public List<Produit> listeProduit();
    public List<Produit> listeProduitPourUneCategorie(Categorie categorie );
}
