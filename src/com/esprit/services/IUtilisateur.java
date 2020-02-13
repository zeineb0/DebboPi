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
 * @author Dell
 * @param <U>
 */
public interface IUtilisateur<U> {
    
    void ajouter(U user) throws SQLException;
    void ajouter1(U user) throws SQLException;
    boolean delete(U user) throws SQLException;
    boolean update(U user) throws SQLException;
    List<U> readAll() throws SQLException;
    
    
    
}
