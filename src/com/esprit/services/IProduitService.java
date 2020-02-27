/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.entities.Categorie;
import com.esprit.entities.Produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Zeineb_yahiaoui
 */
public interface IProduitService {
     public void ajouterProduit(Produit produit)throws SQLException;
    public void supprimerProduit(Produit produit) throws SQLException;
    public void consulterProduit(String nom)throws SQLException;
    public Produit modiferProduit(Produit produit)throws SQLException;
    public List<Produit> listeProduit()throws SQLException;
    // tri par prix
}
