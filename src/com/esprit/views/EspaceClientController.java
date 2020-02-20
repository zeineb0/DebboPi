/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Entrepot;
import com.esprit.entities.Location;
import com.esprit.services.impl.ServiceEntrepot;
import com.esprit.services.impl.ServiceLocation;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EspaceClientController implements Initializable {
 @FXML
    private TableView<Entrepot> table;
    @FXML
    private TableColumn<Entrepot, Integer> Ide;
    @FXML
    private TableColumn<Entrepot, String> adre;
    @FXML
    private TableColumn<Entrepot, Integer> num;
    @FXML
    private TableColumn<Entrepot, Integer> quant;
    @FXML
    private TableColumn<Entrepot, String> etat;
    @FXML
    private TableColumn<Entrepot, String> entrep;
    @FXML
    private TableColumn<Entrepot, Integer> idfour;
    ObservableList<Entrepot> datalist;
    ServiceLocation serviceLocation = new ServiceLocation();
private Entrepot EntrepotSelectionner = new Entrepot();
 public int id;
 public double pri;
    @FXML
    private TableColumn<Entrepot, Double> prix;
    @FXML
    private TextField entrep1;
    @FXML
    private TextField quanmax;
    @FXML
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
            ArrayList<Entrepot> entrepots = (ArrayList<Entrepot>) serviceLocation.readAL();
                
            datalist = FXCollections.observableArrayList(entrepots);
            Ide.setCellValueFactory(new PropertyValueFactory<>("id_entrepot"));
            adre.setCellValueFactory(new PropertyValueFactory<>("adresse_entrepot"));
            num.setCellValueFactory(new PropertyValueFactory<>("num_fiscale"));
            quant.setCellValueFactory(new PropertyValueFactory<>("quantite_max"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

            entrep.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
            prix.setCellValueFactory(new PropertyValueFactory<>("prix_location"));
            idfour.setCellValueFactory(new PropertyValueFactory<>("fk_id_fournisseur"));
            
            table.setItems(datalist);
      
        } catch (SQLException ex) {
             System.out.println("com.esprit.views.AfficherEntrepotController.onClick()");
        }
         
          table.setOnMouseClicked(event->{
        //pour modifier un produit il faut faire deux click
              if (event.getClickCount() == 2) {
                  EntrepotSelectionner=table.getItems().get(table.getSelectionModel().getSelectedIndex());
                  id=EntrepotSelectionner.getId_entrepot();
                  pri=EntrepotSelectionner.getPrix_location();
                  System.out.println(id);
              }
                    });

         
    }    

    @FXML
    private void ajouterLocation(ActionEvent event) {
        
//        try{
//        String ad = adresse.getText();
//            Date date = Integer.parseInt(date.getText());
//        int quantite = Integer.parseInt(quanmax.getText());
//        String entreprise = entrep.getText();
//        Location l =new Location(date_deb_location, date_fin_location, pri, quantite, id);
//        e.setAdresse_entrepot(ad);
//        e.setNum_fiscale(num_fiscale);
//        e.setQuantite_max(quantite);
//      
//        e.setEntreprise(entreprise);
//      
//           
//        serviceEntrepot.ajouter(e);
//            } catch (SQLException ex) {
//                System.out.println("com.esprit.views.AjouterEntrepotController.onClick()");;
//            }
//            
    }
    
}
