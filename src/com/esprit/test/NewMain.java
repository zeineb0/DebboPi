/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.entities.Entrepot;
import com.esprit.services.impl.ServiceEntrepot;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         ServiceEntrepot ent = new ServiceEntrepot();
          List<Entrepot> entrepots;
        try {
            entrepots = ent.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println();
        // TODO code application logic here
    }
    
}
