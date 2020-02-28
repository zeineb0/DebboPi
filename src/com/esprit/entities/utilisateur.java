/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author asus
 */
public class utilisateur {
 
    
    private int id;
    private String nom;
    private String prenom;
    private int cin;
    private Date dn;
    private String role;
    private int tel;
    private int longtitude_user;
    private int altitude_user;
    private String email;
    private String password;
    private String disponibilite;
    

   
     String url = "jdbc:mysql://localhost:3306/debbov1";
     String username = "root";
     String pword = "";
     Connection con; 
     
      public utilisateur() {
    }

    public utilisateur(String nom, String prenom, int cin, Date dn, String role, int tel, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.dn = dn;
        this.role = role;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }
    
    
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getLongtitude_user() {
        return longtitude_user;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    

    public void setLongtitude_user(int longtitude_user) {
        this.longtitude_user = longtitude_user;
    }

    public int getAltitude_user() {
        return altitude_user;
    }

    public void setAltitude_user(int altitude_user) {
        this.altitude_user = altitude_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public Date getDn() {
        return dn;
    }

    public void setDn(Date dn) {
        this.dn = dn;
    }
    
    
    
  public String toString(){
      return "Id :"+this.getId()+"\n"+"Nom :"+this.getNom()+"\n"+"Prénom :"+this.getPrenom()+"\n"+"Cin :"+this.getCin()+"\n"+
              "Date de naissance :"+this.getDn()+"\n"+"Role :"+this.getRole()+"\n"+"Coordonneés(longtitude,altitude) : ("+this.getLongtitude_user()+","+this.getAltitude_user()+")"+"\n"+
              "Email :"+this.getEmail()+"\n"+"Disponibilité :"+this.getDisponibilite();
              
  }
  
    public ResultSet afficherDonneesUser() throws SQLException{

      
      String req="SELECT * FROM `utilisateur` WHERE `utilisateur`.`id_user` = "+this.getId();
      Statement st=con.createStatement();
      ResultSet rst = st.executeQuery(req);
    return rst;
}
    
     public void supprimerUser() throws SQLException{

      
      String req="DELETE FROM `utilisateur` WHERE `utilisateur`.`id_user` = "+this.getId();
         System.out.println(req);
      Statement st=con.createStatement();
      st.executeUpdate(req);
      
      
     }
  
     
     
   
       

}
