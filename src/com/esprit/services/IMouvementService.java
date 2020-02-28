/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.entities.MouvementStock;

/**
 *
 * @author Zeineb_yahiaoui
 */
public interface IMouvementService {
 
    public void ajouterMouvement (MouvementStock m);
    public void modifierMouvement (MouvementStock m);
    public void supprimerMouvement (MouvementStock m);
    public void consulterMouvement (String nature);

}
