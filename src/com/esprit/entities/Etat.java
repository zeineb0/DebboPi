/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author asus
 */
public enum  Etat {
    
    à_louer,loué,libre;

    public Etat getEtat(int x) {
        switch (x) {
            case 1:
                return à_louer;
            case 2:
                return loué;
            default:
                return libre;
        }
    }

    public static Etat getÀ_louer() {
        return à_louer;
    }

    public static Etat getLoué() {
        return loué;
    }

    public static Etat getLibre() {
        return libre;
    }
    
    
}
