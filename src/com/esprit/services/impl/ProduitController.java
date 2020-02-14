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
    public int ajouterProduit(Produit produit)  {
        System.out.println(produit);
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
        } catch (SQLException ex) {
            System.out.println("Produit non ajouté");
            System.out.println(ex.getMessage());
        }
           
      
        return 0;
        
    }

    @Override
    public int supprimerProduit(Produit produit) {
        
        
        String req="DELETE FROM `produit` WHERE `id_produit`=?";
        try {
            ps = conn.prepareStatement(req);
            ps.setInt(1, produit.getId());
            ps.execute();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }

    @Override
    public Produit consulterProduit(Produit produit){
        Produit p=new Produit();
        String req= "SELECT * FROM `produit` WHERE `id_produit`=?";
        try {
        ps=conn.prepareStatement(req);
        ps.setInt(1,produit.getId());
        rs=ps.executeQuery();
        
         while (rs.next()) 
               {
                   
                  
                   p.setId(rs.getInt("id_produit"));
                   p.setNom(rs.getString("nom"));
                   p.setPrix(rs.getDouble("prix"));
                   p.setQuantite(rs.getDouble("quantite"));
                   p.setReserve(rs.getDouble("reserve"));
                   p.setPromotion(rs.getBoolean("promotion"));
                   
               }
             return p;
         
        
        }
        
        
        catch (SQLException ex) {
            System.out.println("Produit introuvable");
            return null;
        }
       
    
    }

    @Override
    public List<Produit> listeProduit() throws SQLException{
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
                   
                   String req2 = "SELECT * FROM `categorie` WHERE `id_produit`=?";
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
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("aaaaa"+produits);
        return produits;
    }

    @Override
    public List<Produit> listeProduitPourUneCategorie(Categorie categorie) throws SQLException{
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
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                + " WHERE `id`=?";    
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
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    return produit;
    }
    
}
