/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Location;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ListeDesLocationController implements Initializable {

    @FXML
    private TableView<Location> table;
    @FXML
    private TableColumn<Location, Integer> Ide;
    @FXML
    private TableColumn<Location, Date> dateDeb;
    @FXML
    private TableColumn<Location, Date> dateFin;
    @FXML
    private TableColumn<Location,Double> prix;
    @FXML
    private TableColumn<Location, Integer> quan;
    @FXML
    private TableColumn<Location, String> adr;
    @FXML
    private TextField prix1;
    @FXML
    private TextField idEnt;
    @FXML
    private Label erreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifierLocation(ActionEvent event) {
    }

    @FXML
    private void supprimerLocation(ActionEvent event) {
    }
    
}
