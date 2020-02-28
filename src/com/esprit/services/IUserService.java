/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.entities.User;
import java.sql.SQLException;

/**
 *
 * @author Zeineb_yahiaoui
 */
public interface IUserService {
    
    public void changerMdp(User u ,String amdp,String nmdp)throws SQLException;
    public void supprimerUser(User u) throws SQLException;
    
  
}
