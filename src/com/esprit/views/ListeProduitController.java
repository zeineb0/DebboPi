/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Categorie;
import com.esprit.entities.Entrepot;
import com.esprit.entities.Produit;
import com.esprit.services.impl.CategorieController;
import com.esprit.services.impl.EntrepotController;
import com.esprit.services.impl.ProduitController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Zeineb_yahiaoui
 */
public class ListeProduitController implements Initializable {

    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> clNom;
    @FXML
    private TableColumn<Produit, Double> clPrix;
    @FXML
    private TableColumn<Produit, Double> clQte;
    @FXML
    private TableColumn<Produit, Double> clReserve;
    
        private Produit ProduitSelectionner =new Produit();

       CategorieController categorieController = new CategorieController();
    
        private  List<Categorie> categories=new ArrayList<>();
    EntrepotController entrepotController=new EntrepotController();
        private List<Entrepot> entrepots= new ArrayList<>();
  
     List<Produit> produits=new ArrayList<>();
    
    ProduitController produitController=new ProduitController();
    @FXML
    private Button btn1;
    @FXML
    private Button btn3;
    @FXML
    private Button btn2;
    @FXML
    private ComboBox<Entrepot> cmbE;
    @FXML
    private ComboBox<Categorie> cmbC;
    @FXML
    private TextField txt;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private TextField txt3;
        private int indexProduitSelectionner;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        categories= categorieController.listCategorie();
       entrepots=entrepotController.readAll();
               produits=produitController.listeProduit();

        ObservableList observableList=FXCollections.observableArrayList(categories);
        ObservableList observableList1=FXCollections.observableArrayList(entrepots);
        cmbC.setItems(observableList);
        cmbE.setItems(observableList1);
        
                reverseButton(true);
                
         clNom.setCellValueFactory(new PropertyValueFactory("Nom"));
         clPrix.setCellValueFactory(new PropertyValueFactory("prix"));
         clQte.setCellValueFactory(new PropertyValueFactory("quantite"));
         clReserve.setCellValueFactory(new PropertyValueFactory("reserve"));
         table.getItems().addAll(produits);
         
         
                table.setOnMouseClicked(event->{
        //pour modifier un produit il faut faire deux click
              if (event.getClickCount() == 2) {
                  //activer les Buttons 
                  reverseButton(false);
                  
                  ProduitSelectionner = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                  indexProduitSelectionner=table.getSelectionModel().getSelectedIndex();
                  txt.setText(ProduitSelectionner.getNom());
                  txt1.setText(String.valueOf(ProduitSelectionner.getPrix()));
                  txt2.setText(String.valueOf(ProduitSelectionner.getQuantite()));
                  txt3.setText(String.valueOf(ProduitSelectionner.getReserve()));
                  //System.out.println(ProduitSelectionner.getCategorie());
                  cmbC.setValue(ProduitSelectionner.getCategorie());
              }
             
                    });
        
        
        
    }    
    
    
    public void ref() {
        table.getItems().clear();
        table.getItems().addAll(produitController.listeProduit());
    }
    
     public void reverseButton(Boolean etat){
        btn1.setDisable(etat);
        btn2.setDisable(etat);
        btn3.setDisable(etat);
    }

    @FXML
    private void onClickModif(ActionEvent event) {
        
        ProduitSelectionner.setNom(txt.getText());
       ProduitSelectionner.setPrix(Double.parseDouble(txt1.getText()));
       ProduitSelectionner.setQuantite(Double.parseDouble(txt2.getText()));
       ProduitSelectionner.setQuantite(Double.parseDouble(txt3.getText()));
        //System.out.println(ProduitSelectionner);
       produitController.modiferProduit(ProduitSelectionner);
       
       /**
        * afin d'afficher la liste finale il nous faut 
        * 
        * 1-vider la liste et remplire la liste de puis la BD 
        */
      
     
        ref();

    }

    @FXML
    private void onClickAnnul(ActionEvent event) {
    }

    @FXML
    private void onClickSupp(ActionEvent event) {
         produitController.supprimerProduit(ProduitSelectionner.getNom());
        table.getItems().remove(indexProduitSelectionner);
    }
    
    
    
    
}
