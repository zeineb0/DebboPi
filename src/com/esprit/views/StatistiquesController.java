/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author ASUS X550V
 */
public class StatistiquesController implements Initializable {
    
    ObservableList<String> typechList = FXCollections.observableArrayList("BarChart","LineChart");
    ObservableList<String> axexList = FXCollections.observableArrayList("axeX1","axeX2");
    ObservableList<String> axeyList = FXCollections.observableArrayList("axeY1","axeY2");

    @FXML
    private ChoiceBox<String> axex;
    @FXML
    private ChoiceBox<String> axey;
    @FXML
    private Button btn;
    @FXML
    private ChoiceBox<String> typech;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typech.setValue("BarChart");
        typech.setItems(typechList);
        axex.setValue("axeX1");
        axex.setItems(axexList);
        axey.setValue("axeY1");
        axey.setItems(axeyList);
              
    }   
    
    public void displayChart(ActionEvent event){
        String typechValue =typech.getValue();
        String axexValue =axex.getValue();
        String axeyValue =axey.getValue();
        if (typechValue.equals("BarChart"))
        {
            
        }

        
    }
    
}
