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
        public void ajouterProduit(Categorie c)throws SQLException;
    public void supprimerProduit(Categorie c) throws SQLException;
    public Categorie consulterProduit(Categorie c)throws SQLException;
    public Categorie modiferProduit(Categorie c)throws SQLException;
        public List<Categorie> listCategorie();

}
