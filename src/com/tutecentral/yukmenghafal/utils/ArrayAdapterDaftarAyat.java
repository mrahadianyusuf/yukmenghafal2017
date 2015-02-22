package com.tutecentral.yukmenghafal.utils;

import java.util.List;

/*import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;*/

import com.tutecentral.yukmenghafal.R;
import com.tutecentral.yukmenghafal.model.Ayat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrayAdapterDaftarAyat extends ArrayAdapter<Ayat>{
	private final Context context;
	private final List<Ayat> daftarAyat;
	private String PACKAGE_NAME;
	private int idSurat;
	
	public ArrayAdapterDaftarAyat(Context context, List<Ayat> daftarAyat, int idSurat)
	{
		super (context, R.layout.row_layout_daftar_ayat, daftarAyat);
		this.context = context;
		this.daftarAyat = daftarAyat;
		this.idSurat = idSurat;
	}
	
	@Override
	public View getView(int posisiton, View convertView, ViewGroup parent)
	{
		
		PACKAGE_NAME = context.getPackageName();
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.row_layout_daftar_ayat, parent, false);
		
		TextView tvNomorAyat = (TextView) rowView.findViewById(R.id.nomor);
		tvNomorAyat.setText(daftarAyat.get(posisiton).getId()+"");
		
		ImageView imgAyat = (ImageView) rowView.findViewById(R.id.namaAyat);
		imgAyat.setBackgroundDrawable(generateGambar(idSurat, posisiton));
//		TextView tvAyat = (TextView) rowView.findViewById(R.id.namaAyat);
//		tvAyat.setText(daftarAyat.get(posisiton).getNamaGambarAyat());
		
		return rowView;
	}
	
	public Drawable generateGambar(int nomorSurat, int nomorAyat)
	{
		try {
			Drawable drawable = context.getResources().getDrawable(context.getResources().getIdentifier(daftarAyat.get(nomorAyat).getNamaGambarAyat(),"drawable", PACKAGE_NAME));
			return drawable;
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("pho", "ada masalah "+e.toString());
			
		}
		return null;		
	}
}
