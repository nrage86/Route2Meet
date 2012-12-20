package com.simov.trabalho2.route2meet;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ObterPercursos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_obter_percursos);
		
		String[] items = {"Rota 21", "Rota 22","Rota 33","Rota 3","Rota 7"};
		ListView lista_ObterPercursos = (ListView) findViewById(R.id.Lista_ObterPercursos);
		lista_ObterPercursos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_obter_percursos, menu);
		return true;
	}

}
