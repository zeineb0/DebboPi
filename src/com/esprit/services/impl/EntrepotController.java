/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Entrepot;
import com.esprit.services.IEntrepotService;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class EntrepotController implements IEntrepotService{
 Connection conn = DataSource.getInstance().getConnection();
    private ResultSet rs;
    private PreparedStatement ps;
        private Statement ste;

    @Override
    public void ajouter(Entrepot e) throws SQLException {
    try{
    PreparedStatement pre=conn.prepareStatement("INSERT INTO `entrepot` (`adresse`, `num_fiscale`, `quantite_max`, `etat`, `entreprise`,`fk_id_user`) VALUES ( ?, ?, ?, ?, ?,?);");
    pre.setString(1, e.getAdresse_entrepot());
    pre.setInt(2, e.getNum_fiscale());
    pre.setInt(3, e.getQuantite_max());
    pre.setString(4, e.getEtat());
    pre.setString(5, e.getEntreprise());
    pre.setInt(6, e.getFk_id_fournisseur());
    pre.executeUpdate();
    }
    catch(SQLException ex)
    {System.out.println("com.esprit.services.impl.ServiceEntrepot.ajouter()");
    }
    }
    @Override
    public void delete(int nb) throws SQLException {
     try{
       PreparedStatement pre = conn.prepareStatement("DELETE FROM `entrepot` WHERE `id_entrepot`= ?");
       pre.setInt(1, nb);
       pre.executeUpdate();
       System.out.println( "l'entrepot qui a l'id :" +nb + " est supprimé.");
     }
     catch(SQLException ex)
     {System.out.println("com.esprit.services.impl.ServiceEntrepot.delete()");
     }
    }
    @Override
    public void update(Entrepot e) throws SQLException {
    try {
            PreparedStatement ps=conn.prepareStatement("UPDATE `entrepot` SET `adresse`=?,`num_fiscale`=?,`quantite_max`=?,`etat`=?,`entreprise`=? ,`fk_id_user`=? WHERE `id_entrepot`=?;");
            ps.setString(1, e.getAdresse_entrepot());
            ps.setInt(2, e.getNum_fiscale());
            ps.setInt(3,  e.getQuantite_max());
            ps.setString(4, e.getEtat());
            ps.setString(5, e.getEntreprise());
            ps.setInt(6, e.getFk_id_fournisseur());
            ps.setInt(7, e.getId_entrepot());
            ps.executeUpdate();
            System.out.println(e.getId_entrepot()+ " updated.");
        } catch (SQLException ex) {
               System.out.println("com.esprit.services.impl.ServiceEntrepot.update()");
        }
    }
    @Override
    public List<Entrepot> readAll() {
     try {
         List<Entrepot> entrepots=new ArrayList<>();
         ste=conn.createStatement();
         ResultSet rs=ste.executeQuery("select * from entrepot");
         while (rs.next()) {
             int id_entrepot=rs.getInt(1);
             String adresse_entrepot=rs.getString(2);
             int num_fiscale=rs.getInt(3);
             int quantite_max=rs.getInt(4);
             String etat = rs.getString(5);
             String entreprise=rs.getString(6);
             int fk_id_fournisseur=rs.getInt(7);
             Entrepot e =new Entrepot(id_entrepot, adresse_entrepot, num_fiscale, quantite_max, etat, entreprise, fk_id_fournisseur);
             entrepots.add(e);
         }
         return entrepots;
     } catch (SQLException ex) {
         Logger.getLogger(EntrepotController.class.getName()).log(Level.SEVERE, null, ex);
     }
     return null;
    
   
}}
