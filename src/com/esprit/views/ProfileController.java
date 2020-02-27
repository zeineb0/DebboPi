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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    private TextField newemail;
    @FXML
    private TextField newtel;
   
    
    Connection con = DataSource.getInstance().getConnection();
    
    
    String iddd = Integer.toString(getIdSession());
    
    java.sql.PreparedStatement ps = null;
    ResultSet rs = null;
    @FXML
    private Button chemail;
    @FXML
    private Button chtel;
    
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
    
    @FXML
    private void changeEmail(MouseEvent event) throws SQLException {
        
        String req2 ="UPDATE `utilisateur` SET `email` = '"+newemail.getText()+"' WHERE `utilisateur`.`id_user` = "+iddd+";";
        java.sql.PreparedStatement ps2 = con.prepareStatement(req2);
        ps2.executeUpdate();
    }
    
    @FXML
    private void changeTel(MouseEvent event) throws SQLException {
        
        String req2 ="UPDATE `utilisateur` SET `tel` = '"+newtel.getText()+"' WHERE `utilisateur`.`id_user` = "+iddd+";";
        java.sql.PreparedStatement ps3 = con.prepareStatement(req2);
        ps3.executeUpdate();
    }
    
    @FXML
    private void goToAcceuil(MouseEvent event) throws SQLException {
        String rl="";
        
        String request ="SELECT * from `utilisateur` WHERE `utilisateur`.`id_user` = "+iddd+";";
        java.sql.PreparedStatement ps4 = con.prepareStatement(request);
        ResultSet rs4 = ps4.executeQuery();

        if (rs4.next()) {
            rl = rs4.getString("role");
            System.out.println(rl);
        }
        

                if (rl.equals("Client")) {
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
                if (rl.equals("TransporteurLibre")){
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
              if (rl.equals("TransporteurAssocie")) {
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
              if (rl.equals("Fournisseur")){
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
    
    
}


