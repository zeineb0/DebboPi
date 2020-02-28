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
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

            String req="UPDATE `entrepot` SET`etat`=? WHERE entrepot.`id_entrepot` = ?";
            PreparedStatement ps= DataSource.getInstance().getConnection().prepareStatement(req);
            
            ps.setString(1, "A Louer");
            ps.setInt(2,id);
            

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
            + " e.quantite_max , e.adresse ,e.entreprise, u.nom, u.prenom ,FK_id_entrepot from `location` l INNER join entrepot e INNER join utilisateur u where l.FK_id_entrepot = e.id_entrepot and u.id_user= e.fk_id_user and etat= \"Loué\"" );
     while (rs.next()) {                
               int id_location=rs.getInt(1);
               Date date_deb_location = rs.getDate(2);
               Date date_fin_location = rs.getDate(3);
               double prix_location = rs.getDouble(4);
               int quantite_max = rs.getInt(5);
               String adresse_entrepot=rs.getString(6);
               String entreprise=rs.getString(7);
               
               String nom=rs.getString(8);
               String prenom=rs.getString(9);
               int FK_id_entrepot = rs.getInt(10);
         System.out.println(FK_id_entrepot);
           //  int FK_id_user = rs.getInt(11);
               
     
             
               
    LocationDetail l = new LocationDetail(id_location, date_deb_location, date_fin_location, prix_location, quantite_max, adresse_entrepot,entreprise, nom, prenom, FK_id_entrepot);
         System.out.println(l.toString());
    locationsDetails.add(l);
     } 
    return locationsDetails;
    }
  

public Double calculPrix (Double prix,Date datedeb, Date datefin)
{ 
        double prix1;
         System.out.println(prix);
        final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 
        
        return prix1= ((prix/31)*(datefin.getTime()-datedeb.getTime()+1))/ (MILLISECONDS_PER_DAY);
        

}
public void pdf(int id) throws FileNotFoundException, DocumentException, BadElementException, IOException
    {
        try {
            String file_name ="C:\\Users\\asus\\Desktop\\pdf\\walid.pdf";
            Document document = new Document();
            //file_name.setReadable(true,false);
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            ste=con.createStatement();
            ResultSet rs =ste.executeQuery("SELECT id_location , date_deb_location , date_fin_location, l.prix_location ,  e.quantite_max , e.adresse ,e.entreprise, u.nom, u.prenom ,FK_id_entrepot from `location` l INNER join entrepot e INNER join utilisateur u where l.FK_id_entrepot = e.id_entrepot and u.id_user= e.fk_id_user and l.id_location="+id+ "");
           
            
            while (rs.next()) { 
                Paragraph para=new Paragraph((" Nom: " +rs.getString(8) + "\n Prenom : " + rs.getString(9)+ "\n Entreprise : " + rs.getString(7)+ "\n Adresse : " + rs.getString(6)+" \n Date debut de location " + rs.getDate(2)
                            +"\n Date de fin de location: "+rs.getDate(3) +"\n Prix de location: "+rs.getDouble(4)
                            +"\n Quantité maximale d'entrepot: "+rs.getDouble(5)));
                document.add(para);
                document.add(Image.getInstance("C:\\Users\\asus\\Desktop\\pdf\\logo.png"));
                document.add(new Paragraph(" "));
                 document.add(new Paragraph(" "));
            }
            document.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLocation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ServiceLocation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServiceLocation.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    
    
//public List<Facture> afficherByreference(String n) {
//        List<Facture> myList = new ArrayList<Facture>();
//        try {
//
//            String requete2 = "SELECT * FROM facture WHERE reference LIKE '%" + n + "%'";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(requete2);
//
//            while (rs.next()) {
//                Facture u = new Facture();
//                u.setId_facture(rs.getInt(1));
//                u.setReference(rs.getString(2));
//                u.setId_achat(rs.getInt(3));
//                u.setClient_name(rs.getString(4));
//                u.setClient_type(rs.getString(5));
//                u.setType_facture(rs.getString(6));
//                u.setStatut_facture(rs.getString(7));
//                u.setTotalHT(rs.getFloat(8));
//                u.setTotalTTC(rs.getFloat(9));
//                u.setEcheance(rs.getString(10));
//                u.setDelivery(rs.getInt(11));
//  
//
//                myList.add(u);
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        return myList;
//    }  
//     
//      public List<Facture> afficherByClient_name( String n) {
//        List<Facture> myList = new ArrayList<Facture>();
//        try {
//
//            String requete2 = "SELECT * FROM facture WHERE client_name LIKE '%" + n + "%'";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(requete2);
//
//            while (rs.next()) {
//                Facture u = new Facture();
//                u.setId_facture(rs.getInt(1));
//                u.setReference(rs.getString(2));
//                u.setId_achat(rs.getInt(3));
//                u.setClient_name(rs.getString(4));
//                u.setClient_type(rs.getString(5));
//                u.setType_facture(rs.getString(6));
//                u.setStatut_facture(rs.getString(7));
//                u.setTotalHT(rs.getFloat(8));
//                u.setTotalTTC(rs.getFloat(9));
//                u.setEcheance(rs.getString(10));
//                u.setDelivery(rs.getInt(11));
//  
//                   myList.add(u);
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        return myList;
//    }



}
