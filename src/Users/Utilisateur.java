/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Utilisateur {
    
    private int id;
    private String nom;
    private String prenom;
    private int cin;
    private Date dn;
    private String role;
    private int longtitude_user;
    private int altitude_user;
    private String email;
    private String password;
    private String disponibilite;

    public Utilisateur() {
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
  @Override  
  public String toString(){
      return "Id :"+this.getId()+"\n"+"Nom :"+this.getNom()+"\n"+"Prénom :"+this.getPrenom()+"\n"+"Cin :"+this.getCin()+"\n"+
              "Date de naissance :"+this.getDn()+"\n"+"Role :"+this.getRole()+"\n"+"Coordonneés(longtitude,altitude) : ("+this.getLongtitude_user()+","+this.getAltitude_user()+")"+"\n"+
              "Email :"+this.getEmail()+"\n"+"Disponibilité :"+this.getDisponibilite();
              
  }
    
}
