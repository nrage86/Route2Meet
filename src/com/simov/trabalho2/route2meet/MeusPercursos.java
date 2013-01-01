package com.simov.trabalho2.route2meet;

import java.io.File;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


/****************************************************************************************
 * MeusPercursos
 * - Mostra a lista dos percursos presentes no tlm (já sacados)
 ***************************************************************************************/
public class MeusPercursos extends Activity {
	/** Variaveis globais*/
	private static final String TAG = "MeusPercursos Activity";
	
	/************************************************************************************
	 * onCreate
	 ***********************************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meus_percursos);
		
		/** Variaveis*/
		ArrayList<String> routeList = new ArrayList<String>();
		ArrayList<String> path = new ArrayList<String>();
		File sdcard = Environment.getExternalStorageDirectory();
		File[] files = sdcard.listFiles();
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();
		
		/** Lista os routes da pasta /sdcard do tlm numa listview no ecra*/
		for(int i=0; i < files.length; i++)    
	    {
	        File file = files[i];
	        path.add(file.getPath());
  
            String filename=file.getName();
            String ext = filename.substring(filename.lastIndexOf('.')+1, filename.length());
            if(ext.equals("txt"))
            {
            	routeList.add(file.getName());
            }
	    }  
		
		/** Tab 1*/
		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("A Completar");
		ListView listView1 = (ListView) findViewById(R.id.ListViewId1);
		listView1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				routeList));
		
		listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MeusPercursos.this, Percurso.class);
				String selectedFile = ((TextView) arg1).getText().toString();
				myIntent.putExtra("routeName", selectedFile);
				MeusPercursos.this.startActivity(myIntent);
			}
		});
		tabHost.addTab(spec1);

		/** Tab 2*/
		TabSpec spec2=tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("Completos");
		spec2.setContent(R.id.tab2);		
		String[] items2 = {"Rota 1", "Rota 12","Rota 3","Rota 13","Rota 6"};
		ListView listView2 = (ListView) findViewById(R.id.ListViewId2);
		listView2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				items2));
		
		listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MeusPercursos.this, Percurso.class);
				String selectedFile = "file.txt";
				myIntent.putExtra("routeName", selectedFile);
				MeusPercursos.this.startActivity(myIntent);
			}
		});
		tabHost.addTab(spec2);
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
		getMenuInflater().inflate(R.menu.activity_meus_percursos, menu);
		return true;
	}
}
