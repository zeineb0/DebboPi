/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author ahmed
 */
public class DataSource {
    
    private static DataSource data;
    private String url = "jdbc:mysql://localhost:3306/debbofinale";

    private String username = "root";
    private String pasword = "";
    private Connection con;

    public DataSource() {
        try {
            con = (Connection)DriverManager.getConnection(url, username, pasword);
            System.out.println("connexion etablie");

        } catch (SQLException e) {

            System.out.println("Erreur de Connexion ");
            System.out.println(e);
        }
    }
  
    public static Connection conDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/debbov1", "root", "");
            return con ;
        } catch (Exception ex) {
           return null;
        }
        
    }
    
    public Connection getConnection() {
        return con;

    }

    public static DataSource getInstance() {
        if (data == null) {
            data = new DataSource();
        }
        return data;
    }
    
}
