/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;


import com.esprit.utilities.DataSource;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField date;
    @FXML
    private TextField role;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnRegister;
    @FXML
    private Label login;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    PreparedStatement ps = null;
   
    
    public void register() throws SQLException{
 
        Connection con = DataSource.getInstance().getConnection();
        
        String req ="INSERT INTO `utilisateur` (`id_user`, `nom`, `prenom`, `cin`, `date`, `role`, `tel`, `longitude_user`, `altitude_user`, `email`, `password`, `disponniblite`, `nbr_maxComm`, `FK_id_produit`) VALUES (NULL, '"+prenom.getText()+"', '"+nom.getText()+"', '"+cin.getText()+"', '"+date.getText()+"', '"+role.getText()+"', '"+tel.getText()+"', NULL, NULL, '"+email.getText()+"', '"+password.getText()+"', '', NULL, NULL); ";
        ps = con.prepareStatement(req);
        int executeUpdate = ps.executeUpdate();
    }
    
    
    
    
    
    /**
     *
     * @param event
     */
    public void goToLogin(MouseEvent event){
       
            
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
