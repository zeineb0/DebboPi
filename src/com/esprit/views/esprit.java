/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Categorie;
import com.esprit.entities.Produit;
import com.esprit.services.ICategorieService;
import com.esprit.services.impl.CategorieController;
import com.esprit.services.impl.ProduitController;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

        Categorie c1=new Categorie();
        c1.setNom("informatique");
        CategorieController categorieController=new CategorieController();
//        categorieController.ajouterCategorie(c1);
        c1.setId(2);
        ProduitController produitController=new ProduitController();
//
//        Produit p1 = new Produit("lait", 1.2, 20,10 , false,null);
//        Produit p2 = new Produit("yaghort", 1.4, 20,10 , false,null);
//        Produit p3 = new Produit("yt", 1.4, 20,10 , false,null);
Produit p1=new Produit();
p1.setNom("ordinateur");
p1.setPrix(2000);
p1.setPromotion(false);
p1.setQuantite(100);
p1.setReserve(4);
p1.setCategorie(c1);

//        produitController.ajouterProduit(p1);

p1.setId(20);
Produit p=produitController.consulterProduit(p1);
        System.out.println(p);

        
        
        
  
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
               
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
       

    }
    
}
