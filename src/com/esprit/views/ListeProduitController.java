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
import com.esprit.services.impl.ServiceEntrepot;
import com.esprit.services.impl.FlickerServiceImpl;
import com.esprit.services.impl.ProduitController;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    
    ServiceEntrepot entrepotController = new ServiceEntrepot();
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
    @FXML
    private Button btnModif;
    @FXML
    private Button btnAnnu;
    @FXML
    private Button btnSupp;
    @FXML
    private ComboBox<Entrepot> comboEntrepot;
    @FXML
    private Button btnImage;
    List<String> lstFiles;
String urli;
    @FXML
    private ImageView imgViwer;
    private TableColumn<Produit, ImageView> clImage;
    @FXML
    private TextField txt31;
    @FXML
    private Label mar1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          lstFiles = new ArrayList<>();
        lstFiles.add("*.JPG");
        lstFiles.add("*.PNG");
        lstFiles.add("*.JPEG");
        // TODO
        categories= categorieController.listCategorie();
               produits=produitController.listeProduit();
               entrepots=entrepotController.readAll();
               
               System.out.println(entrepots);
               
        ObservableList observableList=FXCollections.observableArrayList(categories);
        ObservableList observableList1=FXCollections.observableArrayList(entrepots);
        System.out.println(observableList1);
        cmbC.setItems(observableList);
        comboEntrepot.setItems(observableList1);

                reverseButtonAjout(false);
                reverseButtonModif(false);
                reverseButtonSupp(false);
                reverseButtonAnnuler(false);
                setInvisible(false);
                
         clLib.setCellValueFactory(new PropertyValueFactory("libelle"));
         clPrix.setCellValueFactory(new PropertyValueFactory("prix"));
         clRef.setCellValueFactory(new PropertyValueFactory("reference"));
         clMarque.setCellValueFactory(new PropertyValueFactory("marque"));
         table.getItems().addAll(produits);
         table.refresh();
                table.setOnMouseClicked(event->{
        //pour modifier un produit il faut faire deux click
              if (event.getClickCount() == 2) {
                  //activer les Buttons 
                  reverseButtonModif(false);
                  reverseButtonAjout(true);
                  reverseButtonSupp(false);
                  reverseButtonAnnuler(false);
                  setInvisible(true);

                  ProduitSelectionner = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                  //System.out.println("Produit selectionnner"+ProduitSelectionner);
                  indexProduitSelectionner=table.getSelectionModel().getSelectedIndex();
                  
                  txt.setText(ProduitSelectionner.getLibelle());
                  txt1.setText(String.valueOf(ProduitSelectionner.getPrix()));
                  txt2.setText(String.valueOf(ProduitSelectionner.getReference()));
                  txt3.setText(String.valueOf(ProduitSelectionner.getMarque()));
                 // System.out.println(ProduitSelectionner.getEntrepot());
                  cmbC.setValue(ProduitSelectionner.getCategorie());
                  comboEntrepot.setValue(ProduitSelectionner.getEntrepot());
                  Image image = new Image(ProduitSelectionner.getImage());
                  imgViwer.setImage(image);
              }
                    });}
    
    
    
   
    
     public void reverseButtonModif(Boolean etat){
                btnModif.setDisable(etat);
      
    }
     public void reverseButtonAjout(Boolean etat){
  
        btnAj.setDisable(etat);
        
     }
      public void reverseButtonSupp(Boolean etat){
  
        btnSupp.setDisable(etat);
     }
      public void reverseButtonAnnuler(Boolean etat){
  
        btnAnnu.setDisable(etat);
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
       ProduitSelectionner.setEntrepot(comboEntrepot.getValue());
        System.out.println(comboEntrepot.getValue());
        //System.out.println(ProduitSelectionner);
       ProduitSelectionner.setImage(urli);
       produitController.modiferProduit(ProduitSelectionner);
        ref();
    }

    @FXML
    private void onClickAnnul(ActionEvent event) {
             reverseButtonModif(false);
                  reverseButtonAjout(false);
                  reverseButtonSupp(false);
                  setInvisible(false);
    }

    @FXML
    private void onClickSupp(ActionEvent event) {
        
                reverseButtonModif(true);
                reverseButtonAjout(true);
                reverseButtonAnnuler(false);
                reverseButtonSupp(false);
 
           
                 if (ProduitSelectionner != null) {
                                   
         produitController.supprimerProduit(ProduitSelectionner);
         table.getItems().remove(indexProduitSelectionner);
             
                
        }
 

    }

    
    private boolean verif(){
        if (cmbC.getValue() != null && comboEntrepot.getValue() != null && txt !=null && txt1 != null && txt2 != null && txt3 !=null) {
            return true;
        }
         
        return false;
    }
    
    @FXML
    private void onClickAjouter(ActionEvent event) {
        
//        btnAj.setOnMouseClicked((event1) -> {
//            
//            if (event1.getClickCount()==1)
//                reverseButtonModif(true);
//                reverseButtonSupp(true);
//                reverseButtonAnnuler(true);
//            
//        });

        setInvisible(true);
        
        
        if (verif()){
        String nom = txt.getText();
        Double prix = Double.parseDouble(txt1.getText());
        Integer ref = Integer.parseInt(txt2.getText());
        String marque = txt3.getText();
        
         Produit p=new Produit();
         
        p.setLibelle(nom);
        p.setPrix(prix);
        p.setReference(ref);
        p.setMarque(marque);
        p.setQuantite(Integer.valueOf(txt31.getText()));
        Categorie c=cmbC.getValue();
        p.setCategorie(c);
        Entrepot e = comboEntrepot.getValue();
        p.setEntrepot(e);
        p.setImage(urli);
        produitController.ajouterProduit(p);  
         }
        
      ref();
        
    }
    
     public void ref() {
        table.getItems().clear();
        table.getItems().addAll(produitController.listeProduit());
    }

    
    @FXML
    private void onClickChooseImage(ActionEvent event) throws FileNotFoundException, Exception {
        
        
        FlickerServiceImpl flickr = new FlickerServiceImpl();

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", lstFiles));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            
            InputStream targetStream = new FileInputStream(f);
             urli = flickr.savePhoto(targetStream, "FirstImage");
            System.out.println("************   " +urli);
            //pour afficher l'image 
            Image image = new Image(urli);
         imgViwer.setImage(image);
            
        }

    }


}
        
       
      
    
    

