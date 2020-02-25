/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.esprit.services.impl.ServiceLivraison;
import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;



public class MapDestination extends MapView{
	
/**
 * The map object
 */
	private static Map map;

/**
 * Editable circle options 
 */
	private CircleOptions settingsCircle;
/**
 * Editable LineOptions
 */
	private  PolylineOptions settingsLine;

	
	public CircleOptions getSettingsCircle() {
		return settingsCircle;
	}

	public void setSettingsCircle(CircleOptions settingsCircle) {
		this.settingsCircle = settingsCircle;
	}





	/**
	 * Generate a marker on the LatLongPoint
	 * @param pos of the wanted marker
	 * @return Marker
	 */
	public Marker generateMarker(LatLng pos)
	{
		Marker marker=null;
	
			
			 marker = new Marker((com.teamdev.jxmaps.Map) map);
			marker.setPosition(pos);
			map.setCenter(pos);
	
		System.out.println("Graficado marker");
		return marker;
	}

	/**
	 * Generate a simple nibe between two LatLong points
	 * @param start Start point of the line
	 * @param end End point of the line
	 * @param markers Do you wanna put a marker on the LatLong points
	 */
	public void generateSimplePath(LatLng start,LatLng end,Boolean markers)
	{
		LatLng[] path = {start,end};
		Polyline polyline = new Polyline((com.teamdev.jxmaps.Map) map);
		polyline.setPath(path);
		if(markers)
		{
			generateMarker(start);
			generateMarker(end);
		}
	}

	public Map darMap()
	{
		return map;
	}

	/**
	 * Generate a circle area on the map
	 * @param center LatLong of the center of the map
	 * @param radius on meters
	 */
	public void generateArea(LatLng center,Double radius)
	{
		Circle circle = new Circle((com.teamdev.jxmaps.Map) map);
		circle.setCenter(center);
		circle.setRadius(radius);
		circle.setVisible(true);
		circle.setOptions(settingsCircle);
	}

	/**
	 * Generate a line on the Map on the selected breakpoints
	 * @param markers  do you wanna put a marker on each vertex 
	 * @param path Group of points of the Line
	 */
	public void GenerateLine(boolean markers,LatLng... path)
	{
		if(markers)
		{
			for(LatLng p:path)
			{
				generateMarker(p);
			}
		}
		Polyline polyline = new Polyline((com.teamdev.jxmaps.Map) map);
		polyline.setPath(path);
	}

	/**
	 * Create a new Map panel whit the param Name
	 * @param pString Name for the map
	 */
	public MapDestination(String pString) {

		JFrame frame = new JFrame("Chicago-Data: "+pString);


		settingsCircle=new CircleOptions();
		settingsCircle.setFillColor("#FF0000");
		settingsCircle.setRadius(2000000);
		settingsCircle.setFillOpacity(0.35);

		settingsLine=new PolylineOptions();
		settingsLine.setGeodesic(true);
		settingsLine.setStrokeColor("#FF0000");
		settingsLine.setStrokeOpacity(1.0);
		settingsLine.setStrokeWeight(2.0);

		// Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
		// the map object is ready to use. Current implementation of onMapReady customizes the map object.
		setOnMapReadyHandler(new MapReadyHandler() {
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					map = (Map) getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
					mapOptions.setMapTypeControlOptions(controlOptions);
					map.setOptions(mapOptions);
					map.setCenter(new LatLng(36.81179, 10.17402));
					map.setZoom(10);
                                       

				}
			}
		});
		System.out.print("Espere mientras se genera el mapa ");
		try {
			for(int i=0;i<10;i++)
			{
				TimeUnit.SECONDS.sleep(1);
				System.out.print(".");
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("|");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

        
            public static void main(String[] args) {
       // launch(args);

       
        final MapDestination example = new MapDestination("test");
        LatLng ariana = new LatLng(36.8599390, 10.1909730);
        Marker generateMarker = example.generateMarker(map.getCenter());
        Marker generateMarker2 = example.generateMarker(ariana);
        
            }



}
