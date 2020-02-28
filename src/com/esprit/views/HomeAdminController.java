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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HomeAdminController implements Initializable {

    @FXML
    private Label labelprofile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            setLabelProfileAdmin();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    Connection con = DataSource.getInstance().getConnection();
    String iddd = Integer.toString(getIdSession());
    
    public void setLabelProfileAdmin() throws SQLException{
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
    private void LogOut(MouseEvent event) {
        
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
    
}
