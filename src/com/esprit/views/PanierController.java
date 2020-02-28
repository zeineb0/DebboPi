/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Commande;
import com.esprit.entities.ListPanier;
import com.esprit.entities.Panier;
import com.esprit.services.impl.ServiceCommande;
import com.esprit.services.impl.ServicePanier;
import com.esprit.utilities.DataSource;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ghazi
 */
public class PanierController implements Initializable {

    @FXML
    private TableView<ListPanier> TablePanier;
    @FXML
    private TableColumn<ListPanier, String> ColNomProd;
    @FXML
    private TableColumn<ListPanier, Integer> ColQuantite;
    @FXML
    private TableColumn<ListPanier, Float> colPrixU;
    @FXML
    private TableColumn<ListPanier, Float> ColPrix;
    @FXML
    private TextField BoxTotal;
    @FXML
    private Label vp;
    ObservableList<ListPanier> data_list;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Float prixTotal=0f;
        TablePanier.getItems().clear();
        ArrayList <ListPanier> list_Produit= new ArrayList<>();
        ListPanier e=null;
        ServicePanier sp= new ServicePanier();
        
        try {
            Panier p=sp.getPannier(1);
            String listProduit=p.getListProduit();
            for(int i=0;i<p.getNbrProduit();i++)
            {   String ch= listProduit.substring(0,listProduit.indexOf("."));
                listProduit= listProduit.substring(listProduit.indexOf(".")+1);
                String ch2= listProduit.substring(0,listProduit.indexOf("-"));
                listProduit= listProduit.substring(listProduit.indexOf("-")+1);
                System.out.println(ch);
                System.out.println(ch2);
              String  req="SELECT libelle,prix FROM `produit` WHERE id_produit="+ch2;
        
         Connection cnx=DataSource.getInstance().getConnection();
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rst = pst.executeQuery(req);
        String libelle;
       
        Float prix;
        int x=0;
        while(rst.next())
        {   System.out.println(x++);
            libelle= rst.getString("libelle");
            prix=rst.getFloat("prix");
            list_Produit.add(e=new ListPanier(libelle, Integer.parseInt(ch),prix, Float.parseFloat(ch)*prix));
            prixTotal+=Float.parseFloat(ch)*prix;
           
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
         data_list=FXCollections.observableArrayList(list_Produit);
            ColNomProd.setCellValueFactory(new PropertyValueFactory<>("nom_Produit"));
            ColQuantite.setCellValueFactory(new PropertyValueFactory<>("quantité"));
            colPrixU.setCellValueFactory(new PropertyValueFactory<>("prixUnité"));
            ColPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        TablePanier.setItems(data_list);
        vp.fontProperty().setValue(new Font(14));
        BoxTotal.setText(prixTotal+"");
    }       

    @FXML
    private void PasserCommande(MouseEvent event) throws SQLException{
        ServicePanier sp= new ServicePanier();
        ServiceCommande sc= new ServiceCommande();
        Panier p=sp.getPannier(1);
        Date d= new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        Date dexp= new Date(LocalDate.now().plusDays(30).getYear(), LocalDate.now().plusDays(30).getMonthValue(), LocalDate.now().plusDays(30).getDayOfMonth());
        Commande c=new Commande(d,dexp, p.getListProduit(),Float.parseFloat(BoxTotal.getText()) , "carte", p.getId_client(),p.getNbrProduit());
       sc.addCommande(c);
       p.setEtatPanier('v');
       p.setListProduit("");
       p.setNbrProduit(0);
       sp.updatePannier(p);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent panier = FXMLLoader.load(getClass().getResource("selectProduits.fxml"));
        Scene PanierV= new Scene(panier);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(PanierV);
        window.show();
    }
    
}
