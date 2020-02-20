/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EspaceFournisseurController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClick(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ajouterEntrepot.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Ajouter Article");
        primaryStage.setScene(scene);
        primaryStage.show();
    }  
    @FXML
    private void onClick1(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("supprimerEntrepot.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Ajouter Article");
        primaryStage.setScene(scene);
        primaryStage.show();
    }   
    @FXML
    private void onClick2(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("modifierEntrepot.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Ajouter Article");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    }
    

