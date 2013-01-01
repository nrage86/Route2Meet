package com.simov.trabalho2.route2meet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;


/****************************************************************************************
 * Mapa
 * - Mostra o mapa do "google maps" com as rotas como overlay
 ***************************************************************************************/
public class Mapa extends MapActivity{
	/** Variaveis globais*/
	private static final String TAG = "Mapa Activity";
	private MapController mapController;
	private LocationManager locationManager;
	//private Location currentLocation;
	/* Handle location callback events*/
	/* 
	private final LocationListener locationListener = new LocationListener(){
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			currentLocation = location;
			updateWithNewLocation(location);
		}
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
		}
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
		}
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
		}
	};
	*/

	/************************************************************************************
	 * onCreate
	 ***********************************************************************************/
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        MapView myMapView = (MapView)findViewById(R.id.map_view);
        
        /** Variaveis */
        //int lat, lng;
        int counter=0;
        ArrayList<GeoPoint> coordenadasRota = new ArrayList<GeoPoint>();
        ArrayList<String> coordenadasRotaString = new ArrayList<String>();
        ArrayList<GeoPoint> specialLocationsRota = new ArrayList<GeoPoint>();
        String[] coordenadasString;
        
        /** Serve para receber location updates (in foreground)*/
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        /** Vai buscar a ultima posição GPS para o currentLocation*/
        /*
        currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(currentLocation != null) {
	        //Convert to microdegrees
	        lat = (int)(currentLocation.getLatitude() * 1000000);
	        lng = (int)(currentLocation.getLongitude() * 1000000);
        } else {
	        //Rotunda da Boavista PORTO
	        lat = 41157846;
	        lng = -8629106;
        }       
        */
        
        /** Adiciona pontos da rota (recebidos num array de strings da Activity Percurso)
         *  ao array coordenadasRota*/
        coordenadasRotaString = getIntent().getStringArrayListExtra("coordenadasRotaString");
        counter=coordenadasRotaString.size();
        
        for (String coordenadasPonto : coordenadasRotaString) {
        	coordenadasString = coordenadasPonto.split(";");	
        	// Se for a primeira posição da rota (Vai aparecer um icon)
        	if(counter==coordenadasRotaString.size())
        		specialLocationsRota.add(new GeoPoint(Integer.parseInt(coordenadasString[0]),Integer.parseInt(coordenadasString[1])));
        	// Se for a ultima posição da rota (Vai aparecer um icon)
        	if(counter==1)
        		specialLocationsRota.add(new GeoPoint(Integer.parseInt(coordenadasString[0]),Integer.parseInt(coordenadasString[1])));
        	
        	coordenadasRota.add(new GeoPoint(Integer.parseInt(coordenadasString[0]),Integer.parseInt(coordenadasString[1])));
        	counter--;
		}
        
        /** Configuração do mapa e overlays*/
        LocationOverlayAdapter myOverlay =
        		new LocationOverlayAdapter(getResources().getDrawable(R.drawable.ic_launcher));
		myOverlay.setItems(coordenadasRota,specialLocationsRota);
		myMapView.getOverlays().add(myOverlay);
        myMapView.setSatellite(false);
        myMapView.displayZoomControls(false);
        myMapView.setBuiltInZoomControls(true);
        mapController = myMapView.getController();
		mapController.setCenter(coordenadasRota.get(0));
		mapController.setZoom(16);
    }
	
	/************************************************************************************
	 * onResume
	 ***********************************************************************************/
	@Override
	public void onResume() {
		super.onResume();
	
		/** Pede ao utilizador para lisgar o GPS caso este nao esteja ligado*/
		if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			//Ask the user to enable GPS
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Location Manager");
			builder.setMessage("We want to use your location, but GPS is currently disabled.\n"
					+"Would you like to change these settings now?");
			
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//Launch settings, allowing user to make a change
					Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(i);
				}
			});
			
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//No location service, no Activity
					finish();
				}
			});
			
			builder.create().show();
		}
		
		/** Actualiza a posição para a ultima posição GPS conhecida e depois vai actualizando
		 *  a posição actual de 5  em 5 segundos */
		/*
		//Get a cached location, if it exists
		currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		updateWithNewLocation(currentLocation);
		//Register for updates
		int minTime = 5000;
		float minDistance = 0;
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
		minTime, minDistance, locationListener);
		*/
	}
	
	/************************************************************************************
	 * onStart
	 ***********************************************************************************/
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()" + this);
    }

	/************************************************************************************
	 * onPause
	 ***********************************************************************************/
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()" + this);
    }

	/************************************************************************************
	 * onStop
	 ***********************************************************************************/
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()" + this);
    }

	/************************************************************************************
	 * onDestroy
	 ***********************************************************************************/
    @Override
    public void onDestroy() {
        super.onStop();
        Log.d(TAG, "onDestroy()" + this);
    }
	
	/************************************************************************************
	 * updateWithNewLocation
	 * - Actualiza com nova localização GPS
	 ***********************************************************************************/
	/*
	private void updateWithNewLocation(Location location) {
    	String latLongString = "Unknown";
    	String addressString = "No address found";
    	TextView myLocationText;
    	DecimalFormat df = new DecimalFormat("##.00");  
    	myLocationText = (TextView)findViewById(R.id.text_view);
    	if (location != null) {
    		double lat = location.getLatitude();
    		double lng = location.getLongitude();
    		
    		int x = (int) (lat*1E6);
    		int y = (int) (lng*1E6);
    		GeoPoint point = new GeoPoint(x,y);
    		mapController.animateTo(point);
    		
    		latLongString = "Lat:" +  df.format(lat) + "\nLong:" +  df.format(lng);
    		Geocoder gc = new Geocoder(Mapa.this, Locale.getDefault());
    	    try {
    	    	List<Address> addresses  = gc.getFromLocation(lat, lng, 1);
    	    	if (addresses.size() == 1) {
        			addressString="";
        			Address address = addresses.get(0);
    	    		addressString = addressString + address.getAddressLine(0) + "\n";
    	    		for (int i = 0; i < address.getMaxAddressLineIndex(); i++){
    	    			addressString = addressString + address.getAddressLine(i) + "\n";
		    		}
    	    		addressString = addressString + address.getLocality()+ "\n";
    	    		addressString = addressString + address.getPostalCode()+ "\n";
    	    		addressString = addressString + address.getCountryName()+ "\n";
    	    	}
    	    } catch (IOException ioe) {
    	        Log.e("Geocoder IOException exception: ", ioe.getMessage());
    	    }
    	}		
    	myLocationText.setText("Your Current Position is:\n" + latLongString + "\n" + addressString);
    }	
    */
		
	/************************************************************************************
	 * isRouteDisplayed
	 ***********************************************************************************/
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
