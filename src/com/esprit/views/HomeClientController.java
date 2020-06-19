/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import static com.esprit.entities.Session.getIdSession;
import com.esprit.utilities.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HomeClientController implements Initializable {

    @FXML
    private AnchorPane anchorLoad;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
            Parent pane= FXMLLoader.load(getClass().getResource("Poduits.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            // TODO

            setLabelProfileClient();
        } catch (SQLException ex) {
            Logger.getLogger(HomeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
     
     Connection con = DataSource.getInstance().getConnection();
    String iddd = Integer.toString(getIdSession());
    
    @FXML
    private Label labelprofile ;
    
    public void setLabelProfileClient() throws SQLException{
         String fullName="";
       
        String request0 ="SELECT * from `utilisateur` WHERE `utilisateur`.`id_user` = "+iddd+";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();

        if (rs0.next()) {
            String a = rs0.getString("nom");
            String b = rs0.getString("prenom");
            fullName = b+" "+a;
        }
        System.out.println(iddd);
                     labelprofile.setText(fullName); 
    }
    
    
        @FXML
     void logOut(ActionEvent event){
       
            
            try {
               Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    
    }
 

     @FXML
    void goToProfile(ActionEvent event) {
 try {
            Parent pane= FXMLLoader.load(getClass().getResource("Profile.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            // TODO

            setLabelProfileClient();
        } catch (SQLException ex) {
            Logger.getLogger(HomeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void goToProduits(ActionEvent event) {

 try {
            Parent pane= FXMLLoader.load(getClass().getResource("Poduits.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            // TODO

            setLabelProfileClient();
        } catch (SQLException ex) {
            Logger.getLogger(HomeClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToPanier(ActionEvent event) {
          try {
            Parent pane= FXMLLoader.load(getClass().getResource("Panier.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToProfile(MouseEvent event) {
    }
}
