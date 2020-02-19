/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class TransporteurController implements Initializable {

    @FXML
    private Button liv;
    @FXML
    private Button cnt;
    @FXML
    private Button pr;
    @FXML
    private TableView<?> LivraisonTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnClickLivraison(ActionEvent event) {
    }

    @FXML
    private void OnClickContrat(ActionEvent event) {
    }

    @FXML
    private void OnClickProfile(ActionEvent event) {
    }
    
}
