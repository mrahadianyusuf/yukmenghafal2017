package com.tutecentral.yukmenghafal;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.tutecentral.yukmenghafal.R;
import com.tutecentral.yukmenghafal.controller.ControllerDaftarAyat;
import com.tutecentral.yukmenghafal.model.Ayat;
import com.tutecentral.yukmenghafal.utils.ArrayAdapterDaftarAyat;

public class ViewDaftarAyat extends Activity {
	private ListView listView;
	private ControllerDaftarAyat controllerDA;
	private ArrayAdapterDaftarAyat adapter;
	private int idSurat;
	private String namaSurat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_daftar_ayat);		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		inisiasi();
		isiData();
	}

//	@Override
//	public void onResume()
//	{
//		
//	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_daftar_ayat, menu);
		return true;
	}
	
	public void inisiasi()
	{
		controllerDA = new ControllerDaftarAyat();
		idSurat = this.getIntent().getIntExtra("idSurat", -1);
		namaSurat = this.getIntent().getStringExtra("namaSurat");
		listView = (ListView) findViewById(R.id.list_view);
	}
	
	public void isiData()
	{
		//ini ada yang aneh dah wkwk.
		/*header.setText(" "+controllerDS.getDaftarSurat().get(idSurat-1).getNamaSurat());*/
		List<Ayat> daftarAyat = controllerDA.getDaftarAyat(idSurat);
		adapter = new ArrayAdapterDaftarAyat(this, daftarAyat, idSurat);		
		setTitle(namaSurat);
		getActionBar().setIcon(R.drawable.annaas_pink);
		listView.setAdapter(adapter);		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long arg3){
				// TODO Auto-generated method stub
				try {
					final Ayat ayat = (Ayat) parent.getItemAtPosition(position);					
					Intent i = new Intent(ViewDaftarAyat.this, ViewAyat.class);
					i.putExtra("idSurat", idSurat);
					i.putExtra("idAyat", ayat.getId());
					i.putExtra("namaSurat",namaSurat);
					final int a = 1;
					startActivityForResult(i, a);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; goto parent activity.
//	        	controllerDA.ubahSelesai(idSurat);
	            this.finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onBackPressed()
	{
//    	controllerDA.ubahSelesai(idSurat);
		Log.d("Pho", "ada masalah ViewDaftarAyat");
		finish();
	}
}

