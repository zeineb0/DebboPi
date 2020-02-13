
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
    public static void main(String[] args) throws SQLException {
//        launch(args);
        ServiceEntrepot ent = new ServiceEntrepot();
        Entrepot e1 = new Entrepot(11,"Tunis", 57, 82,"libre", "delice");
        
        //ent.ajouter(e1);
       
        //ent.delete(8);
        ent.update(e1);
         List<Entrepot> entrepots = ent.readAll();
        System.out.println(entrepots);
        
        
    }
    
}