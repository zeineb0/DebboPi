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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Zeineb_yahiaoui
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField txt;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private TextField txt3;
    @FXML
    private TextField txt4;
    @FXML
    private Button btn;
    @FXML
    private Button btn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickValider(ActionEvent event) {
        String nom = txt.getText();
        Double prix = Double.parseDouble(txt1.getText());
        Double qte = Double.parseDouble(txt2.getText());
        Double reserve = Double.parseDouble(txt3.getText());
        Boolean promo = Boolean.getBoolean(txt4.getText());
        
        
        
        
    }

    @FXML
    private void onClickAnnuler(ActionEvent event) {
        
        
        
        
    }
    
}
