/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Livraison;
import com.esprit.services.impl.ServiceLivraison;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class esprit extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         ServiceLivraison ser = new ServiceLivraison();
        Livraison l1 = new Livraison("125464","545","NonLivree",1,2,"aze");
        Livraison l2 = new Livraison("125464","545","Livree",1,2,"aze");
        Livraison l3 = new Livraison("125464","545","annulle",1,2,"aze");
        
                try {
//  
            ser.ajouter(l1);
            ser.ajouter(l2);
            ser.ajouter(l3);
            
            List<Livraison> list = ser.readLivree();
            ser.delete(13);
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
}
