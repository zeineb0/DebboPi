/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import java.io.InputStream;

/**
 *
 * @author ahmed
 */
public interface IFlickerService {
    
public String savePhoto(InputStream photo, String title) throws Exception;

}
