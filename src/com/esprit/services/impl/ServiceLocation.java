/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Location;
import com.esprit.services.IServiceLocation;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.Date;
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
public class ServiceLocation implements IServiceLocation<Location>{
    private final Connection con;
    private Statement ste;
    
    public ServiceLocation() {
    con = DataSource.getInstance().getConnection();

    }
    @Override
    public void ajouter(Location l) throws SQLException {
    try{
    PreparedStatement pre=con.prepareStatement("INSERT INTO `location` (`id_location`, `date_deb_location`,`date_fin_location`, `prix_location`, `FK_id_entrepot`, `FK_id_user`)  VALUES ( ?, ?, ?, ?, ?);");
    pre.setInt(1, l.getId_location());
    pre.setDate(2, l.getDate_deb_location());
    pre.setDate(3, l.getDate_fin_location());
    pre.setDouble(4, l.getPrix_location());
    pre.setInt(5, l.getFK_id_entrepot());
    pre.setInt(6, l.getFK_id_user());
    pre.executeUpdate(); 
    }
    catch(SQLException ex)
    {System.out.println("com.esprit.services.impl.ServiceLocation.ajouter()");
    }
    }

    @Override
    public void delete(int nb) throws SQLException {
    try{
       PreparedStatement pre = con.prepareStatement("DELETE FROM `location` WHERE `id_location`= ?");
       pre.setInt(1, nb);
       pre.executeUpdate();
       System.out.println( nb + " deleted.");
    }
    catch(SQLException ex)
    {
        System.out.println("com.esprit.services.impl.ServiceLocation.delete()");
    }
       
       
    }

    @Override
    public void update(Location l) throws SQLException {
try {
            PreparedStatement pre=con.prepareStatement("UPDATE `location` SET `date_deb_location`=?,`date_fin_location`=?,`prix_location`=?,`FK_id_entrepot`=?,`FK_id_user`=? WHERE `id_location`=?;");
            pre.setInt(1, l.getId_location());
            pre.setDate(2, l.getDate_deb_location());
            pre.setDate(3, l.getDate_fin_location());
            pre.setDouble(4, l.getPrix_location());
            pre.setInt(5, l.getFK_id_entrepot());
            pre.setInt(6, l.getFK_id_user());
            pre.setInt(7, l.getId_location());
            pre.executeUpdate();
            System.out.println("la loc num"+ l.getId_location()+ " updated.");
            
        } catch (SQLException ex) {
        System.out.println("com.esprit.services.impl.ServiceLocation.update()");      
        }
    }

    @Override
    public List<Location> readAll() throws SQLException {
    List<Location> locations=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from location");
     while (rs.next()) {                
               int id_location=rs.getInt(1);
               Date date_deb_location = rs.getDate(2);
               Date date_fin_location = rs.getDate(3);
               double prix_location = rs.getDouble(4);
               int FK_id_entrepot = rs.getInt(5);
               int FK_id_user = rs.getInt(6);
               
               
             
               
    Location l = new Location(id_location, date_deb_location, date_fin_location, prix_location, FK_id_entrepot, FK_id_user);
    locations.add(l);
     }
    return locations;
    }
   public List<Location> trierLocationParPrixCroissant() throws SQLException{
   List<Location> locations2 =new ArrayList<>();
   ste= con.createStatement();
   ResultSet rs = ste.executeQuery("SELECT * FROM location ORDER BY`prix_location`ASC");
   while (rs.next())
   {           int id_location=rs.getInt(1);
               Date date_deb_location = rs.getDate(2);
               Date date_fin_location = rs.getDate(3);
               double prix_location = rs.getDouble(4);
               int FK_id_entrepot = rs.getInt(5);
               int FK_id_user = rs.getInt(6);
               
    Location l = new Location(id_location, date_deb_location, date_fin_location, prix_location, FK_id_entrepot, FK_id_user);
    locations2.add(l);
}
     return locations2;  
}
   
public List<Location> trierLocationParDateCroissant() throws SQLException{
   List<Location> locations3 =new ArrayList<>();
   ste= con.createStatement();
   ResultSet rs = ste.executeQuery("SELECT * FROM location ORDER BY`date_location`ASC");
   while (rs.next())
   {           int id_location=rs.getInt(1);
              Date date_deb_location = rs.getDate(2);
               Date date_fin_location = rs.getDate(3);
               double prix_location = rs.getDouble(4);
               int FK_id_entrepot = rs.getInt(5);
               int FK_id_user = rs.getInt(6);
               
               
    Location l = new Location(id_location, date_deb_location, date_fin_location, prix_location, FK_id_entrepot, FK_id_user);
    locations3.add(l);
}
     return locations3;
              
 
    
}
}
