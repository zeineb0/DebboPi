/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.entities.Categorie;
import com.esprit.entities.Produit;
import com.esprit.services.ICategorieService;
import com.esprit.utilities.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zeineb_yahiaoui
 */
public class CategorieController implements ICategorieService
{
Connection conn = DataSource.getInstance().getConnection();
    private ResultSet rs;
    private PreparedStatement ps;
    
    @Override
    public List<Categorie> listCategorie() {
        List<Categorie> categories = new ArrayList<>();
        String req = "SELECT * FROM `categorie` WHERE 1";
        
        try {
            ps = conn.prepareStatement(req);
            rs = ps.executeQuery();
               while (rs.next()) 
               {
                   Categorie c =new Categorie();
                   c.setId(rs.getInt("id"));
                   c.setNom(rs.getString("nom"));
                   categories.add(c);
               }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;

    }

    @Override
    public void ajouterProduit(Categorie c) throws SQLException {

        String req = "INSERT INTO `categories`( `nom`) VALUES (?,?)";
        ps=conn.prepareStatement(req);
        try{
        ps.setString(1,c.getNom());
        ps.execute();}
        catch (SQLException ex)
        {}    }

    @Override
    public void supprimerProduit(Categorie c) throws SQLException {

        String req= "DELETE FROM `categories` WHERE `id_categorie`=?";
        ps=conn.prepareStatement(req);
        try{
        ps.setInt(1,c.getId());
        ps.execute();}
        catch (SQLException ex)
        {}    }
        

    

    @Override
    public Categorie consulterProduit(Categorie c) throws SQLException {
        String req="SELECT * FROM `categories` WHERE `id_categorie`=?";
        ps=conn.prepareStatement(req);
        try{
        ps.setInt(1,c.getId());
        ps.execute();}
        catch (SQLException ex)
        {}    
    return c;
    }

    @Override
    public Categorie modiferProduit(Categorie c) throws SQLException {
        String req = "UPDATE `categories` SET `id_categorie`=?,`nom`=? WHERE 1";
        ps=conn.prepareStatement(req);
        try{
        ps.setInt(1,c.getId());
        ps.setString(2,c.getNom());
        ps.execute();}
        catch (SQLException ex)
        {}    
    return c;
                
                
                
                
                
                
                }
    
    
}
