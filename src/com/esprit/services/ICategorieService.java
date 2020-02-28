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
public interface ICategorieService {
        public void ajouterCategorie(Categorie c);
    public void supprimerCategorie(String nom) ;
    public void consulterCategorie(String nom);
    public Categorie modiferCategorie(Categorie c);
        public List<Categorie> listCategorie();
            public List<Produit> listeProduitPourUneCategorie(Categorie categorie )throws SQLException;


}
