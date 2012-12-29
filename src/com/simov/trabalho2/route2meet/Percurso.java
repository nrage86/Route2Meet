package com.simov.trabalho2.route2meet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Percurso extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_percurso);
		
		Button btn_iniciar = (Button)findViewById(R.id.button1);
		
		
		
		
		

        ArrayList<GeoPoint> locationsTeste = new ArrayList<GeoPoint>();
        final ArrayList<String> locationsTeste2 = new ArrayList<String>();
        

		File sdcard = Environment.getExternalStorageDirectory();
		String[] separated ;
		//Get the text file
		File file = new File(sdcard,"file.txt");

		//Read text from file
		//StringBuilder text = new StringBuilder();
		String coordenadasJuntas;

		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;

		    while ((line = br.readLine()) != null) {
		        coordenadasJuntas=line;
		        separated = coordenadasJuntas.split(";");
		        //text.replace(".", "");
		        //myNum = Integer.parseInt(myString);
		        
		        //locationsTeste.add(new GeoPoint(Integer.parseInt(separated[0]),Integer.parseInt(separated[1])));
		        
		        locationsTeste2.add(coordenadasJuntas);
		        
		    }
		}
		catch (IOException e) {
		    //You'll need to add proper error handling here
		}
		
		
		
		
		
		
		
		
		btn_iniciar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(Percurso.this, Mapa.class);
				myIntent.putStringArrayListExtra("locationTeste2", locationsTeste2);
				Percurso.this.startActivity(myIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_percurso, menu);
		return true;
	}

}
