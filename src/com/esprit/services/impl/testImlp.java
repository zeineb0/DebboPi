/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Entrepot;
import com.esprit.services.test;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class testImlp implements test <Entrepot> {
    
    private Connection con;
    private Statement ste;

    public testImlp() {
        con = DataSource.getInstance().getConnection();

    }
    

    @Override
    public void ajouter(Entrepot e) throws SQLException {
        ste = con.createStatement();
        //String requeteInsert = ;
        //ste.executeUpdate(requeteInsert);
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
