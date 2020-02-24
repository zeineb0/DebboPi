package com.esprit.views;

import com.esprit.entities.Livraison;
import com.esprit.entities.affdetails;
import com.esprit.services.impl.ServiceLivraison;
import com.esprit.utilities.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
    private TableView<affdetails> tableaff;
//    @FXML
//    private TableColumn<Livraison, Integer> id;
    @FXML
    private TableColumn<affdetails, Date> date;
    @FXML
    private TableColumn<affdetails, String> adresse;
    @FXML
    private TableColumn<affdetails, String> etat;
    @FXML
    private TableColumn<affdetails, String> nom;
    @FXML
    private TableColumn<affdetails, String> prenom;
    @FXML
    private TableColumn<affdetails, String> tel;
    @FXML
    private TextField filterbox;
    @FXML
    private TextField tx1,tx2,tx3,tx4,tx5,tx6;
    private Statement ste;
    ServiceLivraison ser=new ServiceLivraison();
    
   private final ObservableList<affdetails> datalist = FXCollections.observableArrayList();
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            Connection con = DataSource.getInstance().getConnection();
            ResultSet res = con.createStatement().executeQuery("SELECT livraison.date_livraison,livraison.adresse_livraison,livraison.etat_livraison,utilisateur.nom,utilisateur.prenom,utilisateur.tel FROM livraison,utilisateur where utilisateur.id_user=livraison.FK_id_user");    
            while(res.next()){
                    datalist.add(new affdetails(res.getDate(1),
                            res.getString(2),res.getString(3),
                            res.getString(4),res.getString(5),res.getString(6)));
                    
                }            
//            id.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            addButtonToTable();
            tableaff.setItems(datalist);
            
