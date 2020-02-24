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
import javafx.scene.control.Button;
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
        contrat_service = new ContratService();
        
        Date date1 = Date.valueOf(dateDeb.getValue());
        Date date2 = Date.valueOf(dateFin.getValue());
        
     
       
        
        String np = nomp.getText();
        String[] currencies = np.split(" ");
        
        nom=currencies[0];
        prenom=currencies[1];
                
        
        
        
        
        ContratDetail contrat_detail = new ContratDetail();
        contrat_detail.setDate_debut(date1);
        contrat_detail.setDate_fin(date2);
        contrat_detail.setNom(nom);
        contrat_detail.setPrenom(prenom);
        contrat_detail.setEntreprise(entreprise.getText());
        
        System.out.println(contrat_detail);
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
       
        contrat_service.ajouterContrat(contrat, user, entrepot);
        MailService.SendMail(email,"Hello Test");
                
        Parent gestion_contrat = FXMLLoader.load(getClass().getResource("GestionContrat.fxml"));
        Scene gestionCV= new Scene(gestion_contrat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gestionCV);
        window.show();
        
        
        
 
        
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
