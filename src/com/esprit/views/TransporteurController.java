/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Livraison;
import com.esprit.entities.Utilisateur;
import com.esprit.services.impl.ContratService;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class TransporteurController implements Initializable {

    @FXML
    private Button liv;
    @FXML
    private Button cnt;
    @FXML
    private Button pr;
    @FXML
    private TableView<Livraison> LivraisonTable;
    @FXML
    private TableColumn<Livraison, Integer> id;
    @FXML
    private TableColumn<Livraison, Date> date_livraison;
    @FXML
    private TableColumn<Livraison, String> adresse_livraison;
    @FXML
    private TableColumn<Livraison, String> etat_livraison;
    @FXML
    private TableColumn<Livraison, String> acceptation;
    @FXML
    private TableColumn<Livraison, Integer> FK_id_commande;
    @FXML
    private TableColumn<Livraison, Integer> FK_id_user;
    
      ObservableList<Livraison> data_list ;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ContratService contrat_service = new ContratService();
        
        Utilisateur transporteur1 = new Utilisateur();
        transporteur1.setId(1);
        ArrayList<Livraison> list_livraison=(ArrayList<Livraison>) contrat_service.afficherLivraisonParTransporteur(transporteur1);
         
         data_list = FXCollections.observableArrayList(list_livraison);
         
         id.setCellValueFactory(new PropertyValueFactory<>("id_livraison"));
         date_livraison.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
         adresse_livraison.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison"));
         etat_livraison.setCellValueFactory(new PropertyValueFactory<>("etat_livraison"));
         acceptation.setCellValueFactory(new PropertyValueFactory<>("acceptation"));
         FK_id_commande.setCellValueFactory(new PropertyValueFactory<>("FK_id_commande"));
         FK_id_user.setCellValueFactory(new PropertyValueFactory<>("FK_id_user"));
         
         LivraisonTable.setItems(data_list);
         
         
         
         
    }    

    @FXML
    private void OnClickLivraison(ActionEvent event) {
    }

    @FXML
    private void OnClickContrat(ActionEvent event) {
    }

    @FXML
    private void OnClickProfile(ActionEvent event) {
    }
    
}
