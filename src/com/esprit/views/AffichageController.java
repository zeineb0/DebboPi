package com.esprit.views;

import com.esprit.entities.Livraison;
import com.esprit.entities.affdetails;
import com.esprit.services.impl.ServiceLivraison;
import com.esprit.utilities.DataSource;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
    private TextField tx2,tx3,tx4,tx5,tx6;
    private Statement ste;
    ServiceLivraison ser=new ServiceLivraison();
    
   private final ObservableList<affdetails> datalist = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label label;
    @FXML
    private TableColumn<?, ?> mat;
    @FXML
    private TextField txtmat;
    @FXML
    private ImageView icon;
   
    public static String dest;
    
   affdetails affselectionne =new affdetails();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
                Image image = new Image("/icon.png");
                icon.setImage(image); 
            Connection con = DataSource.getInstance().getConnection();
            ResultSet res = con.createStatement().executeQuery("SELECT livraison.id_livraison,livraison.date_livraison,livraison.adresse_livraison,livraison.etat_livraison,utilisateur.nom,utilisateur.prenom,utilisateur.tel FROM livraison,utilisateur where utilisateur.id_user=livraison.FK_id_user");    
            while(res.next()){
                    datalist.add(new affdetails(hashID(res.getString(1)),res.getDate(2),
                            res.getString(3),res.getString(4),
                            res.getString(5),res.getString(6),res.getString(7)));                   
                }            
            mat.setCellValueFactory(new PropertyValueFactory<>("hashmat"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));           
            adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            addButtonToTable();
            tableaff.setItems(datalist);
                
    
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
   }
    

    @FXML
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
    @FXML
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
    
    
    @FXML
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
    @FXML
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
    @FXML
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
              @FXML
          public void smartsearchmat(){ 
                           FilteredList<affdetails> filteredData2 = new FilteredList<>(datalist, b -> true);
        		// tbadél l predicate te3 l filtre selon tabdil l filtre
		txtmat.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData2.setPredicate(affdetails -> {
				// ken l filtre (searchbox) feragh n'affichi kol chay			
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                                // n9arén l predicate beli éna ktébtou selon date w etat
				String lowerCaseFilter = newValue.toLowerCase();
				if (affdetails.getHashmat().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
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
        TableColumn<affdetails, Void> colBtn = new TableColumn("MAP");

        Callback<TableColumn<affdetails, Void>, TableCell<affdetails, Void>> cellFactory = new Callback<TableColumn<affdetails, Void>, TableCell<affdetails, Void>>() {
            public TableCell<affdetails, Void> call(final TableColumn<affdetails, Void> param) {
                final TableCell<affdetails, Void> cell = new TableCell<affdetails, Void>() {
                    private final Button btn = new Button("Localisation");
                    {
                        tableaff.setOnMouseClicked((event) -> {
                            
affselectionne = tableaff.getItems().get(tableaff.getSelectionModel().getSelectedIndex());
                  //System.out.println("Produit selectionnner"+ProduitSelectionner);
                  //indexProduitSelectionner=table.getSelectionModel().getSelectedIndex();
                  
                  dest = affselectionne.getAdresse();
                                          
                        });
                        btn.setOnAction((ActionEvent event) ->{
                            try {
                                Parent root2 = FXMLLoader.load(getClass().getResource("Map.fxml"));
                                Scene scene1 = new Scene(root2);
                                Stage primaryStage1 = new Stage();
                                primaryStage1.setTitle("Hello World!");
                                primaryStage1.setScene(scene1);
                                primaryStage1.show();
                            } catch (IOException ex) {

                            }
                        });
                        
//                            btn.setOnAction((ActionEvent event) -> {
//                                String data = getTableView().getItems().get(getIndex()).getHashmat();
//                                String nb=unhashID(data);
//                                Integer id=Integer.parseInt(nb);
//                                try {
//                                       ser.delete(id);
//                                } catch (SQLException ex) {
//                                }
//                        });
 

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
    
public String hashID(String i){
    String sh="COM"+i+"NP";   
    return sh;   
}
public String unhashID(String i){
    return  i.substring(4, 1);
}

    @FXML
    private void showmap(MouseEvent event) {
        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("showmap.fxml"));
            Scene scene1 = new Scene(root2);
            Stage primaryStage1 = new Stage();
            primaryStage1.setTitle("Hello World!");
            primaryStage1.setScene(scene1);
            primaryStage1.show();
        } catch (IOException ex) {
        }
          
    }

}
