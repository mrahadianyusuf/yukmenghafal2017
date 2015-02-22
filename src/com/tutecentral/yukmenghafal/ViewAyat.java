package com.tutecentral.yukmenghafal;

import com.tutecentral.yukmenghafal.controller.ControllerDaftarAyat;
import com.tutecentral.yukmenghafal.controller.ControllerDaftarSurat;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;


public class ViewAyat extends FragmentActivity {
	static int maxAyat;
	private MyAdapter mAdapter;
	private ViewPager mPager;
	
	private ControllerDaftarSurat controllerDS;
	static int idSurat;
	static int idAyat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_ayat);
		
		
		mAdapter = new MyAdapter(getSupportFragmentManager());
		
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		
		inisiasi();
		isiData();
	}

	public void inisiasi()
	{
		new ControllerDaftarAyat();
		controllerDS = new ControllerDaftarSurat();		
		idSurat = this.getIntent().getIntExtra("idSurat", -1);
		idAyat = this.getIntent().getIntExtra("idAyat", -1);
		maxAyat = controllerDS.getDaftarSurat().get(idSurat-1).getDaftarAyat().size();
	}
	
	public void isiData()
	{
		/*header.setText(" "+controllerDS.getDaftarSurat().get(idSurat-1).getNamaSurat());*/
		
		Log.d("max", Integer.toString(maxAyat));
		mPager.setCurrentItem(idAyat-1);
	}

	public static class MyAdapter extends FragmentStatePagerAdapter{
		
		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}
		
		@Override
		public int getCount()
		{
			return maxAyat;
		}
		
		
		@Override
		public Fragment getItem(int position) {			
			Fragment fragment = new FragmentAyat();
			Bundle args = new Bundle();			
			fragment.setArguments(args);
			return fragment;
		}
	}	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.view_ayat, menu);		
			return true;
		}
	
}
