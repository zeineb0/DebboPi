/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth1Token;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.esprit.services.IFlickerService;

/**
 *
 * @author ahmed
 */
public class FlickerServiceImpl implements IFlickerService {

    private Flickr flickr;

    private UploadMetaData uploadMetaData = new UploadMetaData();

    private String apiKey = "2f178a1ebf7ebffc8bc3a6834c7144fa";

    private String sharedSecret = "d85346085c547e67";

    private void connect() {
        flickr = new Flickr(apiKey, sharedSecret, new REST());
        Auth auth = new Auth();
        auth.setPermission(Permission.READ);
        //token et tokenSecret viennent  de la methode auth()
        auth.setToken("72157713279778826-a44ff4b431cd8b77");
        auth.setTokenSecret("1d34bf3f48fd67aa");
        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);
        
        flickr.setAuth(auth);
    }

    public void auth() {
        //methode pour authentification 
        
        flickr = new Flickr(apiKey, sharedSecret, new REST());

        AuthInterface authInterface = flickr.getAuthInterface();
        Scanner scanner = new Scanner(System.in);

        OAuth1RequestToken token = authInterface.getRequestToken();
        System.out.println("token: " + token);

        String url = authInterface.getAuthorizationUrl(token, Permission.DELETE);
        System.out.println("Follow this URL to authorise yourself on Flickr");
        System.out.println(url);
        System.out.println("Paste in the token it gives you:");
        System.out.print(">>");

        String tokenKey = JOptionPane.showInputDialog(null);

        OAuth1Token accessToken = authInterface.getAccessToken(token, tokenKey);
        System.out.println("Authentication success");

        Auth auth = null;
        try {
            auth = authInterface.checkToken(accessToken);
        } catch (FlickrException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        // This token can be used until the user revokes it.
        System.out.println("Token: " + accessToken.getToken());
        System.out.println("Secret: " + accessToken.getTokenSecret());
        System.out.println("nsid: " + auth.getUser().getId());
        System.out.println("Realname: " + auth.getUser().getRealName());
        System.out.println("Username: " + auth.getUser().getUsername());
        System.out.println("Permission: " + auth.getPermission().getType());

    }

    @Override
    public String savePhoto(InputStream photo, String title) throws Exception {

        connect();
        uploadMetaData.setTitle(title);
        String photoId = flickr.getUploader().upload(photo, uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();

    }

}
