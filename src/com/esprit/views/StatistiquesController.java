/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.utilities.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author ASUS X550V
 */
public class StatistiquesController implements Initializable {
    
    ObservableList<String> typechList = FXCollections.observableArrayList("BarChart","LineChart");
    ObservableList<String> axexList = FXCollections.observableArrayList("Jour","Année");
    ObservableList<String> axeyList = FXCollections.observableArrayList("Produits","CommandeLivree","CommandeAnnulée");

    
    Connection con;
    ResultSet res;
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
    final ToggleGroup group = new ToggleGroup();
    Image img = new Image("/tick.png");
    String arg1,arg2;
    

    XYChart.Series<String,Integer> series;
    XYChart.Series series2;
    @FXML
    private ImageView clear;
    @FXML
    private Label titre;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioentree.setToggleGroup(group);
        radiosortie.setToggleGroup(group);
        typech.setValue("BarChart");
        typech.setItems(typechList);
        axex.setValue("Jour");
        axex.setItems(axexList);
        axey.setValue("Produits");
        axey.setItems(axeyList);
        Image image = new Image("/clear.png");
        clear.setImage(image);
              
    }   
    
    @FXML
    public void displayChart(ActionEvent event){
        RadioButton rb=(RadioButton) group.getSelectedToggle();
        String nature=rb.getText();
        String typechValue =typech.getValue();
        String axexValue =axex.getValue();
        String axeyValue =axey.getValue();
        if (axexValue=="Jour"){
             arg1="date_mouv";
        }
        if (axexValue=="Année"){
             arg1="Année";
        }
        if (axeyValue=="Produits"){
             arg2="NombreProd";
        }
        if (axeyValue=="CommandeLivree"){
             arg2="Livree";
        }
        
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
   // Changing random data after every 1 second.

            String req ="select "+arg1+","+arg2+" from mouvement_du_stock where nature_mouvement='"+nature+"' order by "+arg1;
             series = new XYChart.Series<>();
            series.setName("BarChart qui évolue les "+axeyValue+" "+nature+" par "+axexValue);
            
            try {
             con = DataSource.getInstance().getConnection();
             res = con.createStatement().executeQuery(req);
            while (res.next()){
                  series.getData().add(new XYChart.Data<>(res.getString(1),res.getInt(2)));
//                Notifications NotificationBuilder = Notifications.create().title("Service Statistiques").text("BarChart crée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.TOP_LEFT);
//                NotificationBuilder.darkStyle();
//                NotificationBuilder.showConfirm();            
            }
                             
            barchart.getData().add(series);
            barchart.setVisible(true);
           
                
//                barchart.getData().add(series);
//                barchart.setVisible(true);
//                Notifications NotificationBuilder = Notifications.create().title("Service Statistiques").text("BarChart crée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.TOP_LEFT);
//                NotificationBuilder.darkStyle();
//                NotificationBuilder.showConfirm();
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
//             Timeline Updater = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {  
//       @Override  
//       public void handle(ActionEvent event) {  
//             series.getData().clear();  
//            String req ="select "+arg1+","+arg2+" from mouvement_du_stock where nature_mouvement='"+nature+"' order by "+arg1;
//           try {
//               while (res.next()){
//                   series.getData().add(new XYChart.Data<String,Integer>(res.getString(1),res.getInt(2)));
//               }
//           } catch (SQLException ex) {
//           }
//           
//       }
//   
//             }));
//            Updater.setCycleCount(Timeline.INDEFINITE);  
//            Updater.play();  
//            barchart.getData().add(series);  
            
             }
        
                     
      
        



        else if (typechValue.equals("LineChart"))
         {
            if (barchart.isVisible())
            {
                barchart.setVisible(false);
            }
              String req2 ="select "+arg1+","+arg2+" from mouvement_du_stock where nature_mouvement='"+nature+"' order by "+arg1;
              NumberAxis xAxis = new NumberAxis(0,1000000,5000);
              xAxis.setLabel(axexValue);
              NumberAxis yAxis = new NumberAxis(0,300,3);
              yAxis.setLabel(axeyValue);
             LineChart markerchart=new LineChart(xAxis,yAxis);
             series2 = new XYChart.Series<>();
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

    private void clearall(MouseEvent event) {
        series.getData().clear();
        series2.getData().clear();
    }

    @FXML
    private void clearall(KeyEvent event) {
    }
    
    

}
