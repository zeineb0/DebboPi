/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.sql.Date;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class MouvementStock {
    private int id;
    private String natureDuStock;
    private Produit p;
    private Categorie c;
    private Date dateMouv;

    public MouvementStock(int id, String natureDuStock, Produit p, Categorie c, Date dateMouv) {
        this.id = id;
        this.natureDuStock = natureDuStock;
        this.p = p;
        this.c = c;
        this.dateMouv = dateMouv;
    }

    public MouvementStock(String natureDuStock, Produit p, Categorie c, Date dateMouv) {
        this.natureDuStock = natureDuStock;
        this.p = p;
        this.c = c;
        this.dateMouv = dateMouv;
    }

    public int getId() {
        return id;
    }

    public String getNatureDuStock() {
        return natureDuStock;
    }

    public Produit getP() {
        return p;
    }

    public Categorie getC() {
        return c;
    }

    public Date getDateMouv() {
        return dateMouv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNatureDuStock(String natureDuStock) {
        this.natureDuStock = natureDuStock;
    }

    public void setP(Produit p) {
        this.p = p;
    }

    public void setC(Categorie c) {
        this.c = c;
    }

    public void setDateMouv(Date dateMouv) {
        this.dateMouv = dateMouv;
    }

    @Override
    public String toString() {
        return "MouvementStock{" + "id=" + id + ", natureDuStock=" + natureDuStock + ", p=" + p + ", c=" + c + ", dateMouv=" + dateMouv + '}';
    }
    
    
    
    

    
    
    
    
}
