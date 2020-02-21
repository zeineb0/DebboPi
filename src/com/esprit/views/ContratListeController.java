/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.ContratDetail;
import com.esprit.entities.Livraison;
import com.esprit.services.impl.ContratService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ContratListeController implements Initializable {

    @FXML
    private Button cnt;
    @FXML
    private Button pr;
    @FXML
    private Button liv;
    @FXML
    private TableColumn<ContratDetail, Date> date_debut;
    @FXML
    private TableColumn<ContratDetail, Date> date_fin;
    @FXML
    private TableColumn<ContratDetail, String> nom;
    @FXML
    private TableColumn<ContratDetail, String> prenom;
    @FXML
    private TableColumn<ContratDetail, String> Entreprise;
    @FXML
    private TableView<ContratDetail> ContratTable;
    
    ObservableList<ContratDetail> data_list;
    @FXML
    private Button list_liv;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ContratService contrat_service = new ContratService();
        ArrayList<ContratDetail> liste_contrat=(ArrayList<ContratDetail>) contrat_service.afficherContrat();
        data_list = FXCollections.observableArrayList(liste_contrat);
        
        
        date_debut.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        Entreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        
        ContratTable.setItems(data_list);
        
        
    }    

    @FXML
    private void OnClickContrat(ActionEvent event) {
    }

    @FXML
    private void OnClickProfile(ActionEvent event) {
    }

    @FXML
    private void OnClickLivraison(ActionEvent event) throws IOException {
        Parent livraison = FXMLLoader.load(getClass().getResource("Transporteur.fxml"));
        Scene livraisonV= new Scene(livraison);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(livraisonV);
        window.show();
    }

    @FXML
    private void OnClickListeLivraison(ActionEvent event) throws IOException {
         
        Parent LivEnCours = FXMLLoader.load(getClass().getResource("LivraisonEnCours.fxml"));
        Scene LivEnCoursV= new Scene(LivEnCours);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(LivEnCoursV);
        window.show();
    }
    
}
