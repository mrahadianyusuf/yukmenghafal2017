package com.tutecentral.yukmenghafal;

import java.util.List;

import com.tutecentral.yukmenghafal.R;
import com.tutecentral.yukmenghafal.controller.ControllerDaftarSurat;
import com.tutecentral.yukmenghafal.model.Surat;
import com.tutecentral.yukmenghafal.utils.ArrayAdapterDaftarSurat;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DaftarSurat extends Fragment {
	private ListView listView;
	private ControllerDaftarSurat controller;
	private ArrayAdapterDaftarSurat adapter;

	ImageView ivIcon;
	TextView tvItemName;

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";

	public DaftarSurat() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_view_daftar_surat,
				container, false);

		/* BEGIN : inisialisasi */
		SuratManager.createSuratManager(getActivity().getAssets(),
				getActivity().getApplicationContext());
		controller = new ControllerDaftarSurat();
		listView = (ListView) view.findViewById(R.id.list_view);		
		/* END OF : inisialisasi */

		isidata();
		return view;
	}
	
	public void isidata(){
		List<Surat> daftarSurat = controller.getDaftarSurat();

		adapter = new ArrayAdapterDaftarSurat(getActivity().getApplicationContext()
				, daftarSurat);

		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				// TODO Auto-generated method stub
				try {					
					final Surat surat = (Surat) parent.getItemAtPosition(position);
					Intent i = new Intent(DaftarSurat.this.getActivity(),
							ViewDaftarAyat.class);
					i.putExtra("idSurat", surat.getId());
					i.putExtra("namaSurat",surat.getNamaSurat());
					final int a = 1;										
					startActivityForResult(i, a);				
				} catch (Exception e) {
					
				}
			}			
		});

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getActivity().getMenuInflater().inflate(R.menu.view_daftar_surat, menu);
		return true;
	}
}
