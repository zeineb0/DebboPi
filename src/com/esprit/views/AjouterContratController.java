/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Contrat;
import com.esprit.entities.ContratDetail;
import com.esprit.entities.Entrepot;
import com.esprit.entities.Utilisateur;
import com.esprit.services.impl.ContratService;
import com.esprit.utilities.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AjouterContratController implements Initializable {

    @FXML
    private Button addC;
    @FXML
    private Button gestC;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField entreprise;
    @FXML
    private DatePicker dateDeb;
    @FXML
    private DatePicker dateFin;
    
    private ContratService contrat_service;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickAdd(ActionEvent event) throws SQLException {
        
        Date date1 = Date.valueOf(dateDeb.getValue());
        Date date2 = Date.valueOf(dateFin.getValue());
        
        ContratDetail contrat_detail = new ContratDetail();
        contrat_detail.setDate_debut(date1);
        contrat_detail.setDate_fin(date2);
        contrat_detail.setNom(nom.getText());
        contrat_detail.setPrenom(prenom.getText());
        contrat_detail.setEntreprise(entreprise.getText());
        
        System.out.println(contrat_detail);
        
        String req = "select * from utilisateur where nom = ? and prenom = ?";
        
        String name=nom.getText();
        String last_name = prenom.getText();
        
        PreparedStatement ps= DataSource.getInstance().getConnection().prepareStatement(req);   
        
        ps.setString(1, name);
        ps.setString(2, last_name);
        
        ResultSet rs=ps.executeQuery();

        
        
        int id = rs.getInt("id_user");
         
        
        String req2 = "select * from entrepot where entreprise=?";
        
        String entreprise_name=entreprise.getText();
        PreparedStatement ps2= DataSource.getInstance().getConnection().prepareStatement(req2); 
        ps.setString(1, entreprise_name);
        ResultSet rs2=ps2.executeQuery();
        
        int id2 = rs2.getInt("id_entrepot");
        
        Contrat contrat = new Contrat(date1, date2, id, id2);
        Utilisateur user = new Utilisateur();
        user.setId(id);
        Entrepot entrepot = new Entrepot();
        entrepot.setId_entrepot(id2);
        contrat_service.ajouterContrat(contrat, user, entrepot);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
         
        
        
        
        
    }

    @FXML
    private void onClickGestion(ActionEvent event) throws IOException {
        
        Parent gestion_contrat = FXMLLoader.load(getClass().getResource("GestionContrat.fxml"));
        Scene gestionCV= new Scene(gestion_contrat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gestionCV);
        window.show();
        
    }

    
}
