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
 * @author asus
 */
public interface IServiceLocation <L> {
     void ajouter(L l) throws SQLException;
    void delete(int nb) throws SQLException;
    void update(L l) throws SQLException;
    List<L> readAll() throws SQLException;
}
