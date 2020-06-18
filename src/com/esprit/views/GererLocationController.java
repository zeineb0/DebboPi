/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Entrepot;
import com.esprit.entities.Location;
import com.esprit.services.impl.ServiceLocation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GererLocationController implements Initializable {
    @FXML
    private TableView<Entrepot> table;
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
    private TableColumn<Entrepot, Integer> idfour;
    ObservableList<Entrepot> datalist;
    ServiceLocation serviceLocation = new ServiceLocation();
private Entrepot EntrepotSelectionner = new Entrepot();
 public int idA;
 public float pri;
    @FXML
    private TableColumn<Entrepot, Float> prix;
    @FXML
    private DatePicker dateDeb;
    @FXML
    private DatePicker dateFin;
    private TextField idEnt;
    @FXML
    private Label erreur;
    @FXML
    private TextField prix1;
    @FXML
    private Label erreur1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 erreur.setVisible(false);

         try {
            ArrayList<Entrepot> entrepots = (ArrayList<Entrepot>) serviceLocation.readAL();
                
            datalist = FXCollections.observableArrayList(entrepots);
          //  Ide.setCellValueFactory(new PropertyValueFactory<>("id_entrepot"));
            adre.setCellValueFactory(new PropertyValueFactory<>("adresse_entrepot"));
            num.setCellValueFactory(new PropertyValueFactory<>("num_fiscale"));
            quant.setCellValueFactory(new PropertyValueFactory<>("quantite_max"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

            entrep.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
            prix.setCellValueFactory(new PropertyValueFactory<>("prix_location"));
//            idfour.setCellValueFactory(new PropertyValueFactory<>("fk_id_fournisseur"));
            erreur.setVisible(false);
            erreur1.setVisible(false);
            table.setItems(datalist);
      
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
         
          table.setOnMouseClicked((MouseEvent event)->{
        //pour modifier un produit il faut faire deux click
            if (event.getClickCount() == 2) {
                
                EntrepotSelectionner=table.getItems().get(table.getSelectionModel().getSelectedIndex());
                //System.out.println(EntrepotSelectionner);
                idA=EntrepotSelectionner.getId_entrepot();
                //System.out.println(idA);
                pri=EntrepotSelectionner.getPrix_location();
                //String id = Double.toString(idA);
                //idEnt.setText(id);
                Date date=Date.valueOf(LocalDate.now());
                Date date1 = Date.valueOf(dateDeb.getValue());
                Date date2 = Date.valueOf(dateFin.getValue());
                if(date.before(date1) && date1.before(date2))
                        {String p = serviceLocation.calculPrix(pri,date1, date2 ).toString();
                        prix1.setText(p);
                        }
                else if (date1.before(date))
                         {erreur.setVisible(true);}
                else  if (date2.before(date1))
                         {erreur1.setVisible(true);}
               
                  
                 
              }
                    });

         
    }    
  
    @FXML
    private void ajouterLocation(ActionEvent event) {
        

        try {
        Date date1 = Date.valueOf(dateDeb.getValue());
        Date date2 = Date.valueOf(dateFin.getValue());

         
            Location l =new Location();
            
            l.setDate_deb_location(date1);
            l.setDate_fin_location(date2);
            
            l.setPrix_location(Float.parseFloat(prix1.getText()));
        
         l.setFK_id_entrepot(idA);
            System.out.println(l);
        serviceLocation.ajouter(l);
        serviceLocation.modifierEtatEntrepotALoue(EntrepotSelectionner.getId_entrepot());
            ref();
        
     } catch (SQLException ex) {
         System.out.println("com.esprit.views.EspaceClientController.ajouterLocation()");     }
    }
     public void ref() {
        try {
            table.getItems().clear();
            table.getItems().addAll(serviceLocation.readAL());
        } catch (SQLException ex) {
            System.out.println("erreur");        }
 }          


    @FXML
    private void listerLocation(ActionEvent event) throws IOException {
        Parent list = FXMLLoader.load(getClass().getResource("listeDesLocation.fxml"));
        Scene listE= new Scene(list);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(listE);
        window.show();


    }
    
}
