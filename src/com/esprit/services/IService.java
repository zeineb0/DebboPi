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
 * @author Zeineb_yahiaoui
 * @param <L>
 */
public interface IService<L> {
    void ajouter(L l) throws SQLException;
    boolean delete(L l) throws SQLException;
    boolean update(L l) throws SQLException;
    List<L> readAll() throws SQLException;
}