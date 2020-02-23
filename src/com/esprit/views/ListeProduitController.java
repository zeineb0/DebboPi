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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//masmaat chy meli koltholi

/**
 * FXML Controller class
 *
 * @author Zeineb_yahiaoui
 */
public class ListeProduitController implements Initializable {

    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> clLib;
    @FXML
    private TableColumn<Produit, Double> clPrix;
    @FXML
    private TableColumn<Produit, Integer> clRef;
    @FXML
    private TableColumn<Produit, String> clMarque;
    
        private Produit ProduitSelectionner =new Produit();

       CategorieController categorieController = new CategorieController();
        private  List<Categorie> categories=new ArrayList<>();
   
  
     List<Produit> produits=new ArrayList<>();
    
    ProduitController produitController=new ProduitController();
    @FXML
    private Button btn1;
    @FXML
    private Button btn3;
    @FXML
    private Button btn2;
    
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
    @FXML
    // private ComboBox<Entrepot> cmbE;
EntrepotController entrepotController = new EntrepotController();
    List<Entrepot> entrepots = new ArrayList<>();
    @FXML
    private Label lib;
    @FXML
    private Label prix;
    @FXML
    private Label ref;
    @FXML
    private Label mar;
    @FXML
    private Button btnAj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        categories= categorieController.listCategorie();
               produits=produitController.listeProduit();
               entrepots=entrepotController.readAll();
               
               System.out.println(entrepots);
               
               
               
               
        ObservableList observableList=FXCollections.observableArrayList(categories);
        ObservableList observableList1=FXCollections.observableArrayList(entrepots);
        //System.out.println(observableList1);
        cmbC.setItems(observableList);
        //cmbE.setItems(observableList1);

        
                reverseButtonModif(true);
                reverseButtonAjout(true);
                
                
                
                setInvisible(false);
         clLib.setCellValueFactory(new PropertyValueFactory("libelle"));
         clPrix.setCellValueFactory(new PropertyValueFactory("prix"));
         clRef.setCellValueFactory(new PropertyValueFactory("reference"));
         clMarque.setCellValueFactory(new PropertyValueFactory("marque"));
         table.getItems().addAll(produits);
         
         
                table.setOnMouseClicked(event->{
        //pour modifier un produit il faut faire deux click
              if (event.getClickCount() == 2) {
                  //activer les Buttons 
                  reverseButtonModif(false);
                  reverseButtonAjout(true);
                  setInvisible(true);

                  ProduitSelectionner = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                  //System.out.println("Produit selectionnner"+ProduitSelectionner);
                  indexProduitSelectionner=table.getSelectionModel().getSelectedIndex();
                  
                  txt.setText(ProduitSelectionner.getLibelle());
                  txt1.setText(String.valueOf(ProduitSelectionner.getPrix()));
                  txt2.setText(String.valueOf(ProduitSelectionner.getReference()));
                  txt3.setText(String.valueOf(ProduitSelectionner.getMarque()));
                  //System.out.println(ProduitSelectionner.getCategorie());
                  cmbC.setValue(ProduitSelectionner.getCategorie());
                 // cmbE.setValue(ProduitSelectionner.getEntrepot());
              }
             
                    });
       
    }    
    
    
    public void ref() {
        table.getItems().clear();
        table.getItems().addAll(produitController.listeProduit());
    }
    
     public void reverseButtonModif(Boolean etat){
        btn1.setDisable(etat);
                btn2.setDisable(etat);

      
    }
     public void reverseButtonAjout(Boolean etat){
  
        btnAj.setDisable(etat);
     }
     
     public void setInvisible(Boolean etat){
     lib.setVisible(etat);
     mar.setVisible(etat);
     prix.setVisible(etat);
     ref.setVisible(etat);
     txt.setVisible(etat);
     txt1.setVisible(etat);
     txt2.setVisible(etat);
     txt3.setVisible(etat);
     
     
     
     
     }
     
    @FXML
    private void onClickModif(ActionEvent event) {
        setInvisible(true);
        ProduitSelectionner.setLibelle(txt.getText());
       ProduitSelectionner.setPrix(Double.parseDouble(txt1.getText()));
       ProduitSelectionner.setReference(Integer.parseInt(txt2.getText()));
       ProduitSelectionner.setMarque(txt3.getText());
       
       ProduitSelectionner.setCategorie(cmbC.getValue());
       //ProduitSelectionner.setEntrepot(cmbE.getValue());
        //System.out.println(cmbE.getValue());
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
         produitController.supprimerProduit(ProduitSelectionner.getLibelle());
        table.getItems().remove(indexProduitSelectionner);
    }
    
    
    
    
}
