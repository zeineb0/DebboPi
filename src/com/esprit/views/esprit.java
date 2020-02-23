/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Categorie;
import com.esprit.entities.Entrepot;
import com.esprit.entities.Produit;
import com.esprit.services.ICategorieService;
import com.esprit.services.impl.CategorieController;
import com.esprit.services.impl.EntrepotController;
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
//        // instance catégorie
//        Categorie c1=new Categorie();
//        c1.setNom("informatique1");
//        CategorieController categorieController=new CategorieController();
//        //ajout 
////        categorieController.ajouterCategorie(c1);
//        
////        c1.setNom("infoooooooo");
////        c1.setId(4);
////        categorieController.modiferCategorie(c1);
////                categorieController.consulterCategorie("infoooooooo");
////                categorieController.supprimerCategorie("informatique1");
//                    List<Categorie> liste = categorieController.listCategorie();
//        //c1.setId(2);
//        //instance produit
//        
        ProduitController produitController=new ProduitController();
           produitController.listeProduit();
//           EntrepotController entrepotController=new EntrepotController();
//           List<Entrepot> entrepots = entrepotController.readAll();
//           System.out.println(entrepots);
//       // c1.setId(1);
//       Produit p1 = new Produit("lait", 1.2, 20,10 , false,c1);
//        produitController.consulterProduit("noooo");
//               produitController.supprimerProduit("lait");
////        Produit p2 = new Produit("yaghort", 1.4, 20,10 , false,null);
////        Produit p3 = new Produit("yt", 1.4, 20,10 , false,null);
//        produitController.ajouterProduit(p1);
//        p1.setId(2);
//        produitController.supprimerProduit(p1);
        
//        Produit p3=new Produit();
//        p1.setNom("ordinateur");
//        p1.setPrix(2000);
//        p1.setPromotion(false);
//        p1.setQuantite(100);
//        p1.setReserve(4);
//        p1.setCategorie(c1);
//        // modifier
//        produitController.ajouterProduit(p3);
//        p1.setId(3);
//        p1.setNom("noooo");
//      produitController.modiferProduit(p1);
//////        produitController.ajouterProduit(p1);
//
//        //consulter
//        p1.setId(3);
//        Produit p=produitController.consulterProduit(p1);
//        System.out.println(p);
////        affichage de la liste
//System.out.println("la liste des produits");
//        List<Produit> list = produitController.listeProduit();
//        System.out.println(list.toString());
////      afficher la liste des produits pour une catégorie
//        System.out.println("la liste des produits pour une catégorie");
////       p1.setCategorie(c1);
//       c1.setId(1);
//       
//        List<Produit> list1 = produitController.listeProduitPourUneCategorie(c1);
//        System.out.println(list1.toString());
        
        
//  EntrepotController entrepotController = new EntrepotController();
//  List<Entrepot> entrepots = entrepotController.readAll();
//        System.out.println(entrepots);
        
        Parent root = FXMLLoader.load(getClass().getResource("ListeProduit.fxml"));
        
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
