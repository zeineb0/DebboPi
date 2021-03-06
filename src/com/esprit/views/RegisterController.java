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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

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
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnRegister;
    @FXML
    private Label login;
    @FXML
    private RadioButton radioF;
    @FXML
    private ToggleGroup roless;
    @FXML
    private RadioButton radioC;
    @FXML
    private RadioButton radiotl;
    @FXML
    private RadioButton radiota;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    String leRole="";
    String role="";
    
    PreparedStatement ps = null;
   
    
    
    
    
    
    @FXML
    public void register(MouseEvent event) throws SQLException{
 
        Connection con = DataSource.getInstance().getConnection();
        
        
        if(radioF.isSelected()){
            leRole="Fournisseur";
            role="ROLE_PROPRIETAIRE";
        }
        if(radioC.isSelected()){
            leRole="Client";
            role="ROLE_CLIENT";
        }
        if(radiota.isSelected()){
            leRole="TransporteurAssocie";
            role="ROLE_TRANSPORTEUR";
        }
        if(radiotl.isSelected()){
            leRole="TransporteurLibre";
            role="ROLE_TRANSPORTEUR";
        }
        
        String req ="INSERT INTO `utilisateur` (`id_user`, `nom`, `prenom`,`username`,`username_canonical`, `cin`, `date`, `role`, `tel`, `longitude_user`, `altitude_user`, `email`,`email_canonical`, `password`, `disponniblite`, `nbr_maxComm`,`enabled`,roles) VALUES (NULL, '"+prenom.getText()+"', '"+nom.getText()+"','ghazihc','ghazihc', '"+cin.getText()+"', '"+date.getText()+"', '"+leRole+"', '"+tel.getText()+"', NULL, NULL, '"+email.getText()+"','"+email.getText()+"', '"+DigestUtils.shaHex(password.getText())+"', '', NULL,1,'a:1:{i:0;s:11:\""+role+"\";}'); ";
        ps = con.prepareStatement(req);
        ps.executeUpdate();
        
        
        
        
        
        
                 if (leRole.equals("admin")) {
                
                try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("HomeAdmin.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.print(ex.getMessage());
                } }
                if (leRole.equals("Client")) {
                    try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("HomeClient.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.print(ex.getMessage());
                }
                }
                if (leRole.equals("TransporteurLibre")){
                             try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("HomeTransporteurLibre.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.print(ex.getMessage());
                }
                    
                }
              if (leRole.equals("TransporteurAssocie")) {
                                               try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("HomeTransporteurAssocie.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.print(ex.getMessage());
                }
              }
              if (leRole.equals("Fournisseur")){
                                                                 try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("HomeFournisseur.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.print(ex.getMessage());
                }
              }
        
        
         }
    
    
    
    /**
     *
     * @param event
     */
    @FXML
     void goToLogin(MouseEvent event){
       
            
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
