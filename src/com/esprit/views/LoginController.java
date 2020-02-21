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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    private Label signin;
    @FXML
    private Label check;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // TODO
    }

    PreparedStatement ps = null;
    ResultSet rs = null;

    private String login() throws SQLException {

        Connection con = DataSource.getInstance().getConnection();

        String usr = email.getText();
        String pw = password.getText();

        //Query
        String req = "SELECT * FROM utilisateur Where email = ? and password = ?";

        ps = con.prepareStatement(req);
        ps.setString(1, usr);
        ps.setString(2, pw);
        rs = ps.executeQuery();

        if (!rs.next()) {
            check.setTextFill(Color.TOMATO);
            check.setText("Wrong Email/password");
            return "Error";
        } else {
            //showDialog("Logging Successful", null, "Successful");
            check.setTextFill(Color.GREEN);
            check.setText("Logging Succesfull..Redirecting..");
            return "Success";

        }

    }

    /*  private void showDialog(String info, String header, String title) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(info);
        alert.setHeaderText(header);
        alert.showAndWait();

    }*/
    @FXML
    private void handleButtonAction(MouseEvent event) throws SQLException {
        if (event.getSource() == btnLogin) {

            //LOGIN HERE
            if (login().equals("Success")) {
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
        }

    }

}
