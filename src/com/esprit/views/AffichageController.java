/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Livraison;
import com.esprit.services.impl.ServiceLivraison;
import com.esprit.utilities.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS X550V
 */
public class AffichageController implements Initializable {

    @FXML
    private TableView<Livraison> tableaff;
    @FXML
    private TableColumn<Livraison, Integer> id;
    @FXML
    private TableColumn<Livraison, String> date;
    @FXML
    private TableColumn<Livraison, String> adresse;
    @FXML
    private TableColumn<Livraison, Integer> etat;
    @FXML
    private TableColumn<Livraison, Float> longitude;
    @FXML
    private TableColumn<Livraison, Float> laltitude;
    @FXML
    private TableColumn<Livraison, String> acc;
    @FXML
    private TableColumn<Livraison, Integer> nbc;
    @FXML
    private TableColumn<Livraison, Integer> nbu;
    @FXML
    private TextField filterbox;
    private Statement ste;
    
   private final ObservableList<Livraison> datalist = FXCollections.observableArrayList();
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection con = DataSource.getInstance().getConnection();
            ResultSet res = con.createStatement().executeQuery("select * from livraison");
                while(res.next()){
                    datalist.add(new Livraison(res.getInt("id_livraison"),res.getString("date_livraison"),
                            res.getString("adresse_livraison"),res.getString("etat_livraison"),
                            res.getFloat("longitude_dest"),res.getFloat("altitude_dest"),res.getString("acceptation"),
                            res.getInt("FK_id_commande"),res.getInt("FK_id_user")
                    ));
                }
            
            
            id.setCellValueFactory(new PropertyValueFactory<>("id_livraison"));
            date.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat_livraison"));
            longitude.setCellValueFactory(new PropertyValueFactory<>("longitude_dest"));
            laltitude.setCellValueFactory(new PropertyValueFactory<>("altitude_dest"));
            acc.setCellValueFactory(new PropertyValueFactory<>("acceptation"));
            nbc.setCellValueFactory(new PropertyValueFactory<>("FK_id_commande"));
            nbu.setCellValueFactory(new PropertyValueFactory<>("FK_id_user"));
            
            
            
            tableaff.setItems(datalist);
            
        FilteredList<Livraison> filteredData = new FilteredList<>(datalist, b -> true);
        		// 2. Set the filter Predicate whenever the filter changes.
		filterbox.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(livraison -> {
				// If filter text is empty, display all persons				
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                                				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (livraison.getDate_livraison().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (livraison.getAdresse_livraison().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});

        		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Livraison> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableaff.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableaff.setItems(sortedData);
        } catch (SQLException ex) {
            Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    
 
    
}