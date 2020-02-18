
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Entrepot;
import com.esprit.entities.Etat;
import com.esprit.entities.Location;
import com.esprit.services.impl.ServiceEntrepot;
import com.esprit.services.impl.ServiceLocation;
import java.sql.Date;
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
        try{
        ServiceEntrepot ent = new ServiceEntrepot();
        ServiceLocation loc = new ServiceLocation();
        

////        Location l1 = new Location(Date.valueOf("2020-02-02"), 120, 11, 1);
//        loc.ajouter(l1);
        //ent.ajouter(e1);
      //  ent.ajouter(e2);
          //loc.delete(5);
        //ent.delete(8);
//        ent.update(e1);
        List<Entrepot> entrepots = ent.readAll();
        System.out.println(entrepots);
//        List<Location> locations = loc.readAll();
//            List<Location> locations3 = loc.trierLocationParDateCroissant();

 //       System.out.println(locations3);
}
catch(Exception ex)
{System.out.println("com.esprit.test.esprit.main()");
}
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
    launch(args);

    }
    
}