/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.entities.Entrepot;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Zeineb_yahiaoui
 */
public interface IEntrepotService {
 public void ajouter(Entrepot e) throws SQLException;
 public void delete(int nb) throws SQLException;
 public void update(Entrepot e) throws SQLException ;
 public List<Entrepot> readAll() ;
 
}
