/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.sql.Date;

/**
 *
 * @author Dell
 */
public class Utilisateur {
    
    private int id;
    private String nom,prenom;
    private long cin;
    private Date date;
    private String role;
    private long telephone;
    private String email;
    private String password;
    private int disponibilite;
    private int nombre_max_commande;

    public Utilisateur() {
    }
    
    
    

    public Utilisateur(int id, String nom, String prenom, long cin, Date date, String role, long telephone, String email, String password, int disponibilite, int nombre_max_commande) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.date = date;
        this.role = role;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.disponibilite = disponibilite;
        this.nombre_max_commande = nombre_max_commande;
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

    public long getCin() {
        return cin;
    }

    public void setCin(long cin) {
        this.cin = cin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
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

    public int getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    public int getNombre_max_commande() {
        return nombre_max_commande;
    }

    public void setNombre_max_commande(int nombre_max_commande) {
        this.nombre_max_commande = nombre_max_commande;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", date=" + date + ", role=" + role + ", telephone=" + telephone + ", email=" + email + ", password=" + password + ", disponibilite=" + disponibilite + ", nombre_max_commande=" + nombre_max_commande + '}';
    }
    

    
    
    
}
