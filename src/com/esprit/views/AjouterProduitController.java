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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zeineb_yahiaoui
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField txt;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private TextField txt3;
    private TextField txt4;
    @FXML
    private Button btn;
    @FXML
    private Button btn1;
      private Text txtErruerCateg;
    
    
    @FXML
    private ComboBox<Categorie> cmb;
    
    ProduitController produitController=new ProduitController();
    
    CategorieController categorieController = new CategorieController();
    
        private  List<Categorie> categories=new ArrayList<>();
    @FXML
    private ComboBox<Entrepot> cmb1;
        EntrepotController entrepotController=new EntrepotController();
        private List<Entrepot> entrepots= new ArrayList<>();
  


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
        categories= categorieController.listCategorie();
       entrepots=entrepotController.readAll();
        //System.out.println(categories);
        ObservableList observableList=FXCollections.observableArrayList(categories);
        cmb.setItems(observableList);
        ObservableList observableList1=FXCollections.observableArrayList(entrepots);
        cmb1.setItems(observableList1);
        
        
        
    }    
        private boolean verif(){
        if (cmb.getValue() != null && cmb1.getValue() != null) {
            return true;
        }
         
        return false;
    }

    @FXML
    private void onClickValider(ActionEvent event) {
        
        
        if (verif()){
        String nom = txt.getText();
        Double prix = Double.parseDouble(txt1.getText());
        Double qte = Double.parseDouble(txt2.getText());
        Double reserve = Double.parseDouble(txt3.getText());
        
         Produit p=new Produit();
         
        p.setNom(nom);
        p.setPrix(prix);
        p.setQuantite(qte);
        p.setReserve(reserve);
        Categorie c=cmb.getValue();
        p.setCategorie(c);
        Entrepot e = cmb1.getValue();
        p.setEntrepot(e);
        produitController.ajouterProduit(p); }
        
        
//        Parent parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//       Scene scene = new Scene(parent );
//       Stage stage  = (Stage)((Node)event.getSource()).getScene().getWindow();
//       stage .hide();
//       stage .setScene(scene );
//       stage.show();
       
        
        
        
        else{
            txtErruerCateg.setText("categorie invalide");
        }
        
    }

    @FXML
    private void onClickAnnuler(ActionEvent event) {
        
       
    }

    @FXML
    private void onClickCateg(ActionEvent event) {
    }
    
}
