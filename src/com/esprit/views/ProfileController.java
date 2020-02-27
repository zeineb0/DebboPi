/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import static com.esprit.entities.Session.getIdSession;
import com.esprit.utilities.DataSource;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProfileController implements Initializable {

    @FXML
    private PasswordField oldpw;
    @FXML
    private PasswordField newpw;
    @FXML
    private Button chpw;
    @FXML
    private TextField newidn;
    @FXML
    private TextField newtel;
   
    
    Connection con = DataSource.getInstance().getConnection();
    
    
    String iddd = Integer.toString(getIdSession());
    
    java.sql.PreparedStatement ps = null;
    ResultSet rs = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void changepww(MouseEvent event) throws SQLException {
        
        String req ="UPDATE `utilisateur` SET `password` = '"+DigestUtils.shaHex(newpw.getText())+"' WHERE `utilisateur`.`id_user` = "+iddd+";";
        
        //UPDATE `utilisateur` SET `password` = 'bvbv' WHERE `utilisateur`.`id_user` = 3; 
        
      ps = con.prepareStatement(req);
      ps.executeUpdate();
    }
    
}
