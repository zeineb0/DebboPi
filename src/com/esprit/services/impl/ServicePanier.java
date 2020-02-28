/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Commande;
import com.esprit.entities.Panier;
import com.esprit.entities.Produit;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ghazi
 */
public class ServicePanier {
    Connection cnx;

    public ServicePanier() {
             cnx=DataSource.getInstance().getConnection();}
    public void addPanier(Panier p) throws SQLException{
 
        
      String req="INSERT INTO `panier`(list_produit,nbr_produit,etat_panier,id_client) VALUES('','0','v','"+p.getId_client()+"')";
      PreparedStatement pst = cnx.prepareStatement(req);
      pst.executeUpdate(req);
    }
    public List<Panier> getAllPannier() throws SQLException{
      String req="SELECT * FROM `panier`";
      PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rst= pst.executeQuery(req);
        List<Panier> paniers =  new ArrayList<>();
     /* Panier p= new Panier();
      while(rst.next())
      {   p.setId_panier(rst.getInt("id_panier"));
          String etat=rst.getString("etat_panier");
          p.setEtatPanier(etat.charAt(0));
          String listProduit=rst.getString("list_produit");
          Produit list[];
          try{
          list = new Produit[100]; 
          String c="",num="0123456789"; int k=0; Produit pr;int l=0;
          for(int i=0;i<listProduit.length();i++)
          { if(listProduit.charAt(i)!='-')
          {c=c+listProduit.charAt(i);k=0;}
            else
          {   for(int j=0;j<c.length();j++)
            { k=(k*10)+num.indexOf(c.charAt(j));
            }
            pr=new Produit(k);
              list[l]=pr; 
              l++;
          }
          }
          
          p.setListProduit(list);
          p.setNbrProduit(rst.getInt("nbr_produit"));
          p.setId_client(rst.getInt("id_client"));
          paniers.add(p);
          }catch(ArrayIndexOutOfBoundsException e){
              System.out.println("erreur");
          }
      }
          */
      return paniers;
    }
    public void updatePannier(Panier p) throws SQLException{
        
       
          String req="UPDATE `panier` SET `list_produit`='"+p.getListProduit()+"',`nbr_produit`='"+p.getNbrProduit()+"',`etat_panier`='"+p.getEtatPanier()+"' where `id_panier`="+p.getId_panier();
      PreparedStatement pst = cnx.prepareStatement(req);
      pst.executeUpdate(req);
    }
    public void deletePannier(Panier p) throws SQLException{
         String req="DELETE FROM `panier` where `id_panier`="+p.getId_panier();
      PreparedStatement pst = cnx.prepareStatement(req);
      pst.executeUpdate(req);
    }
        public Panier getPannier(int id_client) throws SQLException{
         String req="SELECT * FROM `panier` where `id_client`="+id_client;
      PreparedStatement pst = cnx.prepareStatement(req);
      ResultSet rst=pst.executeQuery(req);
      Panier p=null;
      while(rst.next())
      {
          
          p = new Panier(rst.getInt(1),id_client , rst.getString(2),rst.getInt(3) , rst.getString(4).charAt(0));
      }
      return p;
    }
}
