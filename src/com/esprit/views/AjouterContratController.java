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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AjouterContratController implements Initializable {

    @FXML
    private Button addC;
    @FXML
    private Button gestC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickAdd(ActionEvent event) {
        
        
    }

    @FXML
    private void onClickGestion(ActionEvent event) throws IOException {
        
        Parent gestion_contrat = FXMLLoader.load(getClass().getResource("GestionContrat.fxml"));
        Scene gestionCV= new Scene(gestion_contrat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gestionCV);
        window.show();
        
    }

    
}
