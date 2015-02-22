package com.tutecentral.yukmenghafal;

import java.util.List;



import com.tutecentral.yukmenghafal.controller.ControllerDaftarSurat;
import com.tutecentral.yukmenghafal.model.Surat;
import com.tutecentral.yukmenghafal.utils.ArrayAdapterDaftarSurat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ViewDaftarSurat extends Activity {
	private ListView listView;
	private TextView header;
	private ControllerDaftarSurat controller;
	private ArrayAdapterDaftarSurat adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_daftar_surat);
		
		inisiasi();
		isiData();
	}

	public void inisiasi()
	{
		SuratManager.createSuratManager(getAssets(), getApplicationContext());
		controller = new ControllerDaftarSurat();
		listView = (ListView) findViewById(R.id.list_view);
		header = (TextView) findViewById(R.id.header);
		
		Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/opensans.ttf");
		header.setTypeface(font);
		
	}
	public void isiData()
	{
		List<Surat> daftarSurat = controller.getDaftarSurat();
		adapter = new ArrayAdapterDaftarSurat(this, daftarSurat);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position,
					long id) {
				// TODO Auto-generated method stub
				try {
					final Surat surat = (Surat) parent.getItemAtPosition(position);
					
					Intent i = new Intent(ViewDaftarSurat.this, ViewDaftarAyat.class);
					i.putExtra("idSurat", surat.getId());
					final int a = 1;
					startActivityForResult(i, a);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_daftar_surat, menu);
		return true;
	}

}
