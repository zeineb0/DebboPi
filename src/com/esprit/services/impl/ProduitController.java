/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Categorie;
import com.esprit.entities.Entrepot;
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
                + " `FK_id_categorie`)"
                + "`FK_id_entrepot`"
                + " VALUES (?,?,?,?,?,?)";
      
        try {
            ps = conn.prepareStatement(req);
            ps.setString(1,produit.getNom());
            ps.setDouble(2,produit.getPrix());
            ps.setDouble(3,produit.getQuantite());
            ps.setDouble(4,produit.getReserve());
            ps.setInt(5,produit.getCategorie().getId());
            ps.setInt(6,produit.getEntrepot().getId_entrepot());
            ps.execute();
            System.out.println("produit ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Produit non ajouté");
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
                    
                   String req3 = "SELECT `entreprise` FROM `entrepot` WHERE `id_entrepot`=?";
                                   PreparedStatement ps3 = conn.prepareStatement(req3);
                                   
                                   ps3.setInt(1,rs.getInt("FK_id_entrepot"));
                                   ResultSet rs3 = ps3.executeQuery();
                                   Entrepot e = new Entrepot();
                                   while(rs3.next()){
                                   
                                   e.setId_entrepot(rs3.getInt("id_entrepot"));

                                   e.setAdresse_entrepot(rs3.getString("adresse_entrepot"));
                                   e.setNum_fiscale(rs3.getInt("num_fiscale"));
                                   e.setQuantite_max(rs3.getInt("quantite_max"));
                                   e.setEtat(rs3.getString("etat"));
                                   e.setEntreprise(rs3.getString("entreprise"));
                                   
                                   }
                                   p.setEntrepot(e);
                                   

                                      produits.add(p);

                   
                   
                   
               }
        } catch (SQLException ex) {
            System.err.println("erreur ");
        }
        System.out.println(produits);
        return produits;
    }

   
   

    @Override
    public Produit modiferProduit(Produit produit)  {
        String req = "UPDATE `produit` SET "
                + "`nom`=?,"
                + "`prix`=?,"
                + "`quantite`=?,"
                + "`reserve`=?,"
                + "`FK_id_categorie`=?"
                + "`FK_id_entrepot`"

                + " WHERE `id_produit`=?";    
          try {
            ps = conn.prepareStatement(req);
            ps.setString(1,produit.getNom());
            ps.setDouble(2,produit.getPrix());
            ps.setDouble(3,produit.getQuantite());
            ps.setDouble(4,produit.getReserve());
            ps.setInt(5,produit.getCategorie().getId());
            ps.setInt(6,produit.getEntrepot().getId_entrepot());
            ps.execute();
              System.out.println("produit modifié");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
        }
        
    return produit;
    }
    
}
