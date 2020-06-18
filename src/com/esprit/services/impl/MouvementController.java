/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.MouvementStock;
import com.esprit.entities.Produit;
import com.esprit.entities.Session;
import com.esprit.services.IMouvementService;
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
public class MouvementController implements IMouvementService{

    Connection conn = DataSource.getInstance().getConnection();
    private ResultSet rs;
    private PreparedStatement ps;
    
    
    @Override
    public void ajouterMouvement(MouvementStock m) {
               
        
        String req = "INSERT INTO `mouvement_du_stock`("
                + "`nature_mouvement`, "
                + "`date_mouv`, "
                + "`FK_id_produit`, "
                + "`FK_id_entrepot`,"
                + "`id_user`)"
                + " VALUES (?,?,?,?,?)";
        try {
        ps=conn.prepareStatement(req);
         ps.setString(1,m.getNatureDuStock());
         ps.setDate(2,m.getDateMouv());
         ps.setInt(3,m.getP().getId());
         ps.setInt(4,m.getE().getId_entrepot());
         ps.setInt(5,Session.getIdSession());
        ps.execute();
            System.out.println("mvt bien ajouté");
            } catch (SQLException ex) {
            System.out.println(ex);    }
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
