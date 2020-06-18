/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Categorie;
import com.esprit.services.impl.CategorieController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Zeineb_yahiaoui
 */
public class ListeCategorieController implements Initializable {

    @FXML
    private TableColumn<Categorie, String> clNom;
    @FXML
    private TextField txtNom;
    @FXML
    private TableView<Categorie> table;
    @FXML
    private Button btnA;
    @FXML
    private Button btnM;
    @FXML
    private Button btnS;
    @FXML
    private Button btnAn;
            CategorieController categorieController= new CategorieController();
    private List<Categorie> categories= new ArrayList<>();
private Categorie categorieSelectionner= new Categorie();
private  int indexCategorieSelectionner;
    @FXML
    private Label labelprofile;
    @FXML
    private Button catégorie;
    @FXML
    private Button produit;
    @FXML
    private Button mvt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
                categories= categorieController.listCategorie();
                         clNom.setCellValueFactory(new PropertyValueFactory("Nom"));
         table.getItems().addAll(categories);
table.setOnMouseClicked(event->{
        //pour modifier un produit il faut faire deux click
              if (event.getClickCount() == 2) {
                  //activer les Buttons 
                  reverseButton(false);
                  
                   categorieSelectionner= table.getItems().get(table.getSelectionModel().getSelectedIndex());
                  indexCategorieSelectionner=table.getSelectionModel().getSelectedIndex();
                  txtNom.setText(categorieSelectionner.getNom());
                  
              }
             
                    });
        
        
        
    }    

    @FXML
    private void onClickAjouter(ActionEvent event) {
        
        String nom = txtNom.getText();
        
        
    Categorie c = new Categorie();
        c.setNom(nom);
      categorieController.ajouterCategorie(c);
      
      table.refresh();
        ref();
     txtNom.setText("");
    }

    @FXML
    private void onClickModif(ActionEvent event) {
        categorieSelectionner.setNom(txtNom.getText());
     
        //System.out.println(ProduitSelectionner);
       categorieController.modiferCategorie(categorieSelectionner);
       
       /**
        * afin d'afficher la liste finale il nous faut 
        * 
        * 1-vider la liste et remplire la liste de puis la BD 
        */
      
     
        ref();

        
        
    }
    public void ref() {
        table.getItems().clear();
        table.getItems().addAll(categorieController.listCategorie());
    }
    @FXML
    private void onClickSupprimer(ActionEvent event) {
        categorieController.supprimerCategorie(categorieSelectionner.getNom());
        table.getItems().remove(indexCategorieSelectionner);
        
    }

   
 
     public void reverseButton(Boolean etat){
        btnA.setDisable(etat);
        btnM.setDisable(etat);
        btnS.setDisable(etat);
        btnAn.setDisable(etat);
    }
     
      @FXML
    private void onClickAnnuler(ActionEvent event) {
    }

    @FXML
    private void S(MouseEvent event) {
    }

    
}
