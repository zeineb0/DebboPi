/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.IServices;

import com.esprit.entities.Contrat;
import com.esprit.entities.ContratDetail;
import com.esprit.entities.Entrepot;
import com.esprit.entities.Livraison;
import com.esprit.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface IContratService {
    
    public void ajouterContrat (Contrat contrat,Utilisateur user,Entrepot entrepot);
    public void supprimerContrat (ContratDetail contrat);
    public void modifierContrat(Contrat contrat);
    public List<ContratDetail> afficherContrat();
    public List<ContratDetail> afficherContratTransporteur(Utilisateur user);
    public void modifierEtatLivraison(Livraison livraison);
    public List<Utilisateur> afficherTransporteurLibre();
    public void accepterLivraison(Livraison livraison);
    public void refuserLivraison(Livraison livraison);
    public List<Livraison> afficherLivraisonParTransporteurNonLivree(Utilisateur u);
    public List<Livraison> afficherLivraisonParTransporteurLivree(Utilisateur u);
    
    
}
