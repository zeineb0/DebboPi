/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.entities.Contrat;
import com.esprit.entities.ContratDetail;
import com.esprit.entities.Entrepot;
import com.esprit.entities.Livraison;
import com.esprit.entities.User;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface IContratService {
    
    public void ajouterContrat (Contrat contrat,User user,Entrepot entrepot);
    public void supprimerContrat (ContratDetail contrat);
    public void modifierContrat(ContratDetail contrat,Date date_debut , Date date_fin);
    public List<ContratDetail> afficherContratEntrepot(Entrepot entrepot);
    public List<ContratDetail> afficherContratTransporteur(User user);
    public void modifierEtatLivraison(Livraison livraison);
    public List<User> afficherTransporteurLibre();
    public void accepterLivraison(Livraison livraison);
    public void refuserLivraison(Livraison livraison);
    public List<Livraison> afficherLivraisonParTransporteurNonLivree(User u);
    public List<Livraison> afficherLivraisonParTransporteurLivree(User u);
    
    
}
