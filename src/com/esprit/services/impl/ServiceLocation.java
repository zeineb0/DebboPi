/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Entrepot;
import com.esprit.entities.Location;
import com.esprit.entities.LocationDetail;
import com.esprit.services.IServiceLocation;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
    PreparedStatement pre=con.prepareStatement("INSERT INTO `location` (`id_location`, `date_deb_location`,`date_fin_location`, `prix_location`, `FK_id_entrepot`, `FK_id_user`)  VALUES ( ?, ?, ?,?, ?, ?);");
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
    public Double getPrix(int id) throws SQLException {
            double prix1 =0;

        
       PreparedStatement pre = con.prepareStatement("SELECT `prix_location` FROM `entrepot` WHERE `id_entrepot` = ?");
       pre.setInt(1, id);
       ResultSet r =pre.executeQuery();
       while (r.next())
               {prix1=r.getDouble("prix_location");}
        return  prix1; 
 
    }
    
    public void modifierEtatEntrepotALoue(int id)
    {
        try {

            String req="UPDATE `entrepot` SET`etat`=\"Loué\" WHERE `id_entrepot` = ?";
            PreparedStatement ps= DataSource.getInstance().getConnection().prepareStatement(req);
            
            ps.setInt(1,id);
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
    }
    public void modifierEtatEntrepotALouer(int id)
    {
        try {

            String req="UPDATE `entrepot` SET`etat`=\"A Louer\" WHERE `id_entrepot` = ?";
            PreparedStatement ps= DataSource.getInstance().getConnection().prepareStatement(req);
            
            ps.setInt(1,id);
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
    }
    

    @Override
    public void update(Location l) throws SQLException {
try {
            PreparedStatement pre=con.prepareStatement("UPDATE `location` SET `date_fin_location`=?,`prix_location`=? WHERE `id_location`=?;");
            pre.setDate(1, l.getDate_fin_location());
            pre.setDouble(2, l.getPrix_location());
            pre.setInt(3, l.getId_location());
            pre.executeUpdate();
            System.out.println("la loc num"+ l.getId_location()+ " updated.");
            
        } catch (SQLException ex) {
        System.out.println("com.esprit.services.impl.ServiceLocation.update()");      
        }
    }
    public List<Entrepot> readAL() throws SQLException {
    List<Entrepot> entrepot=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `entrepot` WHERE `etat`=\"A louer\"");
     while (rs.next()) {                
              int id_entrepot=rs.getInt(1);
               String adresse_entrepot=rs.getString(2);
               int num_fiscale=rs.getInt(3);
               int quantite_max=rs.getInt(4);
               String etat = rs.getString(5);
               double prix_location=rs.getDouble(6);
               String entreprise=rs.getString(7);
             
               int fk_id_fournisseur=rs.getInt(8);
               Entrepot e =new Entrepot(id_entrepot, adresse_entrepot, num_fiscale, quantite_max, etat, prix_location, entreprise, fk_id_fournisseur);
     entrepot.add(e);
     }
    return entrepot;
    }

   
    public List<LocationDetail> readAl1() throws SQLException {
    List<LocationDetail> locationsDetails=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT id_location , date_deb_location , date_fin_location, l.prix_location ,"
            + " e.quantite_max , e.adresse , u.nom, u.prenom ,FK_id_entrepot from `location` l INNER join entrepot e INNER join utilisateur u where l.FK_id_entrepot = e.id_entrepot and u.id_user= e.fk_id_user" );
     while (rs.next()) {                
               int id_location=rs.getInt(1);
               Date date_deb_location = rs.getDate(2);
               Date date_fin_location = rs.getDate(3);
               double prix_location = rs.getDouble(4);
               int quantite_max = rs.getInt(5);
               String adresse_entrepot=rs.getString(6);
               String nom=rs.getString(7);
               String prenom=rs.getString(8);
                  int FK_id_entrepot = rs.getInt(9);

//               int FK_id_user = rs.getInt(10);
               
     
             
               
    LocationDetail l = new LocationDetail(id_location, date_deb_location, date_fin_location, prix_location, quantite_max, adresse_entrepot, nom, prenom, FK_id_entrepot);
    locationsDetails.add(l);
     } 
    return locationsDetails;
    }
  

public Double calculPrix (Double prix,Date datedeb, Date datefin)
{ 
        double prix1;
         System.out.println(prix);
        final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 

        return prix1= ((prix/31)*(datefin.getTime()-datedeb.getTime()))/ (MILLISECONDS_PER_DAY);


}





    @Override
    public List<Location> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getIDEntrepot(int id) throws SQLException {
            int idEn=0;
       PreparedStatement pre = con.prepareStatement("SELECT FK_id_entrepot FROM `location` WHERE `id_location` = ?");
       pre.setInt(1, id);
       ResultSet r =pre.executeQuery();

       while (r.next())
             {idEn=r.getInt("FK_id_entrepot");}

        return  idEn; 
    
   
       
           
  
    }

}
