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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ASUS X550V
 */
public class AffichageController implements Initializable {

    @FXML
    private TableView<Livraison> tableaff;
    @FXML
//    private TableColumn<Livraison, Integer> id;
//    @FXML
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
    ServiceLivraison ser=new ServiceLivraison();
    
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
            
            
//            id.setCellValueFactory(new PropertyValueFactory<>("id_livraison"));
            date.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_livraison"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat_livraison"));
            longitude.setCellValueFactory(new PropertyValueFactory<>("longitude_dest"));
            laltitude.setCellValueFactory(new PropertyValueFactory<>("altitude_dest"));
            acc.setCellValueFactory(new PropertyValueFactory<>("acceptation"));
            nbc.setCellValueFactory(new PropertyValueFactory<>("FK_id_commande"));
            nbu.setCellValueFactory(new PropertyValueFactory<>("FK_id_user"));
            addButtonToTable();
            tableaff.setItems(datalist);
            
            
        FilteredList<Livraison> filteredData = new FilteredList<>(datalist, b -> true);
        		// tbadél l predicate te3 l filtre selon tabdil l filtre
		filterbox.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(livraison -> {
				// ken l filtre (searchbox) feragh n'affichi kol chay			
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                                // n9arén l predicate beli éna ktébtou selon date w etat
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (livraison.getDate_livraison().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (livraison.getEtat_livraison().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				     else  
				    	 return false; 
			});
		});

        		// n7ot FilteredList f SortedList. 
		SortedList<Livraison> sortedData = new SortedList<>(filteredData);
		
		// n9arén e sortedlist b tableview
		sortedData.comparatorProperty().bind(tableaff.comparatorProperty());
		
		//n'ajouti tawa sortedlist l héya resultat te3 l filtre f tableview mte3i
		tableaff.setItems(sortedData);
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
   }

    private void addButtonToTable() {
        TableColumn<Livraison, Void> colBtn = new TableColumn("Details");

        Callback<TableColumn<Livraison, Void>, TableCell<Livraison, Void>> cellFactory = new Callback<TableColumn<Livraison, Void>, TableCell<Livraison, Void>>() {
            public TableCell<Livraison, Void> call(final TableColumn<Livraison, Void> param) {
                final TableCell<Livraison, Void> cell = new TableCell<Livraison, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                            btn.setOnAction((ActionEvent event) -> {
                                int data = getTableView().getItems().get(getIndex()).getId_livraison();
                                try {
                                       ser.delete(data);
                                       
                                } catch (SQLException ex) {
                                }
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

        tableaff.getColumns().add(colBtn);

    }

        
    
}
