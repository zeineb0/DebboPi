/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Commande;
import com.esprit.entities.Produit;
import com.esprit.utilities.DataSource;
import com.sun.org.apache.bcel.internal.generic.D2F;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ghazi
 */
public class ServiceCommande {
     Connection cnx;

    public ServiceCommande() {
             cnx=DataSource.getInstance().getConnection();}
    
      public void addCommande(Commande c) throws SQLException{
        
          String req="INSERT INTO `commande`(list_produit,total,type_paiement,date_commande,date_exp,id_client) "
                  + "VALUES('"+c.getListProduit()+"','"+c.getMontant()+"','"+c.getType_paiement()+"','"+getDate((Date)c.getDateCommande())+"','"+getDate((Date)c.getDateExp())+"','"+c.getIdClient()+"')";
      PreparedStatement pst = cnx.prepareStatement(req);
      pst.executeUpdate(req);
    }


    public List<Commande> getAllCommande() throws SQLException {
      String req="SELECT * FROM `commande`";
      PreparedStatement pst = cnx.prepareStatement(req);
      ResultSet rst = pst.executeQuery(req);
      List <Commande> commandes= new ArrayList<>();
      Commande c= new Commande();
      while(rst.next())
      {   c.setIdCommande(rst.getInt("id_commande"));
          c.setDateCommande(rst.getDate("date_commande"));
          c.setDateExp(rst.getDate("date_exp"));
          c.setMontant(rst.getFloat("total"));
          c.setIdClient(rst.getInt("id_client"));
          c.setListProduit(rst.getString("list_produit"));
          
      }
        
      return commandes;
          }

    public void updateCommande(Commande c) throws SQLException {
                String req="UPDATE `commande` SET `list_produi`='"+c.getListProduit()+"',`type_paiement`='"+c.getType_paiement()+"',`date_commande`='" +getDate((Date)c.getDateCommande())+"',`date_exp`='"+getDate((Date)c.getDateExp())+"',`total`="+c.getMontant()+" where `id_commande`="+c.getIdCommande();
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.executeUpdate(req);
    }


    public void deleteCommande(Commande c) throws SQLException {
         String req="DELETE FROM `commande` where `id_commande`="+c.getIdCommande();
      PreparedStatement pst = cnx.prepareStatement(req);
      pst.executeUpdate(req);
    }
   
    public String getDate(Date d)
    {
        String year=d.getYear()+"";
         String day=d.getDate()+"";
         String month=d.getMonth()+"";
        return(year+"-"+month+"-"+day);
    }
    
}
