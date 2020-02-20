/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Entrepot;
import com.esprit.services.impl.ServiceEntrepot;
import com.esprit.utilities.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SupprimerEntrepotController implements Initializable {

    @FXML
    private TextField entrep;
    @FXML
    private TextField quanmax;
    @FXML
    private TextField adresse;
    @FXML
    private TextField numfisc;
    @FXML
    private TextField id;
    @FXML
    private TextField etat;
    ServiceEntrepot serviceEntrepot = new ServiceEntrepot();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supprimerEntrepot(ActionEvent event) {
        try{
          
        int iden = Integer.parseInt(id.getText());
       serviceEntrepot.delete(iden);
            
    }
        catch (SQLException ex)
                {}
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
