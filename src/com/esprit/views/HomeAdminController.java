/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.User;
import com.esprit.utilities.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HomeAdminController implements Initializable {

    @FXML
    private PasswordField oldpw;
    @FXML
    private PasswordField newpw;
    @FXML
    private Button chpw;
    @FXML
    private TextArea userss;
    @FXML
    private Button infos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    

    @FXML
    private void changepww(MouseEvent event) throws SQLException {
        
              Connection con = DataSource.getInstance().getConnection();
        
     String   a = oldpw.getText();
     String   b = newpw.getText();
     
    if (event.getSource() == chpw) {
  
      
        String req2 = "UPDATE utilisateur SET password='"+b+"'  WHERE password='"+a+"'";
        
        PreparedStatement ps2 = null;
         ps2 = con.prepareStatement(req2);
        int executeUpdate = ps2.executeUpdate();
        
        
        if (event.getSource() == infos ) {
         
       String     req3="select * from utilisateur";
            
             PreparedStatement p4;
             ResultSet rs2;
             p4 = con.prepareStatement(req3);
             rs2 = p4.executeQuery();
             
             while(rs2.next()){
            String nom = rs2.getString("id");
            String prenom = rs2.getString("prenom");
                    
        }
             
             
          //    String a= 
            // userss.setText(a);
       
        }
        
        
      
    }
      
    }
    
    
    
    
}
