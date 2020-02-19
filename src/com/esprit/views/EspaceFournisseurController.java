/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author asus
 */
public class EspaceFournisseurController implements Initializable {

  

    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClick(ActionEvent event) {
        
        
        try {
        Parent root = FXMLLoader.load(getClass().getResource("ajouterEntrepot.fxml"));
       
                 
        } catch (IOException ex) {
            System.out.println("com.esprit.views.EspaceFournisseurController.onClick()");
        }
        
             
    }

   
    
    
    
}
