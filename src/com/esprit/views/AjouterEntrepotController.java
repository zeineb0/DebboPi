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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterEntrepotController implements Initializable {

    @FXML
    private TextField entrep;
    @FXML
    private TextField quanmax;
    @FXML
    private TextField adresse;
    @FXML
    private TextField numfisc;
    @FXML
    private ComboBox<String> etat;
ServiceEntrepot serviceEntrepot = new ServiceEntrepot();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list;
        try {
            list= FXCollections.observableArrayList("libre","loué","à louer");
            etat.setItems(list);
        } catch (Exception ex) {
            System.out.println("com.esprit.views.AjouterEntrepotController.initialize()");
        }
    }    

    @FXML
    private void ajouterEntrepot(ActionEvent event) {
        try{
        String ad = adresse.getText();
        int num_fiscale = Integer.parseInt(numfisc.getText());
        int quantite = Integer.parseInt(quanmax.getText());
        String entreprise = entrep.getText();
        Entrepot e = new Entrepot();
        e.setAdresse_entrepot(ad);
        e.setNum_fiscale(num_fiscale);
        e.setQuantite_max(quantite);
        e.setEtat(etat.getValue());
        e.setEntreprise(entreprise);
      
           
        serviceEntrepot.ajouter(e);
            } catch (SQLException ex) {
                System.out.println("com.esprit.views.AjouterEntrepotController.onClick()");;
            }
            
        }

        @FXML
    private void onClick(ActionEvent event) throws IOException {
         Parent liste = FXMLLoader.load(getClass().getResource("ajouterEntrepot.fxml"));

           Scene ListeE = new Scene(liste);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListeE);
        window.show();
    }  
    @FXML
    private void onClick1(ActionEvent event) throws IOException {
         
        Parent liste = FXMLLoader.load(getClass().getResource("supprimerEntrepot.fxml"));
           Scene ListeE = new Scene(liste);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListeE);
        window.show();
        
    }   
    @FXML
    private void onClick2(ActionEvent event) throws IOException {
        Parent liste = FXMLLoader.load(getClass().getResource("modifierEntrepot.fxml"));
        Scene ListeE = new Scene(liste);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListeE);
        window.show();
    }

    @FXML
    private void onClick3(ActionEvent event) throws IOException {
        Parent liste = FXMLLoader.load(getClass().getResource("espaceFournisseur.fxml"));
        Scene ListeE = new Scene(liste);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListeE);
        window.show();
    }
        
    }
    
