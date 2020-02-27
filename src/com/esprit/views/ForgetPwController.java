/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.services.impl.ServiceMail;
import com.esprit.utilities.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.util.concurrent.ThreadLocalRandom;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ForgetPwController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    Connection con = DataSource.getInstance().getConnection();
    @FXML
    private TextField reEmail;
    
    
    @FXML
    public void sendPw(MouseEvent event) throws SQLException, Exception{
        
        
        
        
     String randomNum = Integer.toString(ThreadLocalRandom.current().nextInt(100, 10000));
        
        String req ="UPDATE `utilisateur` SET `password`='"+DigestUtils.shaHex(randomNum)+"' WHERE `email`='"+reEmail.getText()+"'";
         PreparedStatement ps17 = con.prepareStatement(req);
      ps17.executeUpdate();
        
     ServiceMail.SendMail(reEmail.getText(), randomNum);
        
        
    }
    
    @FXML
    public void backToLogin(MouseEvent event) {
        
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
