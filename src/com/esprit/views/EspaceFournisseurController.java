/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Entrepot;
import com.esprit.services.impl.ServiceEntrepot;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
 @FXML
    private TableView<Entrepot> table;
    @FXML
    private TableColumn<Entrepot, Integer> Ide;
    @FXML
    private TableColumn<Entrepot, String> adre;
    @FXML
    private TableColumn<Entrepot, Integer> num;
    @FXML
    private TableColumn<Entrepot, Integer> quant;
    @FXML
    private TableColumn<Entrepot, String> etat;
    @FXML
    private TableColumn<Entrepot, String> entrep;
    ObservableList<Entrepot> datalist;
    ServiceEntrepot serviceEntrepot = new ServiceEntrepot();

    //public Observablelist<Entrepot> data= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<Entrepot> entrepots = (ArrayList<Entrepot>) serviceEntrepot.readAll();
                
            datalist = FXCollections.observableArrayList(entrepots);
            Ide.setCellValueFactory(new PropertyValueFactory<>("id_entrepot"));
            adre.setCellValueFactory(new PropertyValueFactory<>("adresse_entrepot"));
            num.setCellValueFactory(new PropertyValueFactory<>("num_fiscale"));
            quant.setCellValueFactory(new PropertyValueFactory<>("quantite_max"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

            entrep.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
            
            table.setItems(datalist);
      
        } catch (SQLException ex) {
             System.out.println("com.esprit.views.AfficherEntrepotController.onClick()");
        }
           
    
            }

    @FXML
    private void onClick(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ajouterEntrepot.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Ajouter entrepot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }  
    @FXML
    private void onClick1(ActionEvent event) throws IOException {
         
        Parent liste = FXMLLoader.load(getClass().getResource("supprimerEntrepot.fxml"));
        Scene ListeE = new Scene(liste);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListeE);
        window.show();
//        Parent root = FXMLLoader.load(getClass().getResource("supprimerEntrepot.fxml"));
//
//        Scene scene = new Scene(root);
//        Stage primaryStage = new Stage();
//        primaryStage.setTitle("Supprimer entrepot");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }   
    @FXML
    private void onClick2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("modifierEntrepot.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Modifier entrepot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onClick3(ActionEvent event) throws IOException {
        Parent liste = FXMLLoader.load(getClass().getResource("afficherEntrepot.fxml"));
        Scene ListeE = new Scene(liste);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListeE);
        window.show();
        
//        Parent root = FXMLLoader.load(getClass().getResource("afficherEntrepot.fxml"));
//
//        Scene scene = new Scene(root);
//        Stage primaryStage = new Stage();
//        primaryStage.setTitle("Liste des entrepots");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    }
    
