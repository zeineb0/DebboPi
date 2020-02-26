/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Entrepot;
import com.esprit.entities.Location;
import com.esprit.entities.LocationDetail;
import com.esprit.services.impl.ServiceEntrepot;
import com.esprit.services.impl.ServiceLocation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ListeDesLocationController implements Initializable {

    @FXML
    private TableView<LocationDetail> table;
    @FXML
    private TableColumn<LocationDetail, Date> dateDeb;
    @FXML
    private TableColumn<LocationDetail, Date> dateFin;
    @FXML
    private TableColumn<LocationDetail,Double> prix;
    @FXML
    private TableColumn<LocationDetail, Integer> quan;
    @FXML
    private TableColumn<LocationDetail, String> adr;
    @FXML
    private TextField prix1;
    @FXML
    private Label erreur;
    @FXML
    private Label erreur1;
    @FXML
    private DatePicker datDeb;
    @FXML
    private DatePicker datFin;
    @FXML
    private TableColumn<LocationDetail, String> nom;
     ObservableList<LocationDetail> datalist;
    ServiceLocation serviceLocation = new ServiceLocation();
    Location locationSelectionner = new Location();
    LocationDetail locationSel = new LocationDetail();
    @FXML
    private TableColumn<LocationDetail, String> prenom;
    @FXML
    private Button supp;
    int id;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            ArrayList<LocationDetail> locationsDetails = (ArrayList<LocationDetail>) serviceLocation.readAl1();
                
            datalist = FXCollections.observableArrayList(locationsDetails);
            
            dateDeb.setCellValueFactory(new PropertyValueFactory<>("date_deb_location"));
            dateFin.setCellValueFactory(new PropertyValueFactory<>("date_fin_location"));
            prix.setCellValueFactory(new PropertyValueFactory<>("prix_location"));
            quan.setCellValueFactory(new PropertyValueFactory<>("quantite_max"));
            adr.setCellValueFactory(new PropertyValueFactory<>("adresse_entrepot"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            //addButtonToTable();
            table.setItems(datalist);
            erreur.setVisible(false);
            erreur1.setVisible(false);
      
        } catch (SQLException ex) {
             System.out.println("com.esprit.views.AfficherEntrepotController.onClick()");
        }
        
    
        table.setOnMouseClicked(event->{
//        pour modifier un produit il faut faire deux click
              if (event.getClickCount() == 1) {
                  locationSel =table.getItems().get(table.getSelectionModel().getSelectedIndex());
                 id= locationSel.getId_location();
                supp.setOnAction(new EventHandler<ActionEvent>() {
 
                @Override
                      public void handle(ActionEvent event) {
                        try 
                        {serviceLocation.delete(id);
                        ref();} 
                        catch (SQLException ex) 
                        {System.out.println(".handle()");}
                      }
                
                 }); 
                  
              
        }
               
    
        //pour modifier un produit il faut faire deux click
              if (event.getClickCount() == 2) {
                 locationSel=table.getItems().get(table.getSelectionModel().getSelectedIndex());
                  id= locationSel.getId_location();
                  adr.setText(locationSel.getAdresse_entrepot());
                  double num1 = locationSel.getPrix_location();
                  //String a= Double.toString(num1);
                  //prix1.setText(a);
                  Date datdebans=locationSel.getDate_deb_location();
                  Date datfinans=locationSel.getDate_fin_location();
                 

      
                modifier.setOnAction(new EventHandler<ActionEvent>() {
 
                   
                @Override
                      public void handle(ActionEvent event) {
                        try {
                  Location l = new Location();

                 
                  Date datedeb=Date.valueOf(datDeb.getValue());
                  Date datefin=Date.valueOf(datFin.getValue());
                  if (datedeb.before(datdebans))
                  
                  erreur.setVisible(true);
                  if (datefin.before(datfinans))
                  {
                  erreur1.setVisible(true);}
                  else
                  {String p = serviceLocation.updateCalculPrix(num1, datfinans, datdebans, datedeb, datefin).toString();
                  prix1.setText(p);
                  double p1=Double.parseDouble(p);
                  
                  l.setDate_deb_location(datedeb);
                  l.setDate_fin_location(datefin);
                  
                   l.setPrix_location(p1);}

                  serviceLocation.update(l);
                    ref();
                                }
                 
      
        catch (SQLException ex) {
             System.out.println("com.esprit.views.AfficherEntrepotController.onClick()");
        }
                  
                      }
              });
                        }
                        });
        
                       }
  
    
      public void ref() {
        try {
            table.getItems().clear();
            table.getItems().addAll(serviceLocation.readAl1());
        } catch (SQLException ex) {
            System.out.println("com.esprit.views.EspaceFournisseurController.ref()");        }
 }          

   



    @FXML
    private void retourALaListeDesEntrepots(ActionEvent event) throws IOException {
         Parent list = FXMLLoader.load(getClass().getResource("espaceClient.fxml"));
        Scene listE= new Scene(list);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(listE);
        window.show();
    }
    
}
