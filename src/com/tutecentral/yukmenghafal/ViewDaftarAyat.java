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
import com.tutecentral.yukmenghafal.controller.ControllerDaftarSurat;
import com.tutecentral.yukmenghafal.model.Ayat;
import com.tutecentral.yukmenghafal.utils.ArrayAdapterDaftarAyat;

public class ViewDaftarAyat extends Activity {
	private ListView listView;
	private ControllerDaftarAyat controllerDA;
	private ControllerDaftarSurat controllerDS;
	private ArrayAdapterDaftarAyat adapter;
	private int idSurat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_daftar_ayat);		
		ActionBar actionBar = getActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);
		Log.d("cek0", "error0");
		inisiasi();
		isiData();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_daftar_ayat, menu);
		return true;
	}
	
	public void inisiasi()
	{
		controllerDA = new ControllerDaftarAyat();
		controllerDS = new ControllerDaftarSurat();
		idSurat = this.getIntent().getIntExtra("idSurat", -1);
		listView = (ListView) findViewById(R.id.list_view);
		Log.d("cek1", "error1");
	}
	
	public void isiData()
	{
		//ini ada yang aneh dah wkwk.
		/*header.setText(" "+controllerDS.getDaftarSurat().get(idSurat-1).getNamaSurat());*/
		List<Ayat> daftarAyat = controllerDA.getDaftarAyat(idSurat);
		adapter = new ArrayAdapterDaftarAyat(this, daftarAyat, idSurat);		
		setTitle(controllerDS.getDaftarSurat().get(idSurat-1).getNamaSurat());
		getActionBar().setIcon(R.drawable.annaas_pink);
		listView.setAdapter(adapter);		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long arg3){
				// TODO Auto-generated method stub
				try {
					final Ayat ayat = (Ayat) parent.getItemAtPosition(position);					
					Intent i = new Intent(ViewDaftarAyat.this, ViewAyatFragment.class);
					i.putExtra("idSurat", idSurat);
					i.putExtra("idAyat", ayat.getId());
					final int a = 1;
					Log.d("error", "here");
					
					startActivityForResult(i, a);
					Log.d("error2", "here2");
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
	            this.finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}

