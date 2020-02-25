/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Date;

/**
 *
 * @author ASUS X550V
 */
public class affdetails {
    public String hashmat;
    public Date date;
    public String adresse;
    public String etat;
    public String nom;
    public String prenom;
    public String tel;

    public affdetails(String hashmat, Date date, String adresse, String etat, String nom, String prenom, String tel) {
        this.hashmat = hashmat;
        this.date = date;
        this.adresse = adresse;
        this.etat = etat;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
    }





    public affdetails(java.sql.Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "affdetails{" + "date=" + date + ", adresse=" + adresse + ", etat=" + etat + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + '}';
    }

    public String getHashmat() {
        return hashmat;
    }

    public void setHashmat(String hashmat) {
        this.hashmat = hashmat;
    }
    
    
    
}
