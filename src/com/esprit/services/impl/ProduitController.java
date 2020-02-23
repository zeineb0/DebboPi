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
        System.out.println(produit);
        String req ="INSERT INTO `produit`( "
                + "`libelle`, "
                + "`reference`,"
                + " `marque`, "
                + "`prix`, "
                + "`FK_id_categorie`,"
                + " `FK_id_entrepot`) "
                + "VALUES (?,?,?,?,?,?)";
                
        try {
             ps = conn.prepareStatement(req);
            ps.setString(1,produit.getLibelle());
            ps.setInt(2,produit.getReference());
            ps.setString(3,produit.getMarque());
            ps.setDouble(4,produit.getPrix());
            ps.setInt(5,produit.getCategorie().getId());
            ps.setInt(6,produit.getEntrepot().getId_entrepot());
            ps.execute();
            System.out.println("produit ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Produit non ajouté");
        }
                
    }

    @Override
    public void supprimerProduit(String libelle) {
        
        
        try {
                    String req="DELETE FROM `produit` WHERE `libelle`=?" ;

            ps = conn.prepareStatement(req);
            ps.setString(1, libelle);
            ps.execute();
             System.out.println("suppression validée");
            
        } catch (SQLException ex) {
            System.out.println("erreur de suppression");
        }
        
    }

    @Override
    public void consulterProduit(String nom){
        Produit p=new Produit();
        String req= "SELECT * FROM `produit` WHERE `libelle`=?";
        try {
        ps=conn.prepareStatement(req);
        ps.setString(1,nom);
        rs=ps.executeQuery();
        
         while (rs.next()) 
               {
                                      p.setId(rs.getInt("id_produit"));

                   p.setLibelle(rs.getString("libelle"));
                   p.setPrix(rs.getDouble("prix"));
                   p.setReference(rs.getInt("reference"));
                   p.setMarque(rs.getString("marque"));
                   System.out.println(p);
               }
        }
       
        catch (SQLException ex) {
            System.out.println("Produit introuvable");
        }

    }
     public Categorie getCategorie(int id){
           String req2 = "SELECT * FROM `categories` WHERE `id_categorie`=?";
                        Categorie c =new Categorie();

           try {
            PreparedStatement ps2 = conn.prepareStatement(req2);
               ps2.setInt(1, id);
                ResultSet rs2 = ps2.executeQuery();
                   while (rs2.next()) {  
                       c.setId(rs2.getInt("id_categorie"));
                       c.setNom(rs2.getString("nom"));
                       
                   }
            
        } catch (SQLException ex) {
              
        }
         return c;
        
    }
     public Entrepot getEntrepot (int id){
          Entrepot e = new Entrepot();
         
          String reeq = "SELECT * FROM `entrepot` WHERE `id_entrepot`=?";
                                            

        try {
            PreparedStatement ps4 = conn.prepareStatement(reeq);
                        ps4.setInt(1,id);
                                   ResultSet r2 = ps4.executeQuery();
                                   while(r2.next()){
                                       System.out.println(reeq);System.out.println("in while");
                                   e.setId_entrepot(r2.getInt("id_entrepot"));
                                   e.setAdresse_entrepot(r2.getString("adresse"));
                                   e.setNum_fiscale(r2.getInt("num_fiscale"));
                                   e.setQuantite_max(r2.getInt("quantite_max"));
                                   e.setEtat(r2.getString("etat"));
                                   e.setEntreprise(r2.getString("entreprise"));
                                   
                                   }
        
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
        }
                    return e;

}

    @Override
    public List<Produit> listeProduit() {
              List<Produit> produits = new ArrayList<>();
        String req = "SELECT * FROM `produit`";
        try {
            ps = conn.prepareStatement(req);
             rs = ps.executeQuery();
           
               while (rs.next()) 
               {
                   Produit p=new Produit();
                   p.setId(rs.getInt("id_produit"));
                  p.setLibelle(rs.getString("libelle"));
                   p.setPrix(rs.getDouble("prix"));
                   p.setReference(rs.getInt("reference"));
                   p.setMarque(rs.getString("marque"));
                   /**
                    * Jointure Fk_Produit_Categorie
                    */
                   Categorie c = getCategorie(rs.getInt("FK_id_categorie"));
                   p.setCategorie(c);
                   //affecter la categorie au produit
                 
                     //ajouter le produit dans la liste
                     Entrepot e=getEntrepot(rs.getInt("FK_id_entrepot"));
                      p.setEntrepot(e);
                      
                      produits.add(p);

               }


        } catch (SQLException ex) {
            System.out.println("erreur ");
        }
        System.out.println(produits);
        return produits;
    }

    @Override
    public Produit modiferProduit(Produit produit)  {
        String req = "UPDATE `produit` SET "
                + "`libelle`=?,"
                + "`reference`=?,"
                + "`marque`=?,"
                + "`prix`=?,"
                + "`FK_id_categorie`=?,"
                + "`FK_id_entrepot`=? "
                + "WHERE `id_produit`=?";
                   
          try {
            ps = conn.prepareStatement(req);
            ps.setString(1,produit.getLibelle());
            ps.setInt(2,produit.getReference());
            ps.setString(3,produit.getMarque());
            ps.setDouble(4,produit.getPrix());
            ps.setInt(5,produit.getCategorie().getId());
            ps.setInt(6,produit.getEntrepot().getId_entrepot());
            ps.setInt(7,produit.getId());
            ps.execute();
              System.out.println("produit modifié");
        } catch (SQLException ex) {
            
            System.out.println("erreur de modification");
        }
        
    return produit;
    }
    
}
