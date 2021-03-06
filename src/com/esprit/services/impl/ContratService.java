/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.services.IContratService;
import com.esprit.entities.Contrat;
import com.esprit.entities.ContratDetail;
import com.esprit.entities.Entrepot;
import com.esprit.entities.Livraison;
import com.esprit.entities.User;
import com.esprit.utilities.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class ContratService implements IContratService {
    
    
    
    @Override
    public void ajouterContrat (Contrat contrat,User user,Entrepot entrepot) 
    {
        
            String req="INSERT INTO `dbwebfinale`.`contrat` (`date_deb`, `date_fin`, `FK_id_user`, `FK_id_entrepot`,salaire) VALUES (?, ?, ?, ?,1600);";
            PreparedStatement ps;
        try {
            
            
            ps = DataSource.getInstance().getConnection().prepareStatement(req);
            ps.setDate(1,contrat.getDate_debut() );
            ps.setDate(2,contrat.getDate_fin());
            ps.setInt(3,user.getId());
            ps.setInt(4,entrepot.getId_entrepot());
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    
    }
    
    
    
    
    @Override
    public void supprimerContrat (ContratDetail contrat)
    {
        try {
            String req="DELETE FROM contrat WHERE FK_id_user=? and FK_id_entrepot=?";
            PreparedStatement ps=DataSource.getInstance().getConnection().prepareStatement(req);
            ps.setInt(1,contrat.getId_user());
            ps.setInt(2,contrat.getId_entrepot());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void modifierContrat(ContratDetail contrat , Date date_debut , Date date_fin)
    {
        try {

            String req="UPDATE contrat SET date_deb = ?, date_fin = ? WHERE contrat.`FK_id_user` = ? and contrat.`FK_id_entrepot` = ?";
            PreparedStatement ps= DataSource.getInstance().getConnection().prepareStatement(req);
            
            ps.setDate(1,date_debut);
            ps.setDate(2,date_fin);
            ps.setInt(3,contrat.getId_user());
            ps.setInt(4,contrat.getId_entrepot());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public List<ContratDetail> afficherContratEntrepot(Entrepot entrepot)
    {
       
        List<ContratDetail> contrat_list = new ArrayList<>();
       
       
        try {
            System.out.println(entrepot.getId_entrepot());
            String req="SELECT  date_deb , date_fin , u.id_user, u.nom , u.prenom , e.id_entrepot , e.entreprise FROM `contrat` c INNER JOIN utilisateur u on u.id_user=c.FK_id_user INNER JOIN entrepot e on e.id_entrepot=c.FK_id_entrepot";
            Statement s=DataSource.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            ContratDetail contrat_detail = new ContratDetail();
            //contrat_detail.setId_contrat(rs.getInt("id_contrat"));
            
            contrat_detail.setDate_debut(rs.getDate("date_deb"));
            contrat_detail.setDate_fin(rs.getDate("date_fin"));
            contrat_detail.setId_user(rs.getInt("id_user"));
            contrat_detail.setNom(rs.getString("nom"));
            contrat_detail.setPrenom(rs.getString("prenom"));
            contrat_detail.setId_entrepot(rs.getInt("id_entrepot"));
            contrat_detail.setEntreprise(rs.getString("entreprise"));
            
            
            
            
            
                contrat_list.add(contrat_detail);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contrat_list;
    }
    
     public List<ContratDetail> afficherContratTransporteur(User user)
     {
          List<ContratDetail> contrat_list = new ArrayList<>();
       
       
        try {
            String req="SELECT date_deb , date_fin , u.id_user, u.nom , u.prenom , e.id_entrepot , e.entreprise FROM `contrat` c INNER JOIN utilisateur u on u.id_user=c.FK_id_user INNER JOIN entrepot e on c.FK_id_entrepot=e.id_entrepot where c.FK_id_user='"+user.getId()+"'";
            Statement s=DataSource.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            ContratDetail contrat_detail = new ContratDetail();
            //contrat_detail.setId_contrat(rs.getInt("id_contrat"));
            
            contrat_detail.setDate_debut(rs.getDate("date_deb"));
            contrat_detail.setDate_fin(rs.getDate("date_fin"));
            contrat_detail.setId_user(rs.getInt("id_user"));
            contrat_detail.setNom(rs.getString("nom"));
            contrat_detail.setPrenom(rs.getString("prenom"));
            contrat_detail.setId_entrepot(rs.getInt("id_entrepot"));
            contrat_detail.setEntreprise(rs.getString("entreprise"));
            
            
            
            
            
                contrat_list.add(contrat_detail);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contrat_list;
         
         
     }
    
    
    
    
    
    @Override
     public void modifierEtatLivraison(Livraison livraison)
    {
        try {

            String req="UPDATE livraison SET etat_livraison = ? WHERE livraison.`id_livraison` = ?";
            PreparedStatement ps= DataSource.getInstance().getConnection().prepareStatement(req);
            
            ps.setString(1,"livrée");
            ps.setInt(2,livraison.getId_livraison());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     /*
    @Override
     public List<User> afficherTransporteurLibre()
    {
       
        List<User> transporteur_list = new ArrayList<>();
       
       
        try {
            String req="SELECT * FROM utilisateur where role='TransporteurLibre'";
            Statement s=DataSource.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            User transporteur = new User();
            transporteur.setId(rs.getInt("id_user"));
            transporteur.setNom(rs.getString("nom"));
            transporteur.setPrenom(rs.getString("prenom"));
            transporteur.setCin(rs.getLong("cin"));
            transporteur.setDate(rs.getDate("date"));
            transporteur.setRole(rs.getString("role"));
            transporteur.setTelephone(rs.getLong("tel"));
            transporteur.setEmail(rs.getString("email"));
            transporteur.setPassword(rs.getString("password"));
            transporteur.setDisponibilite(rs.getInt("disponniblite"));
            transporteur.setNombre_max_commande(rs.getInt("nbr_maxComm"));
                   
            if(transporteur.getDisponibilite()<transporteur.getNombre_max_commande())
                   transporteur_list.add(transporteur);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transporteur_list;
    }
     */
     
     
    @Override
    public void accepterLivraison(Livraison livraison)
    {
        try {

            String req="UPDATE livraison SET acceptation = ? WHERE livraison.`id_livraison` = ?";
            PreparedStatement ps= DataSource.getInstance().getConnection().prepareStatement(req);
            
            ps.setString(1,"acceptée");
            ps.setInt(2,livraison.getId_livraison());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void refuserLivraison(Livraison livraison)
    {
        try {

            String req="UPDATE livraison SET acceptation = ? WHERE livraison.`id_livraison` = ?";
            PreparedStatement ps= DataSource.getInstance().getConnection().prepareStatement(req);
            
            ps.setString(1,"non acceptée");
            ps.setInt(2,livraison.getId_livraison());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
     public List<Livraison> afficherLivraisonParTransporteurNonLivree(User u)
    {
       
        List<Livraison> livraison_list = new ArrayList<>();
       
       
        try {
            String req="SELECT * FROM livraison where FK_id_user='"+u.getId()+"' and etat_livraison=\"non livrée\" ";
            Statement s=DataSource.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Livraison livraison = new Livraison();
            livraison.setId_livraison(rs.getInt("id_livraison"));
            livraison.setDate_livraison(rs.getDate("date_livraison"));
            livraison.setAdresse_livraison(rs.getString("adresse_livraison"));
            livraison.setEtat_livraison(rs.getString("etat_livraison"));
            livraison.setAcceptation(rs.getString("acceptation"));
            livraison.setFK_id_commande(rs.getInt("FK_id_commande"));
            livraison.setFK_id_user(rs.getInt("FK_id_user"));
                   
                   livraison_list.add(livraison);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livraison_list;
    }
     
     
     
     
     public List<Livraison> afficherLivraisonParTransporteurLivree(User u)
    {
       
        List<Livraison> livraison_list = new ArrayList<>();
       
       
        try {
            String req="SELECT * FROM livraison where FK_id_user='"+u.getId()+"' and etat_livraison=\"livrée\"";
            Statement s=DataSource.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Livraison livraison = new Livraison();
            livraison.setId_livraison(rs.getInt("id_livraison"));
            livraison.setDate_livraison(rs.getDate("date_livraison"));
            livraison.setAdresse_livraison(rs.getString("adresse_livraison"));
            livraison.setEtat_livraison(rs.getString("etat_livraison"));
            livraison.setAcceptation(rs.getString("acceptation"));
            livraison.setFK_id_commande(rs.getInt("FK_id_commande"));
            livraison.setFK_id_user(rs.getInt("FK_id_user"));
                   
                   livraison_list.add(livraison);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livraison_list;
    }
     
     
     
     public List<String> getNomPrenom(){
        List<String> maliste = new ArrayList<String>();
        try {
            String req="SELECT nom , prenom  FROM utilisateur where role='TransporteurLibre' ";
            Statement s=DataSource.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
          
           maliste.add(rs.getString("nom")+" "+rs.getString("prenom"));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }

    @Override
    public List<User> afficherTransporteurLibre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
        
    }
    
    

