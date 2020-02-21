/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Livraison;
import com.esprit.entities.Utilisateur;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class LivraisonEnCoursController implements Initializable {

    @FXML
    private Button liv;
    @FXML
    private Button cnt;
    @FXML
    private Button pr;
    
   
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
    
    private TableColumn<?, ?> btn;
    @FXML
    private Button list_liv;
    
    ObservableList<Livraison> data_list ;
    @FXML
    private TableView<Livraison> LivraisonEncoursTable;
    @FXML
    private TableColumn<Livraison, Void> colBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ContratService contrat_service = new ContratService();
        
        Utilisateur transporteur1 = new Utilisateur();
        transporteur1.setId(1);
        ArrayList<Livraison> list_livraison=(ArrayList<Livraison>) contrat_service.afficherLivraisonParTransporteurNonLivree(transporteur1);
         
         data_list = FXCollections.observableArrayList(list_livraison);
         
        
         date_livraison.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
         adresse_livraison.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison"));
         etat_livraison.setCellValueFactory(new PropertyValueFactory<>("etat_livraison"));
         acceptation.setCellValueFactory(new PropertyValueFactory<>("acceptation"));
         FK_id_commande.setCellValueFactory(new PropertyValueFactory<>("FK_id_commande"));
         FK_id_user.setCellValueFactory(new PropertyValueFactory<>("FK_id_user"));
         addButtonToTable();
         LivraisonEncoursTable.setItems(data_list);
         
         
         
         
    }
    
    
    private void addButtonToTable() {
       

        Callback<TableColumn<Livraison, Void>, TableCell<Livraison, Void>> cellFactory = new Callback<TableColumn<Livraison, Void>, TableCell<Livraison, Void>>() {
            public TableCell<Livraison, Void> call(final TableColumn<Livraison, Void> param) {
                final TableCell<Livraison, Void> cell = new TableCell<Livraison, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                            btn.setOnAction((ActionEvent event) -> {
                            Livraison data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                        });
 

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

       // LivraisonTable.getColumns().add(colBtn);

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
    private void OnClickContrat(ActionEvent event) throws IOException {
        Parent contrat = FXMLLoader.load(getClass().getResource("ContratListe.fxml"));
        Scene contratV= new Scene(contrat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(contratV);
        window.show();
    }

    @FXML
    private void OnClickProfile(ActionEvent event) {
    }

    @FXML
    private void OnClickListeLivraison(ActionEvent event) {
    }
    
}
