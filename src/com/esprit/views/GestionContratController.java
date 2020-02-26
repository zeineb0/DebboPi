/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Contrat;
import com.esprit.entities.ContratDetail;
import com.esprit.entities.Entrepot;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class GestionContratController implements Initializable {

    @FXML
    private TableView<ContratDetail> ContratTable;
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
    private Button addC;
        ObservableList<ContratDetail> data_list;
    @FXML
    private DatePicker dateD;
    @FXML
    private DatePicker dateF;
    @FXML
    private Button updC;
    @FXML
    private Button delC;
    @FXML
    private Button cancel;
    
    private int indexContratSelectionner;
    
    private ContratDetail Contrat_selectionne = new ContratDetail();
    ContratService contrat_service= new ContratService();
    
            Entrepot entrepot = new Entrepot();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        entrepot.setId_entrepot(3);
        
        ArrayList<ContratDetail> liste_contrat=(ArrayList<ContratDetail>) contrat_service.afficherContratEntrepot(entrepot);
        
        data_list = FXCollections.observableArrayList(liste_contrat);
        
        
        date_debut.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        Entreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        
        ContratTable.setItems(data_list);
        
        
        
        ContratTable.setOnMouseClicked(event->{
        //pour modifier un produit il faut faire deux click
            if (event.getClickCount() == 2)
            {
                  
                Contrat_selectionne =  ContratTable.getItems().get(ContratTable.getSelectionModel().getSelectedIndex());
                indexContratSelectionner=ContratTable.getSelectionModel().getSelectedIndex();
                  
                System.out.println(Contrat_selectionne);
             }
             
                    });
        
        
        
    }    
    
    
     public void ref() {
        ContratTable.getItems().clear();
        ContratTable.getItems().addAll(contrat_service.afficherContratEntrepot(entrepot));
    }

    @FXML
    private void onClickAdd(ActionEvent event) throws IOException {
        
         Parent ajouter_contrat = FXMLLoader.load(getClass().getResource("AjouterContrat.fxml"));
        Scene ajouterCV= new Scene(ajouter_contrat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ajouterCV);
        window.show();
    }

    @FXML
    private void onClickUpdate(ActionEvent event) {
        
         Date date_debut = Date.valueOf(dateD.getValue());
         Date date_fin = Date.valueOf(dateF.getValue());
         
         contrat_service.modifierContrat(Contrat_selectionne, date_debut, date_fin);
         ref();         
         
         
        
    }

    @FXML
    private void onClickDelete(ActionEvent event) {
        
        contrat_service.supprimerContrat(Contrat_selectionne);
        ContratTable.getItems().remove(indexContratSelectionner);
        
        
        
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
        
        
    }
    
}