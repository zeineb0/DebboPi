/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.services.impl;


import com.esprit.entities.Entrepot;
import com.esprit.services.IService;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class ServiceEntrepot implements IService<Entrepot> {

    private final Connection con;
    private Statement ste;
    
    public ServiceEntrepot() {
    con = DataSource.getInstance().getConnection();

    }

    
    @Override
    public void ajouter(Entrepot e) throws SQLException {
    try{    
    PreparedStatement pre=con.prepareStatement("INSERT INTO `entrepot` (`adresse`, `num_fiscale`, `quantite_max`, `etat`, `entreprise`,`fk_id_user`) VALUES ( ?, ?, ?, ?, ?,?);");
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
       PreparedStatement pre = con.prepareStatement("DELETE FROM `entrepot` WHERE `id_entrepot`= ?");
       pre.setInt(1, nb);
       pre.executeUpdate();
       System.out.println( "l'entrepot qui a l'id :" +nb + " est supprimé.");
     }
     catch(SQLException ex)
     {System.out.println("com.esprit.services.impl.ServiceEntrepot.delete()");
     }
       

    }
    
    @Override
    public int update(Entrepot e) throws SQLException {
    int st=0;
        try {
            PreparedStatement ps=con.prepareStatement("UPDATE `entrepot` SET `adresse`=?,`num_fiscale`=?,`quantite_max`=?,`etat`=?,`entreprise`=?  WHERE `id_entrepot`=?;");
            ps.setString(1, e.getAdresse_entrepot());
            ps.setInt(2, e.getNum_fiscale());
            ps.setInt(3,  e.getQuantite_max());
            ps.setString(4, e.getEtat());
            ps.setString(5, e.getEntreprise());
            ps.setInt(6, e.getId_entrepot());
            st = ps.executeUpdate();
            System.out.println("entrepot"+e.getId_entrepot());
        } catch (SQLException ex) {
               System.out.println("com.esprit.services.impl.ServiceEntrepot.update()");
        }
        return st;
    }
    public Entrepot getEntrepotId(int id) throws SQLException
    { Entrepot e = new Entrepot();
        try {
           PreparedStatement ps=con.prepareStatement("select * from entrepot WHERE `id_entrepot`=?;");
           ps.setInt(1, id);
           ResultSet rs=ps.executeQuery();
           while(rs.next())
           {e.setId_entrepot(rs.getInt(1));
            e.setAdresse_entrepot(rs.getString(2));
            e.setNum_fiscale(rs.getInt(3));
            e.setQuantite_max(rs.getInt(4));
            e.setEtat(rs.getString(5));
            e.setEntreprise(rs.getString(6));
            
           }
           
           }
        catch (SQLException ex) {
               System.out.println("com.esprit.services.impl.ServiceEntrepot.getEntrepotId()");}
       return e;        
    }

    @Override
    public List<Entrepot> readAll() throws SQLException {
    List<Entrepot> entrepots=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from entrepot");
     while (rs.next()) {                
               int id_entrepot=rs.getInt(1);
               String adresse_entrepot=rs.getString(2);
               int num_fiscale=rs.getInt(3);
               int quantite_max=rs.getInt(4);
               String etat = rs.getString(5);
               String entreprise=rs.getString(6);
               //int fk_id_fournisseur=rs.getInt(7);
               Entrepot e =new Entrepot(id_entrepot, adresse_entrepot, num_fiscale, quantite_max, etat, entreprise);
     entrepots.add(e);
     }
    return entrepots;
    }

   
}
