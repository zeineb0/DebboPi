/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.User;
import com.esprit.services.IUserService;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class UserService implements IUserService{
    
    private final Connection con;
    private Statement ste;

    public UserService() {
        con = DataSource.getInstance().getConnection();
    }
    
   
    
    
  public void ajouterAuBd(User u) throws SQLException {
       
         String req="INSERT INTO `utilisateur` (`id_user`, `nom`, `prenom`, `cin`, `date`, `role`, `tel`, `longitude_user`, `altitude_user`, `email`, `password`, `disponniblite`, `nbr_maxComm`, `FK_id_produit`) VALUES "
                 + "(NULL, '"+u.getNom()+" '"+u.getPrenom()+"', '"+u.getCin()+"', '"+u.getDn()+"', '"+u.getRole()+"', '"+u.getTel()+"', '"+u.getLongtitude_user()+"', '"+u.getAltitude_user()+"', '"+u.getEmail()+"', '"+u.getPassword()+"', '"+u.getDisponibilite()+"', NULL, '')";
         Statement st=con.createStatement();
            st.executeUpdate(req);
         
     }
  
    @Override
      public void changerMdp(User u ,String amdp,String nmdp) throws SQLException{
           
       if (u.getPassword().equals("amdp")){
           u.setPassword(nmdp);
           String req="UPDATE `utilisateur` SET `password`="+nmdp+" WHERE `utilisateur`.`id_user` = "+u.getId();
           
           
           System.out.println("Password changed succesfully !");
       }
       else System.out.println("old password is uncorrect , please try again !");
    }
  
    @Override
           public void supprimerUser(User u) throws SQLException{

      
      String req="DELETE FROM `utilisateur` WHERE `utilisateur`.`id_user` = "+u.getId();
         System.out.println(req);
      Statement st=con.createStatement();
      st.executeUpdate(req);
      
      
     }
  
  
  
  
}
