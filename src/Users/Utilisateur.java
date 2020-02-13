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

    public Utilisateur() {
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
    
  
   
    
    
    
    
    
    
    
    
    
    
    
}
