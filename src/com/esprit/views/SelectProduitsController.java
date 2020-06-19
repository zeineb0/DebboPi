/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Panier;
import com.esprit.services.impl.ServicePanier;
import com.esprit.utilities.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author ghazi
 */
public class SelectProduitsController implements Initializable {

    @FXML
    private ComboBox<String> ComboBoxCat;
    
    @FXML
    private ImageView imagePanier;
    @FXML
    private ImageView LogoApp;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label Count;
    private static String listProduits="";
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listProduits="";
         Panier p=new Panier();
        ServicePanier sp= new ServicePanier();
        try {
            p=sp.getPannier(1);
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Count.setText(p.getNbrProduit()+"");
        listProduits=p.getListProduit();
        Image image= new Image("/com.esprit.images/13335.png");
        imagePanier.setImage(image);
        image = new Image("/com.esprit.images/logo-debbo.png");
        LogoApp.setImage(image);
        ComboBoxCat.getItems().add("Toutes categories");
        Connection cnx=DataSource.getInstance().getConnection();
        String req="SELECT * FROM `categories`";
        PreparedStatement pst;
        try {
        pst = cnx.prepareStatement(req);
        ResultSet rst = pst.executeQuery(req);
        String label;
        
        while(rst.next())
        {   label= rst.getString("nom");
        ComboBoxCat.getItems().add(label);
        }
        } catch (SQLException ex) {
        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FlowPane flow= new FlowPane();
        flow.setPrefWidth(675);
        req="SELECT * FROM `produit`";
        
        try {
        pst = cnx.prepareStatement(req);
        ResultSet rst = pst.executeQuery(req);
        String libelle;
        String reference;
        String marque;
        int id_produit;
        Float prix;
        Label label;
        while(rst.next())
        {   id_produit=rst.getInt("id_produit");
            libelle= rst.getString("libelle");
            reference=rst.getString("reference");
            marque=rst.getString("marque");
            prix=rst.getFloat("prix");
             AnchorPane x = new AnchorPane();
             x.setMaxSize(225, 225);
             x.setMinSize(225, 225);
             x.setTranslateY(25);
             x.setTranslateX(25);
        GridPane grp= new GridPane();
         ImageView im = new ImageView("/com.esprit.images/logo-debbo.png");
         im.setFitHeight(50);
         im.setFitWidth(50);
         grp.add(im, 0, 0);
        label= new Label(id_produit+"");
        label.setVisible(false);
        grp.add(label, 0, 0);
        label= new Label(libelle);
        grp.add(label, 0, 50);
        label= new Label(marque);
        grp.add(label, 0, 70);
        label= new Label("ref: "+reference);
        grp.add(label, 0, 90);
        label= new Label(prix+" Dt");
        label.textFillProperty().setValue(Paint.valueOf("green"));
        grp.add(label, 0, 110);
       
        
        TextField number= new TextField();
        number.maxWidth(50);
        number.setText("0");
        number.setEditable(false);
        grp.add(number, 0, 170);
        Button up= new Button("");
        up.minWidth(30);
        up.setOnAction(((event) -> {
           
          number.setText(""+(Integer.parseInt(number.getText())+1)); 
        }));
        grp.add(up, 1, 170);
        Button Down= new Button("");
        Down.minWidth(30);
        Down.setOnAction(((event) -> {
             if(Integer.parseInt(number.getText())>0)
           number.setText(""+(Integer.parseInt(number.getText())-1)); 
        }));
        grp.add(Down, 2, 170); 
        Button btn= new Button("Ajouter au panier");
        btn.setOnAction(((event) -> {
            if(!number.getText().equals("0"))
            {
            int count=getQuantity(Count.getText());
            count++;
            Count.setText(""+count);
            String id_p=grp.getChildren().get(1)+"";
            id_p=id_p.substring(id_p.indexOf("'")+1, id_p.lastIndexOf("'"));
            listProduits+=number.getText()+"."+id_p+"-";
            Panier p2= new Panier( 1, listProduits, count, 'p');
             
        try {
            sp.updatePannier(p2);
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }       
            
            }
            else
            System.out.println(listProduits);
        }));
        grp.add(btn, 0, 200);
        x.getChildren().add(grp);
        flow.getChildren().add(x);
        }
        } catch (SQLException ex) {
        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
        scrollPane.setContent(flow);
    }    

  
    @FXML
    private void AccederPanier(MouseEvent event) throws IOException {
        Parent panier = FXMLLoader.load(getClass().getResource("Panier.fxml"));
        Scene PanierV= new Scene(panier);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(PanierV);
        window.show();
    }

    public int getQuantity(String ch)
    {
           
            int count=Integer.parseInt(ch);
            
            return count;
    }
    @FXML
    private void ChangeValue(ActionEvent event) {
           Connection cnx=DataSource.getInstance().getConnection();
           PreparedStatement pst;
           int id=-1;
            FlowPane flow= new FlowPane();
            String req;
        flow.setPrefWidth(675);
        if(!ComboBoxCat.getValue().equals("Toutes categories"))
        {req="SELECT id_categorie FROM `categories` where nom='"+ComboBoxCat.getValue()+"'";
        
        try {
        pst = cnx.prepareStatement(req);
        ResultSet rst = pst.executeQuery(req);
        
        while(rst.next())
        {   id= rst.getInt("id_categorie");
           
        }
        } catch (SQLException ex) {
        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        if(!ComboBoxCat.getValue().equals("Toutes categories"))
        {req="SELECT * FROM `produit` where FK_id_categorie='"+id+"'";}
        else
        {req="SELECT * FROM `produit`";}
        try {
        pst = cnx.prepareStatement(req);
        ResultSet rst = pst.executeQuery(req);
        String libelle;
        String reference;
        String marque;
        int id_produit;
        Float prix;
        Label label;
        while(rst.next())
        {   id_produit=rst.getInt("id_produit");
            libelle= rst.getString("libelle");
            reference=rst.getString("reference");
            marque=rst.getString("marque");
            prix=rst.getFloat("prix");
             AnchorPane x = new AnchorPane();
             x.setMaxSize(225, 225);
             x.setMinSize(225, 225);
             x.setTranslateY(25);
             x.setTranslateX(25);
        GridPane grp= new GridPane();
        ImageView im = new ImageView("/com.esprit.images/logo-debbo.png");
        im.setFitHeight(50);
        im.setFitWidth(50);
        grp.add(im, 0, 0);
        label= new Label(id_produit+"");
        label.setVisible(false);
        grp.add(label, 0, 0);
        label= new Label(libelle);
        grp.add(label, 0, 50);
         label= new Label(marque);
        grp.add(label, 0, 70);
        label= new Label("ref: "+reference);
        grp.add(label, 0, 90);
        label= new Label(prix+" Dt");
        label.textFillProperty().setValue(Paint.valueOf("green"));
        grp.add(label, 0, 110);
       
        TextField number= new TextField();
        number.maxWidth(50);
        number.setText("0");
        number.setEditable(false);
        grp.add(number, 0, 170);
        Button up= new Button("");
        up.minWidth(30);
        up.setOnAction(((event2) -> {
           
          number.setText(""+(Integer.parseInt(number.getText())+1)); 
        }));
        grp.add(up, 1, 170);
        Button Down= new Button("");
        Down.minWidth(30);
        Down.setOnAction(((event2) -> {
             if(Integer.parseInt(number.getText())>0)
           number.setText(""+(Integer.parseInt(number.getText())-1)); 
        }));
        grp.add(Down, 2, 170); 
        Button btn= new Button("Ajouter au panier");
        btn.setOnAction(((event2) -> {
            if(!number.getText().equals("0"))
            {
            int count=getQuantity(Count.getText());
            count++;
            Count.setText(""+count);
            String id_p=grp.getChildren().get(1)+"";
            id_p=id_p.substring(id_p.indexOf("'")+1, id_p.lastIndexOf("'"));
            listProduits+=number.getText()+"."+id_p+"-";
            System.out.println(listProduits);
            }
            else
            System.out.println(listProduits);
        }));
        grp.add(btn, 0, 200);
        x.getChildren().add(grp);
        flow.getChildren().add(x);
        }
        } catch (SQLException ex) {
        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
        scrollPane.setContent(flow);
    }
    
   

                  
}