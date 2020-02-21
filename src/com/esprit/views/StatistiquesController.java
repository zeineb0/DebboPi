/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.utilities.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS X550V
 */
public class StatistiquesController implements Initializable {
    
    ObservableList<String> typechList = FXCollections.observableArrayList("BarChart","LineChart");
    ObservableList<String> axexList = FXCollections.observableArrayList("date_mouv","Année");
    ObservableList<String> axeyList = FXCollections.observableArrayList("NombreProd","axeY2");

    @FXML
    private ChoiceBox<String> axex;
    @FXML
    private ChoiceBox<String> axey;
    @FXML
    private Button btn;
    @FXML
    private ChoiceBox<String> typech;
    @FXML
    private BarChart<String,Integer> barchart;
    @FXML
    private LineChart<String,Integer> linechart;
    @FXML
    private RadioButton radioentree;
    @FXML
    private RadioButton radiosortie;
    @FXML 
    private BubbleChart<Integer,Integer> bubblechart;
    
    final ToggleGroup group = new ToggleGroup();
    Image img = new Image("/tick.png");



    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioentree.setToggleGroup(group);
        radiosortie.setToggleGroup(group);
        typech.setValue("BarChart");
        typech.setItems(typechList);
        axex.setValue("date_mouv");
        axex.setItems(axexList);
        axey.setValue("NombreProd");
        axey.setItems(axeyList);
              
    }   
    
    public void displayChart(ActionEvent event){
        RadioButton rb=(RadioButton) group.getSelectedToggle();
        String nature=rb.getText();
        String typechValue =typech.getValue();
        String axexValue =axex.getValue();
        String axeyValue =axey.getValue();
//        if (rb == null){
//            
//                Notifications NotificationBuilder = Notifications.create().title("Service Statistiques").text("Selectionner la nature du mouvement").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.TOP_LEFT);
//                NotificationBuilder.darkStyle();
//                NotificationBuilder.showConfirm();           
//        }
        
        if (typechValue.equals("BarChart"))
        {
            if (linechart.isVisible())
            {
                linechart.setVisible(false);
            }
//            if (bubblechart.isVisible())
//            {
//                bubblechart.setVisible(false);
//            }
            
            String req ="select "+axexValue+","+axeyValue+" from mouvement_du_stock where nature_mouvement='"+nature+"' order by "+axexValue;
            XYChart.Series<String,Integer> series = new XYChart.Series<>();
            series.setName("BarChart qui évolue le "+axeyValue+" "+nature+" par "+axexValue);
            
            try {
            Connection con = DataSource.getInstance().getConnection();
            ResultSet res = con.createStatement().executeQuery(req);
            while (res.next()){
                series.getData().add(new XYChart.Data<>(res.getString(1),res.getInt(2)));
            }
                barchart.getData().add(series);
                barchart.setVisible(true);
                Notifications NotificationBuilder = Notifications.create().title("Service Statistiques").text("BarChart crée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.TOP_LEFT);
                NotificationBuilder.darkStyle();
                NotificationBuilder.showConfirm();
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            }
        else if (typechValue.equals("LineChart"))
         {
            if (barchart.isVisible())
            {
                barchart.setVisible(false);
            }
//            if (bubblechart.isVisible())
//            {
//                bubblechart.setVisible(false);
//            }
              String req2 ="select "+axexValue+","+axeyValue+" from mouvement_du_stock where nature_mouvement='"+nature+"' order by "+axexValue;
              NumberAxis xAxis = new NumberAxis(0,1000000,5000);
              xAxis.setLabel(axexValue);
              NumberAxis yAxis = new NumberAxis(0,300,3);
              yAxis.setLabel(axeyValue);
             LineChart markerchart=new LineChart(xAxis,yAxis);
             XYChart.Series series2 = new XYChart.Series<>();
            try {
            Connection con = DataSource.getInstance().getConnection();
            ResultSet res = con.createStatement().executeQuery(req2);
            while (res.next()){
                series2.getData().add(new XYChart.Data<>(res.getString(1),res.getInt(2)));
            }
                linechart.getData().add(series2);
                linechart.setVisible(true);
                Notifications NotificationBuilder = Notifications.create().title("Service Statistiques").text("LineChart crée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.TOP_LEFT);
                NotificationBuilder.darkStyle();
                NotificationBuilder.showConfirm();
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }  
            
            
        }
//        else {
//            if (linechart.isVisible())
//            {
//                linechart.setVisible(false);
//            }
//            if (barchart.isVisible())
//            {
//                barchart.setVisible(false);
//            }
//            Connection con = DataSource.getInstance().getConnection();
//            
//            
//
//            
//            
//            
//            
//            
//        }


        
        
        
        
        
        
        
        
    
    }
    
    
    
}
