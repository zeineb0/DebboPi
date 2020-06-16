/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Session;
import static com.esprit.entities.Session.getIdSession;
import com.esprit.entities.User;
import com.esprit.utilities.DataSource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import static sun.plugin.javascript.navig.JSType.Image;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnLogin;
    @FXML
    private Label forgetpw;
    @FXML
    private Label register;
    @FXML
    private Label check;
    @FXML
    private ImageView im;
    @FXML
    private ImageView LOGO;
   

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    Image ima=new Image("background.jpg");
    im.setImage(ima);
    ima=new Image("logo-debbo.png");
    LOGO.setImage(ima);
        // TODO
    }

     
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    String roleee="";
    int idS;

    private String login() throws SQLException {

        Connection con = DataSource.getInstance().getConnection();

        String usr = email.getText();
        String pw = password.getText();


        
        
         
              
                try {
            
        
        
            String query_url = "http://localhost/DebboWeb/web/app_dev.php/forum/logg";
           String json = "{\n" +
"    \"username\": \""+email.getText()+"\",\n" +
"    \"password\": \""+password.getText()+"\"\n" +
"}";
           URL url = new URL(query_url);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setConnectTimeout(5000);
           conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
           conn.setDoOutput(true);
           conn.setDoInput(true);
           conn.setRequestMethod("POST");
           OutputStream os = conn.getOutputStream();
           os.write(json.getBytes("UTF-8"));
           os.close(); 
           // read the response
           InputStream in = new BufferedInputStream(conn.getInputStream());
           String result = IOUtils.toString(in, "UTF-8");
           System.out.println(result);
           System.out.println("result after Reading JSON Response");
           JSONObject myResponse = new JSONObject(result);

           in.close();
           conn.disconnect();
        
            System.out.println("1");

                               
                   //Query
        String req = "SELECT * FROM `utilisateur` WHERE `username` LIKE '"+email.getText()+"'";

        ps = con.prepareStatement(req);
        
      
        rs = ps.executeQuery();
        
        if (rs.next()){
            
           roleee=rs.getString("role");
                  System.out.println("2");
           idS=rs.getInt("id_user");
           
           Session.setIdSession(idS);
           
           
            System.out.println(getIdSession());
            
            System.out.println(roleee);
            
            check.setTextFill(Color.GREEN);
            check.setText("Logging Succesfull..Redirecting..");
            return "Success";
            
        }
        else{
            
            System.out.println("sar prb  +++++++++");
            check.setTextFill(Color.TOMATO);
            check.setText("Wrong Email/password");
            
            return "Error";
        }

            
        } 
                catch (Exception e) {
                    System.out.println("sar prb ************** +++++++++");
            check.setTextFill(Color.TOMATO);
            check.setText("Wrong Email/password");
            
            return "Error";
        }
 
         
         
        

   /*     if (!rs.next()) {
            check.setTextFill(Color.TOMATO);
            check.setText("Wrong Email/password");
            return "Error";
        } else {
           
           roleee=rs.getString("role");
           idS=rs.getInt("id_user");
           Session.setIdSession(idS);
           
           
            System.out.println(getIdSession());
            check.setTextFill(Color.GREEN);
            check.setText("Logging Succesfull..Redirecting..");
            return "Success";

        }*/

    }
     @FXML
     public void forgetP(MouseEvent event){
         
         try {
               Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ForgetPw.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
         
         
     }

    
    
    @FXML
    private void handleButtonAction(MouseEvent event) throws SQLException {
        if (event.getSource() == btnLogin) {

            //LOGIN HERE
            if (login().equals("Success")) {
                
                
                if (roleee.equals("admin")) {
                
                try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("HomeAdmin.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.print(ex.getMessage());
                }
                }
                if (roleee.equals("Client")) {
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
                if (roleee.equals("TransporteurLibre")){
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
              if (roleee.equals("TransporteurAssocie")) {
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
              if (roleee.equals("Fournisseur")){
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
        if (event.getSource() == register) {
            
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Register.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
        
        

    }

 
    
    

}
