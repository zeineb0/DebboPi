/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author ASUS
 */
public class Loader {
    private Pane view ;
    
    public Pane getPage(String nom)
    {
        try
        {
            URL fileURL = esprit.class.getResource("/esprit/"+nom+".fxml");
            view =new FXMLLoader().load(fileURL);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return view;
    
}
}
