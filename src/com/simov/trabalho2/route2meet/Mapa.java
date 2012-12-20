package com.simov.trabalho2.route2meet;

import java.io.IOException;
import java.text.DecimalFormat;
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
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;


public class Mapa extends MapActivity{
	
	private MapController mapController;

	//Handle location callback events
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
	
	LocationManager locationManager;
	Location currentLocation;
		
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        
        MapView myMapView = (MapView)findViewById(R.id.map_view);

        // Configure the map display options
        mapController = myMapView.getController();
        myMapView.setSatellite(false);
        myMapView.displayZoomControls(false);
        myMapView.setBuiltInZoomControls(true);
        mapController.setZoom(15);
        /*
        //Get a cached location, if it exists
        updateWithNewLocation(currentLocation);
    	//Register for updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5,locationListener);
        */
    }
	
	@Override
	public void onResume() {
		super.onResume();
		
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
		//Get a cached location, if it exists
		currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		updateWithNewLocation(currentLocation);
		//Register for updates
		int minTime = 5000;
		float minDistance = 0;
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
		minTime, minDistance, locationListener);
	}
	
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
			
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
