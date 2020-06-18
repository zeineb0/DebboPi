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
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    @FXML
    private TextField prix;
    private Label erreur;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list;
        try {
            list= FXCollections.observableArrayList("Libre","Loué","A louer");
            etat.setItems(list);
            erreur.setVisible(false);
        } catch (Exception ex) {
            System.out.println("com.esprit.views.AjouterEntrepotController.initialize()");
        }
    }    

    @FXML
    private void ajouterEntrepot(ActionEvent event) {
        if(numfisc.getText().equals("")||adresse.getText().equals("")||entrep.getText().equals(""))
        {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout Entrepot");
            alert.setHeaderText(null);
            alert.setContentText("Les champs doivent etre rempli");
            alert.showAndWait();}
        else{
        try{
        String ad = adresse.getText();
        int num_fiscale = Integer.parseInt(numfisc.getText());
        int quantite = Integer.parseInt(quanmax.getText());
        Float pr=Float.parseFloat(prix.getText());
        
        String entreprise = entrep.getText();
        Entrepot e = new Entrepot();
        e.setAdresse_entrepot(ad);
        
        e.setNum_fiscale(num_fiscale);
        e.setQuantite_max(quantite);
        e.setEtat(etat.getValue());
        e.setPrix_location(pr);
        e.setEntreprise(entreprise);
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation d'ajout");
                alert.setContentText("Voulez-vous ajouter cet entrepot ");
                alert.setHeaderText(null);
                Optional<ButtonType> resultat = alert.showAndWait();    
                if(resultat.get()== ButtonType.OK)
                {
                                serviceEntrepot.ajouter(e);
                                adresse.setText("");
                                numfisc.setText("");
                                quanmax.setText("");
                                prix.setText("");
                                entrep.setText("");
                }
                else
                {
                    System.out.println("cancel");
                }
           
            } catch (SQLException ex) {
                System.out.println("com.esprit.views.AjouterEntrepotController.onClick()");
            }
        }
        }

    @FXML
    private void retourVersAffichage(ActionEvent event) throws IOException {
     Parent list = FXMLLoader.load(getClass().getResource("gererEntrepot.fxml"));
        Scene listE= new Scene(list);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(listE);
        window.show();
    }
        
    }
    
