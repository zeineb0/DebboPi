/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Categorie;
import com.esprit.entities.Entrepot;
import com.esprit.entities.MouvementStock;
import com.esprit.entities.Produit;
import com.esprit.services.impl.CategorieController;
import com.esprit.services.impl.ServiceEntrepot;
import com.esprit.services.impl.MouvementController;
import com.esprit.services.impl.ProduitController;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Zeineb_yahiaoui
 */
public class MvtStockController implements Initializable {

    @FXML
    private TextField txt;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private DatePicker date;
    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> clLib;
    @FXML
    private TableColumn<Produit,Double> clPrix;
    @FXML
    private TableColumn<Produit, Integer> clRef;
    @FXML
    private TableColumn<Produit, String> clMarq;
    @FXML
    private TextField txt3;
    private MouvementStock mouvementStock = new MouvementStock();
    private Produit ProduitSelectionner =new Produit();

    CategorieController categorieController = new CategorieController();
    private  List<Categorie> categories=new ArrayList<>();
   
    ProduitController produitController=new ProduitController();
    private List<Produit> produits=new ArrayList<>();
    
     private int indexProduitSelectionner;
  
    ServiceEntrepot entrepotController = new ServiceEntrepot();
    List<Entrepot> entrepots = new ArrayList<>();
    
    MouvementController mouvementController = new MouvementController();
    
    @FXML
    private ComboBox<Entrepot> cmbE;
    @FXML
    private ComboBox<Categorie> cmbC;
    @FXML
    private Button btnEn;
    @FXML
    private TextField txtQ;
    @FXML
    private CheckBox chE;
    @FXML
    private CheckBox chS;
    @FXML
    private TableColumn<MouvementStock, Integer> clQte;
    @FXML
    private Label erreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        erreur.setVisible(false);
        txt.setDisable(true);
        txt1.setDisable(true);
        txt2.setDisable(true);
        txt3.setDisable(true);
        
        categories= categorieController.listCategorie();
        produits=produitController.listeProduit();
        entrepots=entrepotController.readAll();
        System.out.println(entrepots);

        ObservableList observableList=FXCollections.observableArrayList(categories);
        ObservableList observableList1=FXCollections.observableArrayList(entrepots);
        System.out.println(observableList1);
        cmbC.setItems(observableList);
        cmbE.setItems(observableList1);
        clLib.setCellValueFactory(new PropertyValueFactory("libelle"));
        clPrix.setCellValueFactory(new PropertyValueFactory("prix"));
        clRef.setCellValueFactory(new PropertyValueFactory("reference"));
        clMarq.setCellValueFactory(new PropertyValueFactory("marque"));
         
         
        //clQte.setCellValueFactory(new PropertyValueFactory("quantite"));
        table.getItems().addAll(produits);
         
         table.setOnMouseClicked(event->{
        //pour modifier un produit il faut faire deux click
              if (event.getClickCount() == 2) {
                  //activer les Buttons 
              
                  ProduitSelectionner = table.getItems().get(table.getSelectionModel().getSelectedIndex());
                  //System.out.println("Produit selectionnner"+ProduitSelectionner);
                  indexProduitSelectionner=table.getSelectionModel().getSelectedIndex();
                  
                  txt.setText(ProduitSelectionner.getLibelle());
                  txt1.setText(String.valueOf(ProduitSelectionner.getPrix()));
                  txt2.setText(String.valueOf(ProduitSelectionner.getReference()));
                  txt3.setText(String.valueOf(ProduitSelectionner.getMarque()));
                  //txtQ.setText(String.valueOf(ProduitSelectionner.getQuantite()));
                 // System.out.println(ProduitSelectionner.getEntrepot());
                  Entrepot entrep = ProduitSelectionner.getEntrepot();
                  cmbC.setValue(ProduitSelectionner.getCategorie());
                  cmbE.setValue(entrep);
                    int qtep=  ProduitSelectionner.getQuantite();
                    System.out.println(qtep);
                    String nom = ProduitSelectionner.getLibelle();
                    System.out.println(nom);
              }
             
                    });
         
    } 
    
    private boolean verif(){
        if (cmbC.getValue() != null && cmbE.getValue() != null && txt !=null && txt1 != null 
                && txt2 != null && txt3 !=null && txtQ != null && date != null) {
            
            return true;
        }
         
        return false;
    }

    @FXML
    private void onClickValider(ActionEvent event) {
        
        MouvementStock mvt = new MouvementStock();
        Integer qte = Integer.parseInt(txtQ.getText());
        Date date1 = Date.valueOf(this.date.getValue());
        String nature = String.valueOf(chE.getText());
        Date dateLoc = Date.valueOf(LocalDate.now());
          
                if(date1.before(dateLoc) )
                        {
                                erreur.setVisible(true);
                        }
                    else
                         { 
                             mvt.setDateMouv(date1);

                           }
        Produit p = new Produit();
        System.out.println(qte);
        Entrepot e = cmbE.getValue();
        mvt.setE(e);
        mvt.setP(ProduitSelectionner);
        mvt.setNatureDuStock(nature);
    
        int id =ProduitSelectionner.getId();
        
     if (ProduitSelectionner.getQuantite() != 0){
               Integer qteAjoutée = qte+ProduitSelectionner.getQuantite();

             produitController.ajouterQte(qteAjoutée, ProduitSelectionner.getId());

         System.out.println("oui");
     
     } else{
         
         System.out.println("non");
        produitController.ajouterQte(qte, ProduitSelectionner.getId());}
     
     
     
        //ProduitSelectionner.setQuantite(qte);
        mouvementController.ajouterMouvement(mvt);
    }
   
}
