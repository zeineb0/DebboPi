/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Entrepot;
import com.esprit.entities.utilisateur;
import com.esprit.services.impl.ServiceEntrepot;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
 * @author asus
 */
public class GererEntrepotController implements Initializable {
   

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Entrepot> table;
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
    ObservableList<Entrepot> datalist;
    ServiceEntrepot serviceEntrepot = new ServiceEntrepot();
    @FXML
    private TableColumn<Entrepot, Double> prix;
    private Entrepot EntrepotSelectionner = new Entrepot();
    private Entrepot EntrepotSelectionner1 = new Entrepot();

int id;
    @FXML
    private Button supp;
    @FXML
    private Button modifier;
    @FXML
    private TextField numfisc;
    @FXML
    private TextField quanmax;
    @FXML
    private TextField adresse;
    @FXML
    private ComboBox<String> etat1;
    @FXML
    private TextField entrep1;
    @FXML
    private TextField pri;
//public Observablelist<Entrepot> data= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    utilisateur u = new utilisateur();
    private Label prixLocation;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            ArrayList<Entrepot> entrepots = (ArrayList<Entrepot>) serviceEntrepot.readAll();
                
            datalist = FXCollections.observableArrayList(entrepots);
            adre.setCellValueFactory(new PropertyValueFactory<>("adresse_entrepot"));
            num.setCellValueFactory(new PropertyValueFactory<>("num_fiscale"));
            quant.setCellValueFactory(new PropertyValueFactory<>("quantite_max"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            prix.setCellValueFactory(new PropertyValueFactory<>("prix_location"));
            entrep.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
           // addButtonToTable();
            table.setItems(datalist);
      
        } catch (SQLException ex) {
             System.out.println("com.esprit.views.AfficherEntrepotController.onClick()");
        }
                ObservableList<String> list;

         try {
            list= FXCollections.observableArrayList("Libre","Loué","A louer");
            etat1.setItems(list);
        } catch (Exception ex) {
            System.out.println("com.esprit.views.AjouterEntrepotController.initialize()");
        }
        
         
    
        table.setOnMouseClicked(event->{
        //pour modifier un produit il faut faire deux click
              if (event.getClickCount() == 1) {
                  EntrepotSelectionner=table.getItems().get(table.getSelectionModel().getSelectedIndex());
                  id= EntrepotSelectionner.getId_entrepot();
                supp.setOnAction(new EventHandler<ActionEvent>() {
 
                @Override
                      public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setContentText(" Voulez-vous supprimer cet entrepot");
                alert.setHeaderText(null);
        
        
                Optional<ButtonType> resultat = alert.showAndWait();    
                if(resultat.get()== ButtonType.OK)
                {   try 
                        {serviceEntrepot.delete(id);
                        
                            ref();} 
                        catch (SQLException ex) 
                        {System.out.println(".handle()");}
                      }
                                       

                else
                {
                    System.out.println("cancel");
                }
                      }       
                 }); 
                  
                
        }
               
    
        //pour modifier un produit il faut faire deux click
              if (event.getClickCount() == 2) {
                  EntrepotSelectionner1=table.getItems().get(table.getSelectionModel().getSelectedIndex());
                  id= EntrepotSelectionner1.getId_entrepot();
                  adresse.setText(EntrepotSelectionner1.getAdresse_entrepot());
                  int num1 = EntrepotSelectionner1.getNum_fiscale();
                  String a= Integer.toString(num1);
                  numfisc.setText(a);
                  int num2 = EntrepotSelectionner1.getQuantite_max();
                  String b= Integer.toString(num2);
                  quanmax.setText(b);
                  etat1.setValue(EntrepotSelectionner1.getEtat());
                  entrep1.setText(EntrepotSelectionner1.getEntreprise());
                  Double num3 = EntrepotSelectionner1.getPrix_location();
                  String c= Double.toString(num3);
                  pri.setText(c);
                                                             
                modifier.setOnAction(new EventHandler<ActionEvent>() {
 
                   
                @Override
                      public void handle(ActionEvent event) {
                 if(numfisc.getText().equals("")||adresse.getText().equals("")||entrep1.getText().equals("")||etat1.getValue().toString().equals("")||quanmax.getText().equals(""))
                 {Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Modifier Entrepot");
                   alert.setHeaderText(null);
                   alert.setContentText("Les champs doivent etre rempli");
                   alert.showAndWait();}
                 else{
                        try {
                  Entrepot e = new Entrepot();

                  String ad = adresse.getText();
                  int num_fiscale = Integer.parseInt(numfisc.getText());
                  int quantite = Integer.parseInt(quanmax.getText());
                  String et=etat1.getValue();
                  Double pri1= Double.parseDouble(pri.getText());
                  
                  e.setPrix_location(pri1);
                  
                  e.setAdresse_entrepot(ad);
                  e.setNum_fiscale(num_fiscale);
                  e.setQuantite_max(quantite);
                  e.setEtat(et);
                  e.setEntreprise( entrep1.getText());
                  e.setId_entrepot(id);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de modification");
                alert.setContentText("Voulez-vous modifier cet entrepot ");
                alert.setHeaderText(null);
        
        
                Optional<ButtonType> resultat = alert.showAndWait();    
                if(resultat.get()== ButtonType.OK)
                {
                                      serviceEntrepot.update(e);
                                      adresse.setText("");
                                      numfisc.setText("");
                                       quanmax.setText("");
                                      etat1.setValue(null);
                                      entrep1.setText("");
                                      pri.setText("");
                                    ref();
}
                else
                {
                    System.out.println("cancel");
                }
                                }
                 
      
        catch (SQLException ex) {
             System.out.println("com.esprit.views.AfficherEntrepotController.onClick()");
        }
                      }}
              });
                        }
                        });
        
                       }
  
    
      public void ref() {
        try {
            table.getItems().clear();
            table.getItems().addAll(serviceEntrepot.readAll());
        } catch (SQLException ex) {
            System.out.println("com.esprit.views.EspaceFournisseurController.ref()");        }
    }          
        @FXML
    private void onClick1(ActionEvent event) throws IOException {
     Parent liste = FXMLLoader.load(getClass().getResource("ajouterEntrepot.fxml"));

           Scene ListeE = new Scene(liste);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListeE);
        window.show();
    }

//    private void onClick(ActionEvent event) throws IOException {
//         Parent liste = FXMLLoader.load(getClass().getResource("ajouterEntrepot.fxml"));
//
//           Scene ListeE = new Scene(liste);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(ListeE);
//        window.show();
//    }  
 
      
     
//    private void onClick3(ActionEvent event) throws IOException {
//        Parent liste = FXMLLoader.load(getClass().getResource("gererEntrepot.fxml"));
//        Scene ListeE = new Scene(liste);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(ListeE);
//        window.show();
//    }
////        Parent root = FXMLLoader.load(getClass().getResource("afficherEntrepot.fxml"));
//
//        Scene scene = new Scene(root);
//        Stage primaryStage = new Stage();
//        primaryStage.setTitle("Liste des entrepots");
//        primaryStage.setScene(scene);
//        primaryStage.show();

//    private void onClick(Event event) throws IOException {
//        //Parent liste = FXMLLoader.load(getClass().getResource("ajouterEntrepot.fxml"));
//
//           Scene ListeE = new Scene(liste);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(ListeE);
//        window.show();
//    }

   


   

    
       
    

    }

    
   
   

    

    
    

