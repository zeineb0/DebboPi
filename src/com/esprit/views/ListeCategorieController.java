/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Zeineb_yahiaoui
 */
public class ListeCategorieController implements Initializable {

    @FXML
    private TableColumn<Categorie, String> clNom;
    @FXML
    private TextField txtNom;
    @FXML
    private TableView<?> table;
    @FXML
    private Button btnA;
    @FXML
    private Button btnM;
    @FXML
    private Button btnS;
    @FXML
    private Button btnAn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void onClickAjouter(ActionEvent event) {
    }

    @FXML
    private void onClickModif(ActionEvent event) {
    }

    @FXML
    private void onClickSupprimer(ActionEvent event) {
    }

    @FXML
    private void onClickAnnuler(ActionEvent event) {
    }
    
}
