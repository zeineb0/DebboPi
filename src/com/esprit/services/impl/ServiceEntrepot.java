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

    private final Connection con;
    private Statement ste;
    
    public ServiceEntrepot() {
    con = DataSource.getInstance().getConnection();

    }

    
    @Override
    public void ajouter(Entrepot e) throws SQLException {
        
    PreparedStatement pre=con.prepareStatement("INSERT INTO `entrepot` (`adresse`, `num_fiscale`, `quantite_max`, `etat`, `entreprise`) VALUES ( ?, ?, ?, ?, ?);");
    pre.setString(1, e.getAdresse_entrepot());
    pre.setInt(2, e.getNum_fiscale());
    pre.setInt(3, e.getQuantite_max());
    pre.setString(4, e.getEtat());
    pre.setString(5, e.getEntreprise());
    pre.executeUpdate(); 
    
    }

    @Override
    public boolean delete(int nb) throws SQLException {
       PreparedStatement pre = con.prepareStatement("DELETE FROM `entrepot` WHERE `id_entrepot`= ?");
       pre.setInt(1, nb);
       pre.executeUpdate();
        return false;
       

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