//                // TEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEXTFIELD 3
//                FilteredList<affdetails> filteredData3 = new FilteredList<>(datalist, b -> true);
//        		// tbadél l predicate te3 l filtre selon tabdil l filtre
//		tx3.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData3.setPredicate(affdetails -> {
//				// ken l filtre (searchbox) feragh n'affichi kol chay			
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//                                // n9arén l predicate beli éna ktébtou selon date w etat
//				String lowerCaseFilter = newValue.toLowerCase();
//				if (affdetails.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//					return true; 
//				}
//				     else  
//				    	 return false; 
//			});
//		});
//        		// n7ot FilteredList f SortedList. 
//		SortedList<affdetails> sortedData3 = new SortedList<>(filteredData3);
//		
//		// n9arén e sortedlist b tableview
//		sortedData3.comparatorProperty().bind(tableaff.comparatorProperty());
//		
//		//n'ajouti tawa sortedlist l héya resultat te3 l filtre f tableview mte3i
//		tableaff.setItems(sortedData3);
//                //TEXTFIEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELD 4
//                                   FilteredList<affdetails> filteredData4 = new FilteredList<>(datalist, b -> true);
//        		// tbadél l predicate te3 l filtre selon tabdil l filtre
//		tx4.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData4.setPredicate(affdetails -> {
//				// ken l filtre (searchbox) feragh n'affichi kol chay			
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//                                // n9arén l predicate beli éna ktébtou selon date w etat
//				String lowerCaseFilter = newValue.toLowerCase();
//				if (affdetails.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//					return true; 
//				}
//				     else  
//				    	 return false; 
//			});
//		});
//        		// n7ot FilteredList f SortedList. 
//		SortedList<affdetails> sortedData4 = new SortedList<>(filteredData4);
//		
//		// n9arén e sortedlist b tableview
//		sortedData4.comparatorProperty().bind(tableaff.comparatorProperty());
//		
//		//n'ajouti tawa sortedlist l héya resultat te3 l filtre f tableview mte3i
//		tableaff.setItems(sortedData4);
                
    
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
   }
    

    public void smartsearchAdr(){ 
                           FilteredList<affdetails> filteredData2 = new FilteredList<>(datalist, b -> true);
        		// tbadél l predicate te3 l filtre selon tabdil l filtre
		tx2.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData2.setPredicate(affdetails -> {
				// ken l filtre (searchbox) feragh n'affichi kol chay			
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                                // n9arén l predicate beli éna ktébtou selon date w etat
				String lowerCaseFilter = newValue.toLowerCase();
				if (affdetails.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}
				     else  
				    	 return false; 
			});
		});
        		// n7ot FilteredList f SortedList. 
		SortedList<affdetails> sortedData = new SortedList<>(filteredData2);
		
		// n9arén e sortedlist b tableview
		sortedData.comparatorProperty().bind(tableaff.comparatorProperty());
		
		//n'ajouti tawa sortedlist l héya resultat te3 l filtre f tableview mte3i
		tableaff.setItems(sortedData);
        
    }
      public void smartsearchEtat(){ 
                           FilteredList<affdetails> filteredData2 = new FilteredList<>(datalist, b -> true);
        		// tbadél l predicate te3 l filtre selon tabdil l filtre
		tx3.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData2.setPredicate(affdetails -> {
				// ken l filtre (searchbox) feragh n'affichi kol chay			
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                                // n9arén l predicate beli éna ktébtou selon date w etat
				String lowerCaseFilter = newValue.toLowerCase();
				if (affdetails.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}
				     else  
				    	 return false; 
			});
		});
        		// n7ot FilteredList f SortedList. 
		SortedList<affdetails> sortedData = new SortedList<>(filteredData2);
		
		// n9arén e sortedlist b tableview
		sortedData.comparatorProperty().bind(tableaff.comparatorProperty());
		
		//n'ajouti tawa sortedlist l héya resultat te3 l filtre f tableview mte3i
		tableaff.setItems(sortedData);
        
    }
    
    
      public void smartsearchNom(){ 
                           FilteredList<affdetails> filteredData2 = new FilteredList<>(datalist, b -> true);
        		// tbadél l predicate te3 l filtre selon tabdil l filtre
		tx4.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData2.setPredicate(affdetails -> {
				// ken l filtre (searchbox) feragh n'affichi kol chay			
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                                // n9arén l predicate beli éna ktébtou selon date w etat
				String lowerCaseFilter = newValue.toLowerCase();
				if (affdetails.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}
				     else  
				    	 return false; 
			});
		});
        		// n7ot FilteredList f SortedList. 
		SortedList<affdetails> sortedData = new SortedList<>(filteredData2);
		
		// n9arén e sortedlist b tableview
		sortedData.comparatorProperty().bind(tableaff.comparatorProperty());
		
		//n'ajouti tawa sortedlist l héya resultat te3 l filtre f tableview mte3i
		tableaff.setItems(sortedData);
        
    }
        public void smartsearchPrenom(){ 
                           FilteredList<affdetails> filteredData2 = new FilteredList<>(datalist, b -> true);
        		// tbadél l predicate te3 l filtre selon tabdil l filtre
		tx5.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData2.setPredicate(affdetails -> {
				// ken l filtre (searchbox) feragh n'affichi kol chay			
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                                // n9arén l predicate beli éna ktébtou selon date w etat
				String lowerCaseFilter = newValue.toLowerCase();
				if (affdetails.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}
				     else  
				    	 return false; 
			});
		});
        		// n7ot FilteredList f SortedList. 
		SortedList<affdetails> sortedData = new SortedList<>(filteredData2);
		
		// n9arén e sortedlist b tableview
		sortedData.comparatorProperty().bind(tableaff.comparatorProperty());
		
		//n'ajouti tawa sortedlist l héya resultat te3 l filtre f tableview mte3i
		tableaff.setItems(sortedData);
        
    }
          public void smartsearchTel(){ 
                           FilteredList<affdetails> filteredData2 = new FilteredList<>(datalist, b -> true);
        		// tbadél l predicate te3 l filtre selon tabdil l filtre
		tx6.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData2.setPredicate(affdetails -> {
				// ken l filtre (searchbox) feragh n'affichi kol chay			
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                                // n9arén l predicate beli éna ktébtou selon date w etat
				String lowerCaseFilter = newValue.toLowerCase();
				if (affdetails.getTel().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}
				     else  
				    	 return false; 
			});
		});
        		// n7ot FilteredList f SortedList. 
		SortedList<affdetails> sortedData = new SortedList<>(filteredData2);
		
		// n9arén e sortedlist b tableview
		sortedData.comparatorProperty().bind(tableaff.comparatorProperty());
		
		//n'ajouti tawa sortedlist l héya resultat te3 l filtre f tableview mte3i
		tableaff.setItems(sortedData);
        
    }
           
    private void addButtonToTable() {
        TableColumn<affdetails, Void> colBtn = new TableColumn("Details");

        Callback<TableColumn<affdetails, Void>, TableCell<affdetails, Void>> cellFactory = new Callback<TableColumn<affdetails, Void>, TableCell<affdetails, Void>>() {
            public TableCell<affdetails, Void> call(final TableColumn<affdetails, Void> param) {
                final TableCell<affdetails, Void> cell = new TableCell<affdetails, Void>() {

                    private final Button btn = new Button("Supprimer");

//                    {
//                            btn.setOnAction((ActionEvent event) -> {
//                                int data = getTableView().getItems().get(getIndex()).getEtat();
//                                try {
//                                       ser.delete(data);
//                                       
//                                } catch (SQLException ex) {
//                                }
//                        });
// 
//
//                    }

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
