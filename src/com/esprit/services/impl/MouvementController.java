/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.MouvementStock;
import com.esprit.entities.Produit;
import com.esprit.services.IMouvementService;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class MouvementController implements IMouvementService{

    Connection conn = DataSource.getInstance().getConnection();
    private ResultSet rs;
    private PreparedStatement ps;
    
    @Override
    public void ajouterMouvement(MouvementStock m) {
                if (m.getQuantite() != 0){
                
                String req1 = "UPDATE `mouvement_du_stock` SET"
                        + " `nature_mouvement`=?,"
                        + "`date_mouv`=?,"
                        + "`quantite`=?,"
                        + "`FK_id_produit`=?,"
                        + "`FK_id_entrepot`=? "
                        + "WHERE `id_mouv`=?";
                
                
                try {
                    Produit p = new Produit();
                ps=conn.prepareStatement(req1);
                ps.setString(1,m.getNatureDuStock());
                ps.setDate(2,m.getDateMouv());
//                int idp =p.getId();
//                if (m.getP().getId()=idp){
//                
//                
//                }
                ps.setInt(3,m.getQuantite());
                ps.setInt(4,m.getP().getId());
                ps.setInt(5,m.getE().getId_entrepot());
                ps.setInt(6,m.getId());
                ps.execute();
            System.out.println("mvt bien ajouté");
                }catch (SQLException e){
                
                    System.out.println(e);
                }
                }
                else{
        
        
        String req = "INSERT INTO `mouvement_du_stock`( "
                + "`nature_mouvement`,"
                + " `date_mouv`, "
                + "`quantite`, "
                + "`FK_id_produit`, "
                + "`FK_id_entrepot`)"
                + "VALUES (?,?,?,?,?) ";
        try {
        ps=conn.prepareStatement(req);
         ps.setString(1,m.getNatureDuStock());
         ps.setDate(2,m.getDateMouv());
         ps.setInt(3,m.getQuantite());
         ps.setInt(4,m.getP().getId());
         ps.setInt(5,m.getE().getId_entrepot());
        ps.execute();
            System.out.println("mvt bien ajouté");
            } catch (SQLException ex) {
            System.out.println(ex);    }}}
    
    
    public  void testQuantité (MouvementStock m){
    
        MouvementStock mvt = new MouvementStock();
        if (mvt.getQuantite()!= 0){
            int nvqte=0;
         nvqte = nvqte+ mvt.getQuantite();
             
        String req = "INSERT INTO `mouvement_du_stock`( "
                + "`nature_mouvement`,"
                + " `date_mouv`, "
                + "`quantite`, "
                + "`FK_id_produit`, "
                + "`FK_id_entrepot`)"
                + "VALUES (?,?,?,?,?)";
        try {
        ps=conn.prepareStatement(req);
         ps.setString(1,mvt.getNatureDuStock());
         ps.setDate(2,mvt.getDateMouv());
         ps.setInt(3,nvqte);
         ps.setInt(4,mvt.getP().getId());
         ps.setInt(5,mvt.getE().getId_entrepot());
        ps.execute();
            } catch (SQLException ex) {
            System.out.println("erreur");    }
            
            
            
        }
        else ajouterMouvement(mvt);
    
    }

    @Override
    public void modifierMouvement(MouvementStock m) {
        
        
        
        
     String req = "UPDATE `mouvement_du_stock` SET `nature_mouvement`=?,`date_mouv`=?,`FK_id_produit`=?,`FK_id_entrepot`=? WHERE 1";  
     try{
     ps=conn.prepareStatement(req);
     ps.setString(1,m.getNatureDuStock());
     ps.setDate(2,m.getDateMouv());
     ps.setInt(3,m.getP().getId());
     ps.setInt(4,m.getE().getId_entrepot());
     }
     catch(SQLException ex){
             System.out.println("erreur");}
     
    }
    

    @Override
    public void supprimerMouvement(MouvementStock m) {
        
        
    }

    @Override
    public void consulterMouvement(String nature) {
        MouvementStock m = new MouvementStock();
        String req= "SELECT * FROM `mouvement_du_stock` WHERE `nature_mouvement` LIKE ?";
        try {
        ps=conn.prepareStatement(req);
        ps.setString(1,nature);
        rs=ps.executeQuery();
        
         while (rs.next()) 
               {
                   m.setDateMouv(rs.getDate("date_mouv"));
                   m.setNatureDuStock(rs.getString("natureDuStock"));
               }
        }
        catch (SQLException ex) {
            System.out.println("mouvement introuvable");
          
        }
    }
    
}
