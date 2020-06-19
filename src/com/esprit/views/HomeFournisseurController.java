/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import static com.esprit.entities.Session.getIdSession;
import com.esprit.utilities.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HomeFournisseurController implements Initializable {

    private BorderPane trial;
        @FXML
    private AnchorPane anchorLoad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
        
 try {
            Parent pane= FXMLLoader.load(getClass().getResource("gererLocation.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            setLabelProfileFournisseur();
        } catch (SQLException ex) {
            Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }    
     Connection con = DataSource.getInstance().getConnection();
    String iddd = Integer.toString(getIdSession());
    
    @FXML 
    private Label labelprofile;
    
    public void setLabelProfileFournisseur() throws SQLException{
         String fullName="";
       
        String request0 ="SELECT * from `utilisateur` WHERE `utilisateur`.`id_user` = "+iddd+";";
        java.sql.PreparedStatement ps0 = con.prepareStatement(request0);
        ResultSet rs0 = ps0.executeQuery();

        if (rs0.next()) {
            String a = rs0.getString("nom");
            String b = rs0.getString("prenom");
            fullName = b+" "+a;
        }
        System.out.println(iddd);
                     labelprofile.setText(fullName); 
    }
    
    @FXML
    void logOut(MouseEvent event){
       
            
            try {
               Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
 

            
            
            
            
}
    
    
                void goToFatma(MouseEvent event) {
        
                                 try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("gererEntrepot.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.print(ex.getMessage());
                }
                 
    }
    
    
    
         @FXML
      void goToProfile(MouseEvent event){
         try {
               Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Profile.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
     }

    @FXML
    private void loadStat(ActionEvent event) {
       
 try {
            Parent pane= FXMLLoader.load(getClass().getResource("Statistiques.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void loadLivraison(ActionEvent event) {
      
        
 try {
            Parent pane= FXMLLoader.load(getClass().getResource("Affichage.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void LoadEntropot(ActionEvent event) {
      
 try {
            Parent pane= FXMLLoader.load(getClass().getResource("gererEntrepot.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void loadLocation(ActionEvent event) {
      
        
 try {
            Parent pane= FXMLLoader.load(getClass().getResource("gererLocation.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void loadContrat(ActionEvent event) {
     
       
 try {
            Parent pane= FXMLLoader.load(getClass().getResource("GestionContrat.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void addContrat()
    {
        
         
 try {
            Parent pane= FXMLLoader.load(getClass().getResource("AjouterContrat.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
   

    @FXML
    private void categorie(ActionEvent  event) {
       
 try {
            Parent pane= FXMLLoader.load(getClass().getResource("ListeCategorie.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
    @FXML
    void mvtStock(ActionEvent event) {
   
 try {
            Parent pane= FXMLLoader.load(getClass().getResource("MvtStock.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    void produit(ActionEvent event) {
     
 try {
            Parent pane= FXMLLoader.load(getClass().getResource("ListeProduit.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void LoadAjoutE(ActionEvent event) {
        try {
            Parent pane= FXMLLoader.load(getClass().getResource("ajouterEntrepot.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadAjoutLocation(ActionEvent event) {
        try {
            Parent pane= FXMLLoader.load(getClass().getResource("ajouterLocation.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadListeLocation(ActionEvent event) {
        try {
            Parent pane= FXMLLoader.load(getClass().getResource("listeDesLocation.fxml"));
               System.out.println("nice");
           anchorLoad.getChildren().setAll(pane);
       } catch (IOException ex) {
           Logger.getLogger(HomeFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
