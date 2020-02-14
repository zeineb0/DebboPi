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
        public void ajouterCategorie(Categorie c)throws SQLException;
    public void supprimerCategorie(Categorie c) throws SQLException;
    public Categorie consulterCategorie(Categorie c)throws SQLException;
    public Categorie modiferCategorie(Categorie c)throws SQLException;
        public List<Categorie> listCategorie();

}
