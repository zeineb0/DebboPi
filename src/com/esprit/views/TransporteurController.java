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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private TableView<Livraison> LivraisonTable;
   
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
    @FXML
    private Button list_liv;
    TableColumn<Livraison, Void> colBtn;
    @FXML
    private TextField filtreAdresse;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
         ContratService contrat_service = new ContratService();
        
        Utilisateur transporteur1 = new Utilisateur();
        transporteur1.setId(1);
        ArrayList<Livraison> list_livraison=(ArrayList<Livraison>) contrat_service.afficherLivraisonParTransporteurLivree(transporteur1);
         
         data_list = FXCollections.observableArrayList(list_livraison);
         
         
         
      
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
    private void OnClickContrat(ActionEvent event) throws IOException {
        Parent contrat = FXMLLoader.load(getClass().getResource("ContratListe.fxml"));
        Scene contratV= new Scene(contrat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(contratV);
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
    
    @FXML
        public void searchAdresse(){ 
            
            
            
            
                           FilteredList<Livraison> filteredData2 = new FilteredList<>(data_list, l -> true);
        		// tbadél l predicate te3 l filtre selon tabdil l filtre
		filtreAdresse.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData2.setPredicate(livraison -> {
				// ken l filtre (searchbox) feragh n'affichi kol chay			
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                                // n9arén l predicate beli éna ktébtou selon date w etat
				String lowerCaseFilter = newValue.toLowerCase();
				if (livraison.getAdresse_livraison().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}
				     else  
				    	 return false; 
			});
		});
        		// n7ot FilteredList f SortedList. 
		SortedList<Livraison> sortedData = new SortedList<>(filteredData2);
		
		// n9arén e sortedlist b tableview
		
		//n'ajouti tawa sortedlist l héya resultat te3 l filtre f tableview mte3i
		LivraisonTable.setItems(sortedData);
        
    }
    
    
    
}
