/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Utilisateur;
import com.esprit.services.IUtilisateur;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Dell
 */
public class UtilisateurImpl implements IUtilisateur<Utilisateur> {
    
    
     private Connection con;
    private Statement ste;

    public UtilisateurImpl() {
        con = DataSource.getInstance().getConnection();

    }

    @Override
    public void ajouter(Utilisateur user) throws SQLException {
        
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `utilisateur`(`id_user`, `nom`, `prenom`, `cin`, `type`) VALUES ('"+user.getId()+"', '" + user.getNom() + "', '" 
                + user.getPrenom() + "', '" + user.getCin() + "','" + user.getType()+"');";
       ste.executeUpdate(requeteInsert);
    }

    @Override
    public void ajouter1(Utilisateur user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Utilisateur user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Utilisateur user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
