/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.views;

import com.esprit.utilities.DataSource;
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.javafx.MapView;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class showmapController implements Initializable {
    @FXML
    private MapView mapView;
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
            mapView.setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {  
                // Check if the map is loaded correctly
                if (status == MapStatus.MAP_STATUS_OK) {
                    try {
                        // Getting the associated map object
                        final Map map = mapView.getMap();
                        // Creating a map options object
                        MapOptions options = new MapOptions();
                        // Creating a map type control options object
                        MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                        // Changing position of the map type control
                        controlOptions.setPosition(ControlPosition.TOP_RIGHT);
                        // Setting map type control options
                        options.setMapTypeControlOptions(controlOptions);
                        // Setting map options
                        map.setOptions(options);
                        // Setting the map center
                        map.setCenter(new LatLng(36.8599390, 10.1909730));
                        // Setting initial zoom value
                        map.setZoom(7.0);
                        Connection con = DataSource.getInstance().getConnection();
                        ResultSet res = con.createStatement().executeQuery("select adresse_livraison from livraison where etat_livraison='NonLivree'");
                        while (res.next()){
                            performGeocode(res.getString(1));     
                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
      
    }    
    private void performGeocode(String text) {
        // Getting the associated map object
        Map map = mapView.getMap();
        Marker mark = new Marker(map);
        // Creating a geocode request
        GeocoderRequest request = new GeocoderRequest();
        // Setting address to the geocode request
        request.setAddress(text);

        // Geocoding position by the entered address
        mapView.getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
            @Override
            public void onComplete(GeocoderResult[] results, GeocoderStatus status) {
                // Checking operation status
                if ((status == GeocoderStatus.OK) && (results.length > 0)) {
                    // Getting the first result
                    GeocoderResult result = results[0];
                    // Getting a location of the result
                    LatLng location = result.getGeometry().getLocation();
                    // Setting the map center to result location
                    mark.setPosition(location);
                    final InfoWindow window = new InfoWindow(map);
                    window.setContent("Im Waiting");
                    window.open(map, mark);
                }
            }
        });
    }
    
}
