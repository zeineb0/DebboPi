/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esprit.services.impl;


import com.esprit.entities.Entrepot;
import com.esprit.services.IService;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author asus
 */
public class ServiceEntrepot implements IService<Entrepot> {

    private Connection con;
    private Statement ste;
    
    public ServiceEntrepot() {
    con = DataSource.getInstance().getConnection();

    }

    
    @Override
    public void ajouter(Entrepot e) throws SQLException {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `entrepot` ( `nom_entrepot`, `adresse_entrepot`, `num_fiscale`, `quantite_max`) VALUES ( ?, ?, ? ,?);");
    pre.setString(1, e.getNom_entrepot());
    pre.setString(2, e.getAdresse_entrepot());
    pre.setInt(3, e.getNum_fiscale());
    pre.setInt(4, e.getQuantite_max());
    pre.executeUpdate();
    }

    @Override
    public boolean delete(Entrepot e) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Entrepot e) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Entrepot> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
