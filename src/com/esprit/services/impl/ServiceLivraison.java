/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Livraison;
import com.esprit.services.IService;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ServiceLivraison implements IService<Livraison> {

    private final Connection con;
    private Statement ste;

    public ServiceLivraison() {
        con = DataSource.getInstance().getConnection();

    }

    @Override
    public void ajouter(Livraison l) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `livraison` (`date_livraison`, `adresse_livraison`, `etat_livraison`, `longitude_dest`, `altitude_dest` , `acceptation`) VALUES (?,?,?,?,?,?);");
    
    pre.setString(1, l.getDate_livraison());
    pre.setString(2, l.getAdresse_livraison());
    pre.setString(3, l.getEtat_livraison());
    pre.setFloat(4, l.getLongitude_dest());
    pre.setFloat(5, l.getAltitude_dest());
    pre.setString(6, l.getAcceptation());
    pre.executeUpdate();
    }
    @Override
    public boolean delete(Livraison l) throws SQLException
    {
       boolean x=true;
       return x;
        
    }
    @Override
    public boolean update(Livraison l) throws SQLException
    {
           boolean x=true;
       return x;
        
    }

    

    @Override
    public List<Livraison> readAll() throws SQLException {
    List<Livraison> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Livraison");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String date=rs.getString(2);
               String adr=rs.getString(3);
               String etat=rs.getString(4);
               float longitude=rs.getFloat(5);
               float altitude=rs.getFloat(6);
               String acc=rs.getString(7);
               int fk1=rs.getInt(7);
               int fk2=rs.getInt(8);
               Livraison l=new Livraison(id,date, adr, etat, longitude,altitude,acc,fk1,fk2);
     arr.add(l);
     }
    return arr;
    }

}
