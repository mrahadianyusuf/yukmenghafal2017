package com.tutecentral.yukmenghafal;

import com.tutecentral.yukmenghafal.controller.ControllerDaftarAyat;
import com.tutecentral.yukmenghafal.controller.ControllerDaftarSurat;

import android.R.menu;
import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
	private ControllerDaftarAyat controllerDA;
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
		 LayoutInflater inflater = (LayoutInflater) getActionBar()
		            .getThemedContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		    View customActionBarView = inflater.inflate(R.layout.actionbar_custom, null);
		ActionBar actionBar = this.getActionBar();		
//		actionBar.setDisplayOptions(
//	            ActionBar.DISPLAY_SHOW_CUSTOM,
//	            ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME
//	                    | ActionBar.DISPLAY_SHOW_TITLE);
//	    actionBar.setCustomView(customActionBarView,
//	            new ActionBar.LayoutParams(
//	                    ViewGroup.LayoutParams.MATCH_PARENT,
//	                    ViewGroup.LayoutParams.MATCH_PARENT));
	    actionBar.setDisplayHomeAsUpEnabled(true);
	}

	public void inisiasi()
	{
		controllerDA = new ControllerDaftarAyat();
		controllerDS = new ControllerDaftarSurat();		
		idSurat = this.getIntent().getIntExtra("idSurat", -1);
		idAyat = this.getIntent().getIntExtra("idAyat", -1);
		maxAyat = controllerDS.getDaftarSurat().get(idSurat-1).getDaftarAyat().size();
	}
	
	public void isiData()
	{
		setTitle(controllerDS.getDaftarSurat().get(idSurat-1).getNamaSurat());
		
		getActionBar().setIcon(R.drawable.annaas_pink);
		/*header.setText(" "+controllerDS.getDaftarSurat().get(idSurat-1).getNamaSurat());*/
		mPager.setCurrentItem(idAyat-1);
	}
	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        menu.findItem(R.id.action_settings).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
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
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.view_ayat, menu);
			return true;
		}
}
