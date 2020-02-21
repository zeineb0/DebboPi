
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Entrepot;
import com.esprit.services.impl.ServiceEntrepot;
import com.esprit.services.impl.ServiceLocation;
import java.sql.SQLException;
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
  
       Parent root = FXMLLoader.load(getClass().getResource("espaceClient.fxml"));
        
       Scene scene = new Scene(root);
        stage.setTitle("Entrepot");
        stage.setScene(scene);
        stage.show();
        
         try{
//        ServiceEntrepot ent = new ServiceEntrepot();
//        ServiceLocation loc = new ServiceLocation();
//        Entrepot e1 = new Entrepot( "ariana",130, 520,"Libre", "delice", 5);

////        Location l1 = new Location(Date.valueOf("2020-02-02"), 120, 11, 1);
//        loc.ajouter(l1);
//  ent.ajouter(e1);
//            System.out.println(e1);
      //  ent.ajouter(e2);
          //loc.delete(5);
        //ent.delete(8);
//        ent.update(e1);
//        List<Entrepot> entrepots = ent.readAll();
//        System.out.println(entrepots);
//        List<Location> locations = loc.readAll();
//            List<Location> locations3 = loc.trierLocationParDateCroissant();

 //       System.out.println(locations3);
}
catch(Exception ex)
{System.out.println("erreur");
}
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
    launch(args);

    }

}