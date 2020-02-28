/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.entities.Panier;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ghazi
 */
public interface IServicePanier {
    public void addPannier(Panier p) throws SQLException;
    public List<Panier> getAllPanier() throws SQLException;
    public void updatePannier(Panier p) throws SQLException;
    public void deletePannier(Panier p) throws SQLException;
}
