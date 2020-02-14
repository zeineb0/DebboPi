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
    PreparedStatement pre=con.prepareStatement("INSERT INTO `location` (`id_location`, `date_location`, `prix_location`, `FK_id_entrepot`, `FK_id_user`)  VALUES ( ?, ?, ?, ?, ?);");
    pre.setInt(1, l.getId_location());
    pre.setDate(2, l.getDate_location());
    pre.setDouble(3, l.getPrix_location());
    pre.setInt(4, l.getFK_id_entrepot());
    pre.setInt(5, l.getFK_id_user());
    pre.executeUpdate(); 
    }

    @Override
    public void delete(int nb) throws SQLException {
    PreparedStatement pre = con.prepareStatement("DELETE FROM `location` WHERE `id_location`= ?");
       pre.setInt(1, nb);
       pre.executeUpdate();
       System.out.println( nb + " deleted.");

       
       
    }

    @Override
    public void update(Location l) throws SQLException {
try {
            PreparedStatement pre=con.prepareStatement("UPDATE `location` SET `date_location`=?,`prix_location`=?,`FK_id_entrepot`=?,`FK_id_user`=? WHERE `id_location`=?;");
            pre.setInt(1, l.getId_location());
            pre.setDate(2, l.getDate_location());
            pre.setDouble(3, l.getPrix_location());
            pre.setInt(4, l.getFK_id_entrepot());
            pre.setInt(5, l.getFK_id_user());
            pre.setInt(6, l.getId_location());
            pre.executeUpdate();
            System.out.println("la loc num"+ l.getId_location()+ " updated.");
            
        } catch (SQLException ex) {
               
        }
    }

    @Override
    public List<Location> readAll() throws SQLException {
    List<Location> locations=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from location");
     while (rs.next()) {                
               int id_location=rs.getInt(1);
               Date date_location = rs.getDate(2);
               double prix_location = rs.getDouble(3);
               int FK_id_entrepot = rs.getInt(4);
               int FK_id_user = rs.getInt(5);
               
               
               
               
    Location l = new Location(id_location, date_location, prix_location, FK_id_entrepot, FK_id_user);
    locations.add(l);
     }
    return locations;
    }
    
    
}
