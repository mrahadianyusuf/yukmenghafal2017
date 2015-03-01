package com.tutecentral.yukmenghafal;

import com.tutecentral.yukmenghafal.controller.ControllerDaftarAyat;
import com.tutecentral.yukmenghafal.controller.ControllerDaftarSurat;
import com.tutecentral.yukmenghafal.R;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


@SuppressLint("NewApi")
public class FragmentAyat extends Fragment {
	int fragVal;
	private int idSurat;
	private int idAyat;
	private ControllerDaftarAyat da;
	private ControllerDaftarSurat ds;
	private String PACKAGE_NAME;
	
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
	public void onCreate(Bundle saveInstanceState)
	{
		super.onCreate(saveInstanceState);
		idSurat = getArguments() != null ? getArguments().getInt("idSurat"):1;
		idAyat = getArguments() != null ? getArguments().getInt("idAyat"):1;
		Log.d("pho", "ada masalah atas "+idSurat+" "+idAyat);
		da = new ControllerDaftarAyat();
		ds = new ControllerDaftarSurat();
		PACKAGE_NAME = getActivity().getApplicationContext().getPackageName();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState)
	{
		View layoutView = inflater.inflate(R.layout.fragment_ayat, parent, false);
		ImageView gambarVisualAyat = (ImageView) layoutView.findViewById(R.id.fragment_ayatdalam);
		gambarVisualAyat.setBackgroundDrawable(generateGambar(idSurat, idAyat));
		
		ImageView gambarAyat = (ImageView) layoutView.findViewById(R.id.fragment_ayatarab);
		gambarAyat.setBackgroundDrawable(generateAyat(idSurat, idAyat));
		
		return layoutView;
	}
	
	public Drawable generateGambar(int nomorSurat, int nomorAyat)
	{
		try {
			Log.d("pho", "ada masalah bawha "+idSurat+" "+idAyat);
			Drawable drawable = getResources().getDrawable(getResources().getIdentifier(da.getDaftarAyat(idSurat).get(idAyat).getNamaGambarVisual(),"drawable", PACKAGE_NAME));
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
			Drawable drawable = getResources().getDrawable(getResources().getIdentifier(da.getDaftarAyat(idSurat).get(idAyat).getNamaGambarAyat(), "drawable", PACKAGE_NAME));
			return drawable;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
