/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.utilities.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author ASUS X550V
 */
public class PieChartController implements Initializable {

    @FXML
    private PieChart piechart;
    Connection con;
    ResultSet res;
    Integer max=0;
    Integer min=0;
    String villeplus;
    String villemoins;
    @FXML
    private TextArea plus;
    @FXML
    private TextArea moins;

    /**
     * Initializes the controller class.
     */ 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        piechart.setTitle("Disposition des commandes selon les villes");
        piechart.setLabelLineLength(10);
        piechart.setLegendSide(Side.LEFT);
        String req="Select adresse_livraison,count(*) from livraison GROUP BY adresse_livraison";  
         try {
             con = DataSource.getInstance().getConnection();
             res = con.createStatement().executeQuery(req);
             min=1;
            while (res.next()){
                  if (res.getInt(2)>max){
                      villeplus=res.getString(1);
                      max=res.getInt(2);
                  }
                  if (res.getInt(2)<min){
                      villemoins=res.getString(1);
                      min=res.getInt(2);
                  }
                  PieChart.Data slice = new PieChart.Data(res.getString(1),res.getInt(2));
                  final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");
                  piechart.getData().add(slice);  
                  
            }
            
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
         String ch=Integer.toString(max);
         String ch1=villeplus+" avec "+ch+" Commandes";
         String sh=Integer.toString(min);
         String sh2=villemoins+" avec "+sh+" Commandes";
         plus.setText(ch1);
         moins.setText(sh2);
         
         

         

        
        
    }
//    public void CalculPourcentagePieChart(){
//        final Label caption = new Label("");
//        caption.setTextFill(Color.DARKORANGE);
//        caption.setStyle("-fx-font: 24 arial;");
//
//        for (final PieChart.Data data : piechart.getData()) {
//            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
//                new EventHandler<MouseEvent>() {
//      @Override public void handle(MouseEvent e) {
//                caption.setTranslateX(e.getSceneX());
//                caption.setTranslateY(e.getSceneY());
//                caption.setText(String.valueOf(data.getPieValue()) + "%");
//             }
//        });
//}
//    }
    
        }