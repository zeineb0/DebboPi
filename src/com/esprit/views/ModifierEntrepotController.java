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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierEntrepotController implements Initializable {

    @FXML
    private TextField entrep;
    @FXML
    private TextField quanmax;
    @FXML
    private TextField adresse;
    @FXML
    private TextField numfisc;
    @FXML
    private TextField etat;
    @FXML
    private TextField id;
    ServiceEntrepot serviceEntrepot = new ServiceEntrepot();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

    @FXML
    private void modifierEntrepot(ActionEvent event) {
        try{
        int iden = Integer.parseInt(id.getText());
        String ad = adresse.getText();
        int num_fiscale = Integer.parseInt(numfisc.getText());
        int quantite = Integer.parseInt(quanmax.getText());
        String et=etat.getText();
        String entreprise = entrep.getText();
        Entrepot e = new Entrepot();
        e.setId_entrepot(iden);
        e.setAdresse_entrepot(ad);
        e.setNum_fiscale(num_fiscale);
        e.setQuantite_max(quantite);
        e.setEtat(et);
        e.setEntreprise(entreprise);
      
           
        int st =serviceEntrepot.update(e);
        if(st>0)
        {Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Entrepot!");
            alert.setHeaderText("Information");
            alert.setContentText("Entrepot bien modifié");
            alert.showAndWait();
                    }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modifier Entrepot!");
            alert.setHeaderText("Information");
            alert.setContentText("Entrepot non modifié");
            alert.showAndWait();
        }
            } catch (SQLException ex) {
                System.out.println("com.esprit.views.AjouterEntrepotController.onClick()");;
            }
            
        }

    @FXML
    private void getEntrepot(ActionEvent event) throws SQLException {
    int iden = Integer.parseInt(id.getText());
    Entrepot e =serviceEntrepot.getEntrepotId(iden);
    adresse.setText(e.getAdresse_entrepot());
    entrep.setText(e.getEntreprise());
    etat.setText(e.getEtat());
    int nu = e.getNum_fiscale();
    String fi =""+nu;
    numfisc.setText(fi);
     int qu = e.getQuantite_max();
    String ma =""+qu;
    quanmax.setText(ma);
    
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
    

