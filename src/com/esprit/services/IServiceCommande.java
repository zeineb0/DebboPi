/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.entities.Commande;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ghazi
 */
public interface IServiceCommande {
    public void addCommande(Commande c) throws SQLException;
    public List<Commande> getAllCommande() throws SQLException;
    public void updateCommande(Commande c) throws SQLException;
    public void deleteCommande(Commande c) throws SQLException;
}
