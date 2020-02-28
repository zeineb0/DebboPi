/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Contrat;
import com.esprit.entities.ContratDetail;
import com.esprit.entities.Entrepot;
import com.esprit.entities.Utilisateur;
import com.esprit.services.impl.ContratService;
import com.esprit.services.impl.MailService;
import com.esprit.utilities.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AjouterContratController implements Initializable {

    @FXML
    private Button addC;
    @FXML
    private Button gestC;
    
    private String nom;
    private String prenom;
    @FXML
    private TextField entreprise;
    @FXML
    private DatePicker dateDeb;
    @FXML
    private DatePicker dateFin;
    
    private ContratService contrat_service;
    
    private int id, id2;
    private String email;
    private List<String> nom_prenom ;
    @FXML
    private TextField nomp;
    private Date date1,date2;
    
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        contrat_service=new ContratService();
        
        nom_prenom=contrat_service.getNomPrenom();
        
        System.out.println(nom_prenom);
        
    
        TextFields.bindAutoCompletion(nomp, nom_prenom);
           
           
           
        
        
        
        
    }    
    
    

    @FXML
    private void onClickAdd(ActionEvent event) throws SQLException, IOException, Exception {

        
         if(nomp.getText().equals("") || entreprise.getText().equals("") || dateDeb.getValue() == null || dateFin.getValue() == null )
        {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" un ou plusieurs champs sont vides ");
            alert.setContentText(" vous devez remplir tous les champs ");
            alert.setHeaderText(null);
            alert.showAndWait();
            
            System.out.println("feragh ");
        }
         else
         {
             
            contrat_service = new ContratService();
//        
            date1 = Date.valueOf(dateDeb.getValue());
            date2 = Date.valueOf(dateFin.getValue());

            ContratDetail contrat_detail = new ContratDetail();
            contrat_detail.setDate_debut(date1);
            contrat_detail.setDate_fin(date2);
            contrat_detail.setNom(nom);
            contrat_detail.setPrenom(prenom);
            contrat_detail.setEntreprise(entreprise.getText());
        
            System.out.println(contrat_detail);
            
            String np = nomp.getText();
            String[] currencies = np.split(" ");
            
            if(currencies.length<2)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Les champs ne sont pas corrects");
                alert.setContentText(" vous devez saisir le nom et le prenom ");
                alert.setHeaderText(null);
                alert.showAndWait();
            }else
            {
                nom=currencies[0];
                prenom=currencies[1];
            
        
           
            
            
                    
            try
            {
            String req = "select id_user , nom , prenom  from utilisateur where nom ='"+nom+"' and prenom ='"+prenom+"' ";
            Statement s=DataSource.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);

            while(rs.next())
                 {
                    id = rs.getInt("id_user");
                    System.out.println(id);        
                 }
            }   catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            try
            {
            String req2 = "select id_entrepot from entrepot where entreprise='"+entreprise.getText()+"'";
            Statement s=DataSource.getInstance().getConnection().createStatement();
            ResultSet rs2=s.executeQuery(req2);
            
            while (rs2.next())
            {
                id2 = rs2.getInt("id_entrepot");
                System.out.println(id2);
            }
            
            }catch(SQLException ex)
            {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
            try
            {
            String req = "select email  from utilisateur where nom ='"+nom+"' and prenom ='"+prenom+"' ";
            Statement s=DataSource.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);

            while(rs.next())
                 {
                    email = rs.getString("email");
                    System.out.println(email);        
                 }
            }   catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
            }
       
      
            Contrat contrat = new Contrat(date1, date2);
            Utilisateur user = new Utilisateur();
            user.setId(id);
            Entrepot entrepot = new Entrepot();
            entrepot.setId_entrepot(id2);
        
        
     
    
            if(date2.before(date1))
            {
            System.out.println(" les valeurs des dates fausses");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Les champs ne sont pas corrects");
            alert.setContentText(" la Date du fin est inférieure à la date du début");
            alert.setHeaderText(null);
            alert.showAndWait();
            }
            else
            {
                
                
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de l'ajout du contrat");
                alert.setContentText(" Vous etes sur de vouloir ajouter le contrat");
                alert.setHeaderText(null);
        
        
                Optional<ButtonType> resultat = alert.showAndWait();    
                 if(resultat.get()== ButtonType.OK)
                {
                    contrat_service.ajouterContrat(contrat, user, entrepot);
                    MailService.SendMail(email,"Hello Test");
                      
                    Parent gestion_contrat = FXMLLoader.load(getClass().getResource("GestionContrat.fxml"));
                    Scene gestionCV= new Scene(gestion_contrat);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(gestionCV);
                    window.show();
                    
                    
                }
                 else
                {
                    System.out.println("cancel");
                }   
                
                
                
                
            
            
            
            }
             
             
             
             
            System.out.println("m3obin");
            }
         }
        
     
       
        
        
                
        
        
        
        

       
        
      
        
        
        
 
        
    }

    @FXML
    private void onClickGestion(ActionEvent event) throws IOException {
        
        Parent gestion_contrat = FXMLLoader.load(getClass().getResource("GestionContrat.fxml"));
        Scene gestionCV= new Scene(gestion_contrat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gestionCV);
        window.show();
        
    }

    
}
