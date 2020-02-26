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
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
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
    int id1;
    @FXML
    private Button modifier;
    Date datefin;
    Date datdeb;

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
              locationSel=table.getItems().get(table.getSelectionModel().getSelectedIndex());
              datdeb=locationSel.getDate_deb_location();
               datDeb.setValue(datdeb.toLocalDate());
               datFin.setValue(null);
               prix1.setText("");
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
         Parent list = FXMLLoader.load(getClass().getResource("gererLocation.fxml"));
        Scene listE= new Scene(list);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(listE);
        window.show();
    }

    @FXML
    private void ModLocation(ActionEvent event) {
        if(table.getSelectionModel().getSelectedIndex()==-1)
                {
                    System.out.println("selectionner de la table location");
                }
        else
        {   locationSel =table.getItems().get(table.getSelectionModel().getSelectedIndex());
            id= locationSel.getId_location(); 
            datefin=Date.valueOf(datFin.getValue());
                 if(datefin.toString().equals("")) 
                 {
                      System.out.println("selectionner date");
                 }
                 else
                 {Location l = new Location();
                   try {
                 
                  double p1=Double.parseDouble(prix1.getText());
                  l.setDate_fin_location(datefin);
                  l.setPrix_location(p1);
                  l.setId_location(id);

                  serviceLocation.update(l);
                    ref();
                                }
                 
                    
        catch (SQLException ex) {
             System.out.println("com.esprit.views.AfficherEntrepotController.onClick()");
        }
                 }
        }
    }

    @FXML
    private void SuppLocation(ActionEvent event) {
          if(table.getSelectionModel().getSelectedIndex()==-1)
                {
                    System.out.println("selectionner de la table location");

                }
           else
        {
                 locationSel =table.getItems().get(table.getSelectionModel().getSelectedIndex());
                 id= locationSel.getId_location();  
                  try 
                        {serviceLocation.delete(id);
                        ref();} 
                        catch (SQLException ex) 
                        {System.out.println(".handle()");}
                      

        }
    }

    @FXML
    private void getDate(ActionEvent event) throws SQLException {
                System.out.println("oui");
        if(table.getSelectionModel().getSelectedIndex()==-1||datFin.getValue()==null)
                {
                    System.out.println("erreur");
                }
        else
            
        {
        locationSel =table.getItems().get(table.getSelectionModel().getSelectedIndex());
          int  id_l= locationSel.getId_location(); 
           System.out.println("hhh"+id_l);
         int id_Ent= serviceLocation.getIDEntrepot(id_l);
            System.out.println("hhh"+id_Ent);
        try {
                         double prixLoca = serviceLocation.getPrix(id_Ent);
                         System.out.println(prixLoca);
                         datdeb=Date.valueOf(datDeb.getValue());
                         datefin=Date.valueOf(datFin.getValue());
                         String p = serviceLocation.calculPrix(prixLoca, datdeb, datefin).toString();
                          System.out.println(p);
                         prix1.setText(p);
                     } catch (SQLException ex) {
                                System.out.println("com.esprit.views.ListeDesLocationController.initialize()");
                     }

    }
    }


  
  
}
