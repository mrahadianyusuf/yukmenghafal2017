package com.tutecentral.yukmenghafal;

import com.tutecentral.yukmenghafal.controller.ControllerAyat;
import com.tutecentral.yukmenghafal.R;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


@SuppressLint("NewApi")
public class FragmentAyat extends Fragment {
	int fragVal;
	private int idSurat;
	private int idAyat;
	private ControllerAyat controllerAyat;
	private String PACKAGE_NAME;
	
	private MenuItem menu_bookmark;
	private MenuItem menu_love;
	
	private boolean status_bookmark;
	private boolean status_love;
	
	static FragmentAyat init(int idSurat, int idAyat)
	{
		FragmentAyat ayatFrag = new FragmentAyat();
		// Supply Val Input as an argument
		Bundle args = new Bundle();
		args.putInt("idSurat", idSurat);
		args.putInt("idAyat", idAyat);
		ayatFrag.setArguments(args);
		return ayatFrag;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    inflater.inflate(R.menu.view_ayat, menu);
	    menu_bookmark = menu.findItem(R.id.action_bookmark);
		menu_love = menu.findItem(R.id.action_love);
		iconDefault();
	    super.onCreateOptionsMenu(menu,inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; goto parent activity.
	        	controllerAyat.ubahSelesai(idSurat-1);
	            getActivity().finish();
	            return true;
	        case R.id.action_bookmark:
	        	if(status_bookmark){
	        		menu_bookmark.setIcon(R.drawable.bookmark_white);
	        		status_bookmark = false;
	        		controllerAyat.getDaftarAyat(idSurat).get(idAyat).setStatusBookmark(status_bookmark);
	        	}
	        	else 
	        	{
	        		menu_bookmark.setIcon(R.drawable.bookmark);
	        		controllerAyat.hapusBookmark();
	        		status_bookmark = true;
	        		controllerAyat.getDaftarAyat(idSurat).get(idAyat).setStatusBookmark(status_bookmark);
	        	}
	        	return true;
	        case R.id.action_love:
	        	if(status_love){
	        		menu_love.setIcon(R.drawable.love_white);
	        		status_love = false;
	        		controllerAyat.getDaftarAyat(idSurat).get(idAyat).setStatusSelesai(status_love);
	        	}
	        	else 
	        	{
	        		menu_love.setIcon(R.drawable.love_pink);
	        		status_love= true;
	        		controllerAyat.getDaftarAyat(idSurat).get(idAyat).setStatusSelesai(status_love);
	        	}
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onCreate(Bundle saveInstanceState)
	{
		super.onCreate(saveInstanceState);
		
		idSurat = getArguments() != null ? getArguments().getInt("idSurat"):1;
		idAyat = getArguments() != null ? getArguments().getInt("idAyat"):1;
		controllerAyat = new ControllerAyat();
		PACKAGE_NAME = getActivity().getApplicationContext().getPackageName();
		status_bookmark = controllerAyat.getDaftarAyat(idSurat).get(idAyat).getStatusBookmark();
		status_love = controllerAyat.getDaftarAyat(idSurat).get(idAyat).getStatusSelesai();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState)
	{
		setHasOptionsMenu(true);
		
		View layoutView = inflater.inflate(R.layout.fragment_ayat, parent, false);
		ImageView gambarVisualAyat = (ImageView) layoutView.findViewById(R.id.fragment_ayatdalam);
		gambarVisualAyat.setBackgroundDrawable(generateGambar(idSurat, idAyat));
		
		ImageView gambarAyat = (ImageView) layoutView.findViewById(R.id.fragment_ayatarab);
		gambarAyat.setBackgroundDrawable(generateAyat(idSurat, idAyat));
		
		return layoutView;
	}
	
	public void iconDefault()
	{
		if(status_bookmark)
			menu_bookmark.setIcon(R.drawable.bookmark);
		else 
			menu_bookmark.setIcon(R.drawable.bookmark_white);
		if(status_love)
			menu_love.setIcon(R.drawable.love_pink);
		else 
			menu_love.setIcon(R.drawable.love_white);
	}
	
	public Drawable generateGambar(int nomorSurat, int nomorAyat)
	{
		try {
			Drawable drawable = getResources().getDrawable(getResources().getIdentifier(controllerAyat.getGambarVisual(nomorSurat, nomorAyat), "drawable", PACKAGE_NAME));
			return drawable;
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("pho", "ada masalah exceptoin "+e.toString());
		}
		return null;		
	}
	
	public Drawable generateAyat(int nomorSurat, int nomorAyat)
	{
		try {
			Drawable drawable = getResources().getDrawable(getResources().getIdentifier(controllerAyat.getGambarAyat(nomorSurat,nomorAyat), "drawable", PACKAGE_NAME));
			return drawable;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
