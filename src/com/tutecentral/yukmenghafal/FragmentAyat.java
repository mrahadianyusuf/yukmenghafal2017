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
//		args.putInt("val", val);
		args.putInt("idSurat", idSurat);
		args.putInt("idAyat", idAyat);
		Log.d("oh", "ada masalah "+idSurat+" "+idAyat);
		ayatFrag.setArguments(args);
		
		return ayatFrag;
	}
	
	@Override
	public void onCreate(Bundle saveInstanceState)
	{
		super.onCreate(saveInstanceState);
//		fragVal = getArguments() != null ? getArguments().getInt("val") : 1;

		idSurat = getArguments() != null ? getArguments().getInt("idSurat"):1;
		idAyat = getArguments() != null ? getArguments().getInt("idAyat"):1;
		da = new ControllerDaftarAyat();
		ds = new ControllerDaftarSurat();
		PACKAGE_NAME = getActivity().getApplicationContext().getPackageName();
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState)
	{
//		Drawable a = generateGambar(idSurat, idAyat);

		View layoutView = inflater.inflate(R.layout.fragment_ayat, parent, false);
		ImageView gambarAyat = (ImageView) layoutView.findViewById(R.id.fragment_ayatdalam);
		gambarAyat.setBackgroundDrawable(generateGambar(idSurat, idAyat));
//		TextView tvText = (TextView) layoutView.findViewById(R.id.testtext);
//		tvText.setText(tvText.getText()+ " Nomor Surat: "+ idSurat +" nomor ayat: "+idAyat);
		return layoutView;
	}
	
	public Drawable generateGambar(int nomorSurat, int nomorAyat)
	{
		try {
			Drawable drawable = getResources().getDrawable(getResources().getIdentifier(da.getDaftarAyat(idSurat).get(idAyat).getNamaGambarVisual(),"drawable", PACKAGE_NAME));
			return drawable;
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("pho", "ada masalah "+e.toString());
			Log.d("pho", "ada masalah "+da.getDaftarAyat(idSurat).get(idAyat).getNamaGambarVisual());
			
		}
		return null;		
	}
}
