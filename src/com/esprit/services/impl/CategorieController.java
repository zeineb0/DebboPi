/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Categorie;
import com.esprit.entities.Produit;
import com.esprit.services.ICategorieService;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class CategorieController implements ICategorieService
{
Connection conn = DataSource.getInstance().getConnection();
    private ResultSet rs;
    private PreparedStatement ps;
    
    @Override
    public List<Categorie> listCategorie() {
        List<Categorie> categories = new ArrayList<>();
        String req = "SELECT * FROM `categories` WHERE 1";
        
        try {
            ps = conn.prepareStatement(req);
            rs = ps.executeQuery();
               while (rs.next()) 
               {
                   Categorie c =new Categorie();
                   c.setId(rs.getInt("id_categorie"));
                   c.setNom(rs.getString("nom"));
                   categories.add(c);
               }
            
                               System.out.println(categories.toString());

        } catch (SQLException ex) {
            System.out.println("erreur");        }
        return categories;

    }

    @Override
    public void ajouterCategorie(Categorie c)  {

        String req = "INSERT INTO `categories`( `nom`) VALUES (?)";
    try {
        ps=conn.prepareStatement(req);
         ps.setString(1,c.getNom());
        ps.execute();
    } catch (SQLException ex) {
        Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
    }
     
  }
 

    @Override
    public void supprimerCategorie(String nom) {

        String req= "DELETE FROM `categories` WHERE `nom`=?";
    
        try{
                    ps=conn.prepareStatement(req);

        ps.setString(1,nom);
        ps.execute();
        
            System.out.println("catégorie supprimée");
        }
        catch (SQLException ex)
        {
            System.out.println("erreur de suppression");
        }    }
        

    

    @Override
    public void consulterCategorie(String nom) {
                    Categorie c = new Categorie();

        String req="SELECT * FROM `categories` WHERE `nom` like ?";
        try{
                    ps=conn.prepareStatement(req);

        ps.setString(1,nom);
        ps.execute();
        rs=ps.executeQuery();
            while (rs.next()) {
               
                c.setId(rs.getInt("id_categorie"));
                   c.setNom(rs.getString("nom"));
                
            }
            System.out.println(c);
        }
        catch (SQLException ex)
        {System.out.println("catégorie introuvable");}    
    
    }

    @Override
    public Categorie modiferCategorie(Categorie c) {
        String req = "UPDATE `categories` SET `nom`=? WHERE `id_categorie`=?";
        try{
                    ps=conn.prepareStatement(req);

        ps.setInt(2,c.getId());
        ps.setString(1,c.getNom());
        
        ps.execute();
        
            System.out.println("catégorie modifiée");
        }
        catch (SQLException ex)
        {
            System.out.println("erreur de modification");
        }    
    return c;
                
    
                }
    @Override
     public List<Produit> listeProduitPourUneCategorie(Categorie categorie) {
    List<Produit> produits = new ArrayList<>();
        String req = "SELECT * FROM `produit` WHERE `FK_id_categorie`=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, categorie.getId());
             rs = ps.executeQuery();
               while (rs.next()) 
               {
                   Produit p=new Produit();
                   p.setId(rs.getInt("id_produit"));
                   p.setNom(rs.getString("nom"));
                   p.setPrix(rs.getDouble("prix"));
                   p.setQuantite(rs.getDouble("quantite"));
                   p.setReserve(rs.getDouble("reserve"));
                   p.setPromotion(rs.getBoolean("promotion"));
                   p.setCategorie(categorie);
                   produits.add(p);
               }
               } catch (SQLException ex) {
            System.out.println("erreur");        }
           return produits;
    }
    
    
}
