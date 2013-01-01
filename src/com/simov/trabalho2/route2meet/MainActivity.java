package com.simov.trabalho2.route2meet;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


/****************************************************************************************
 * MainActivity
 * - Inicio
 ***************************************************************************************/
public class MainActivity extends Activity {
	/** Variaveis globais*/
	private static final String TAG = "MainActivity";
	
	/************************************************************************************
	 * onCreate
	 ***********************************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/** Botoes*/
		Button btn_meusPercursos = (Button)findViewById(R.id.btn_meusPercursos);
		Button btn_obterPercursos = (Button)findViewById(R.id.btn_obtPercursos);
		
		btn_meusPercursos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MainActivity.this, MeusPercursos.class);
				MainActivity.this.startActivity(myIntent);
			}
		});
		
		btn_obterPercursos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MainActivity.this, ObterPercursos.class);
				MainActivity.this.startActivity(myIntent);
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
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}	
}
