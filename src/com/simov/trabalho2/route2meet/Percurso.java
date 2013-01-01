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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/****************************************************************************************
 * Percurso
 * - Mostra informação sobre o percurso e permite inicia-lo
 ***************************************************************************************/
public class Percurso extends Activity {
	/** Variaveis globais*/
	private static final String TAG = "Percurso Activity";
	
	/************************************************************************************
	 * onCreate
	 ***********************************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_percurso);
		
		/** Variaveis*/
		final ArrayList<String> coordenadasRotaString = new ArrayList<String>();
		String coordenadasPonto=null;
		String routeName = getIntent().getStringExtra("routeName");
		File sdcard = Environment.getExternalStorageDirectory();
		File file = new File(sdcard,routeName);

		/** Botoes*/
		Button btn_iniciar = (Button)findViewById(R.id.button1);
		
		/** Le o ficheiro com as coordenadas da rota*/
		lerFicheiro(file, coordenadasPonto, coordenadasRotaString);
		
		/** btn_iniciar Click Listener*/
		btn_iniciar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent myIntent = new Intent(Percurso.this, Mapa.class);
				myIntent.putStringArrayListExtra("coordenadasRotaString", coordenadasRotaString);
				Percurso.this.startActivity(myIntent);
				finish();
			}
		});	
	}
	
	/************************************************************************************
	 * onResume
	 ***********************************************************************************/
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()" + this);
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
	 * onCreateOptionsMenu
	 ***********************************************************************************/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_percurso, menu);
		return true;
	}
	
	/************************************************************************************
	 * lerFicheiro
	 ***********************************************************************************/
	public void lerFicheiro(File file,String coordenadasPonto,ArrayList<String> coordenadasRotaString) {
		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;

		    while ((line = br.readLine()) != null) {
		        coordenadasPonto=line;
		        coordenadasRotaString.add(coordenadasPonto);
		    }
		    br.close();
		}
		catch (IOException e) {
			Log.d(TAG, "ERRO na leitura do ficheiro" + this);
		}
	}
}
