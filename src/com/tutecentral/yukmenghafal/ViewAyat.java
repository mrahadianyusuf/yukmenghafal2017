package com.tutecentral.yukmenghafal;

import com.tutecentral.yukmenghafal.controller.ControllerAyat;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;


public class ViewAyat extends FragmentActivity {
	static int maxAyat;
	private MyAdapter mAdapter;
	private ViewPager mPager;
	private String namaSurat;
	private ControllerAyat controllerAyat;
	static int idSurat;
	static int idAyat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_ayat);
		inisiasi();
		mAdapter = new MyAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		setupTabs();
		isiData();
		
	}
	
	public void setupTabs()
	{
		ActionBar actionBar = this.getActionBar();		
	    actionBar.setDisplayHomeAsUpEnabled(true);
	}

	public void inisiasi()
	{
		controllerAyat = new ControllerAyat();	
		idSurat = this.getIntent().getIntExtra("idSurat", -1);
		idAyat = this.getIntent().getIntExtra("idAyat", -1);
		namaSurat = this.getIntent().getStringExtra("namaSurat");
		maxAyat = controllerAyat.getDaftarSurat().get(idSurat-1).getDaftarAyat().size();
	}
	
	public void isiData()
	{
		
		setTitle(namaSurat);
		getActionBar().setIcon(R.drawable.annaas_pink);		
		mPager.setCurrentItem(idAyat-1);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_ayat, menu);
		return true;
	}
	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
//        menu.findItem(R.id.action_settings).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; goto parent activity.
	            this.finish();
	            return true;
	        case R.id.action_bookmark:
	        	return true;
	        case R.id.action_love:
	        	
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
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
			return FragmentAyat.init(idSurat,position);
		}
	}	
		
}
