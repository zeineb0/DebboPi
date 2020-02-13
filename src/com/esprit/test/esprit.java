/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.entities.Utilisateur;
import com.esprit.services.impl.UtilisateurImpl;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class esprit /*extends Application*/ {
    
//    @Override
//    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    /**
//     * @param args the command line arguments
//     */
    public static void main(String[] args) {
//        launch(args);
        
        UtilisateurImpl serviceUser = new UtilisateurImpl();
        
        Utilisateur user1 = new Utilisateur(1, "07478897", "akacha", "farouk", "transporteur");
        
        try {
            serviceUser.ajouter(user1);
        } catch (SQLException ex) {
            Logger.getLogger(esprit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
