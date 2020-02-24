/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.entities.Contrat;
import com.esprit.entities.ContratDetail;
import com.esprit.entities.Entrepot;
import com.esprit.entities.Livraison;
import com.esprit.entities.Utilisateur;
import com.esprit.services.impl.ContratService;
import com.esprit.services.impl.MailService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class esprit extends Application  {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AjouterContrat.fxml"));
        
        
        ContratService contrat_service = new ContratService();
        
        Entrepot entrepot1 = new Entrepot();
        entrepot1.setId_entrepot(1);
        Utilisateur transporteur1 = new Utilisateur();
        transporteur1.setId(1);
        
        //Contrat contrat = new Contrat( new java.sql.Date(116, 2, 20), new java.sql.Date(118, 2, 20), 1, 1);
        
      //  contrat_service.ajouterContrat(contrat, transporteur1, entrepot1);
        
//        contrat_service.supprimerContrat(contrat);

//        contrat_service.modifierContrat(contrat);

                 List<Contrat> contrat_list = new ArrayList<>();
                 List<ContratDetail> contrat_detail =new ArrayList<>();

         // contrat_list=contrat_service.afficherContrat();
               //   contrat_list.stream().forEach(System.out::println);
          
              //    List<Livraison> livraison_list = new ArrayList<>();
                 // livraison_list=contrat_service.afficherLivraisonParTransporteur(transporteur1);
           //       System.out.println("#########################");
             //     livraison_list.stream().forEach(System.out::println);
                  System.out.println("****");
//                  
//                  contrat_detail=contrat_service.afficherContrat();
//                  contrat_detail.stream().forEach(System.out::println);

                    MailService.SendMail("benakachamohamedfarouk@gmail.com","Hello Test");
                  
                  
                  
               //   Livraison livraison = new Livraison();
                //  livraison.setId_livraison(1);
                  
                 // contrat_service.accepterLivraison(livraison);
                //  contrat_service.accepterLivraison(livraison);
                  //contrat_service.modifierEtatLivraison(livraison, "livrée");
                  
                  
                  
                  
                  

        
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     launch(args);
        
    }
    
}
