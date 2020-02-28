/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS X550V
 */
public interface IServiceLivraison <L>{
 
    void ajouter(L l) throws SQLException;
    boolean delete(int nb) throws SQLException;
    boolean update(L l) throws SQLException;
    List<L> readAll() throws SQLException;
    List<L> readLivree() throws SQLException;
    List<L> readAnnulle() throws SQLException;
    List<L> readNonLivree() throws SQLException;
}
   

