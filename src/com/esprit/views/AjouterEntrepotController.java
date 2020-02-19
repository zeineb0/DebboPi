/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Entrepot;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import com.esprit.services.impl.ServiceEntrepot;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterEntrepotController implements Initializable {

    private TextField txtadd;
    private TextField txtnum;
    private TextField txtquan;
    private ComboBox<String> cbN;
    private TextField txten ;
    ServiceEntrepot serviceEntrepot=new ServiceEntrepot();
    private ObservableList <String> list=FXCollections.observableArrayList("libre","loué","à louer");


    private boolean verif(){
        if (cbN.getValue() != null) {
            return true;
        }
        return false;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

        cbN.setItems(list);
               
    }  
    @FXML
    private void onClick(ActionEvent event) throws SQLException {
try{
        String adresse = txtadd.getText();
        int num_fiscale = Integer.valueOf(txtnum.getText());
        int quantite = Integer.valueOf(txtquan.getText());
        String entreprise = txten.getText();
        Entrepot e =new Entrepot();
        e.setAdresse_entrepot(adresse);
        e.setNum_fiscale(num_fiscale);
        e.setQuantite_max(quantite);
        e.setEntreprise(entreprise);
        String c=cbN.getValue();
        e.setEtat(c);
      
        serviceEntrepot.ajouter(e);
            
}
catch(Exception ex)
{System.out.println("com.esprit.views.AjouterEntrepotController.onClick()");}
    }
}
