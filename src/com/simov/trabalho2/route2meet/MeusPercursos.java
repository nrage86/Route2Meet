package com.simov.trabalho2.route2meet;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MeusPercursos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meus_percursos);
		
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();

		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("A Completar");
		String[] items1 = {"Rota 4", "Rota 15","Rota 8"};
		ListView listView1 = (ListView) findViewById(R.id.ListViewId1);
		listView1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items1));
		
		listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MeusPercursos.this, Percurso.class);
				MeusPercursos.this.startActivity(myIntent);
			}
		});

		TabSpec spec2=tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("Completos");
		spec2.setContent(R.id.tab2);		
		String[] items2 = {"Rota 1", "Rota 12","Rota 3","Rota 13","Rota 6"};
		ListView listView2 = (ListView) findViewById(R.id.ListViewId2);
		listView2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items2));
		
		listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(MeusPercursos.this, Percurso.class);
				MeusPercursos.this.startActivity(myIntent);
			}
		});

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_meus_percursos, menu);
		return true;
	}

}
