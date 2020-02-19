/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Categorie;
import com.esprit.entities.Produit;
import com.esprit.services.IProduitService;
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
public class ProduitController implements IProduitService{

    Connection conn = DataSource.getInstance().getConnection();
    private ResultSet rs;
    private PreparedStatement ps;

    @Override
    public void ajouterProduit(Produit produit)  {
        String req = "INSERT INTO `produit`("
                + "`nom`,"
                + " `prix`,"
                + " `quantite`,"
                + " `reserve`,"
                + " `promotion`,"
                + " `FK_id_categorie`)"
                + " VALUES (?,?,?,?,?,?)";
      
        try {
            ps = conn.prepareStatement(req);
            ps.setString(1,produit.getNom());
            ps.setDouble(2,produit.getPrix());
            ps.setDouble(3,produit.getQuantite());
            ps.setDouble(4,produit.getReserve());
            ps.setBoolean(5,produit.isPromotion());
            ps.setInt(6,produit.getCategorie().getId());
            ps.execute();
            System.out.println("produit ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Produit non ajouté");
            System.out.println(ex.getMessage());
        }
                
    }

    @Override
    public void supprimerProduit(String nom) {
        
        
        try {
                    String req="DELETE FROM `produit` WHERE `nom`=?" ;

            ps = conn.prepareStatement(req);
            ps.setString(1, nom);
            ps.execute();
             System.out.println("suppression validée");
            
        } catch (SQLException ex) {
            System.out.println("erreur de suppression");
        }
        
    }

    @Override
    public void consulterProduit(String nom){
        Produit p=new Produit();
        String req= "SELECT * FROM `produit` WHERE `nom`=?";
        try {
        ps=conn.prepareStatement(req);
        ps.setString(1,nom);
        rs=ps.executeQuery();
        
         while (rs.next()) 
               {
                   
                  
                   p.setId(rs.getInt("id_produit"));
                   p.setNom(rs.getString("nom"));
                   p.setPrix(rs.getDouble("prix"));
                   p.setQuantite(rs.getDouble("quantite"));
                   p.setReserve(rs.getDouble("reserve"));
                   p.setPromotion(rs.getBoolean("promotion"));
                   System.out.println(p);
               }
        }
       
        catch (SQLException ex) {
            System.out.println("Produit introuvable");
        }
             

    
    }

    @Override
    public List<Produit> listeProduit() {
        List<Produit> produits = new ArrayList<>();
        String req = "SELECT * FROM `produit` WHERE 1";
        try {
            ps = conn.prepareStatement(req);
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
                   
                  
                   /**
                    * Jointure Fk_Produit_Categorie
                    */
                   
                   String req2 = "SELECT * FROM `categories` WHERE `id_categorie`=?";
                PreparedStatement ps2 = conn.prepareStatement(req2);
                
                ps2.setInt(1, rs.getInt("FK_id_categorie"));
                ResultSet rs2 = ps2.executeQuery();
                 Categorie c =new Categorie();
                   while (rs2.next()) {  
                       c.setId(rs2.getInt("id_categorie"));
                       c.setNom(rs2.getString("nom"));
                   }
                   //affecter la categorie au produit
                   p.setCategorie(c);
                     //ajouter le produit dans la liste 
                   produits.add(p);
               }
        } catch (SQLException ex) {
            System.err.println("erreur ");
        }
        //System.out.println(produits);
        return produits;
    }

   
   

    @Override
    public Produit modiferProduit(Produit produit) throws SQLException {
        String req = "UPDATE `produit` SET "
                + "`nom`=?,"
                + "`prix`=?,"
                + "`quantite`=?,"
                + "`reserve`=?,"
                + "`promotion`=?,"
                + "`FK_id_categorie`=?"
                + " WHERE `id_produit`=?";    
          try {
            ps = conn.prepareStatement(req);
            ps.setString(1,produit.getNom());
            ps.setDouble(2,produit.getPrix());
            ps.setDouble(3,produit.getQuantite());
            ps.setDouble(4,produit.getReserve());
            ps.setBoolean(5,produit.isPromotion());
            ps.setInt(6,produit.getCategorie().getId());
            ps.setInt(7,produit.getId());
            ps.execute();
              System.out.println("produit modifié");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
        }
        
    return produit;
    }
    
}
