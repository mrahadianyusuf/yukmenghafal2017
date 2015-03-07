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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrayAdapterDaftarAyat extends ArrayAdapter<Ayat>{
	private final Context context;
	private final List<Ayat> daftarAyat;
	private String PACKAGE_NAME;
	private int idSurat;
	
	private Button button_love;
	private boolean status_love;
	public ArrayAdapterDaftarAyat(Context context, List<Ayat> daftarAyat, int idSurat)
	{
		super (context, R.layout.row_layout_daftar_ayat, daftarAyat);
		this.context = context;
		this.daftarAyat = daftarAyat;
		this.idSurat = idSurat;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final int posisi = position;
		PACKAGE_NAME = context.getPackageName();
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.row_layout_daftar_ayat, parent, false);
		status_love = daftarAyat.get(position).getStatusBookmark();
		button_love = (Button) rowView.findViewById(R.id.button_love_ayat);
		button_love.setTag(posisi);
		setButtonBackround(position);
		
		TextView tvNomorAyat = (TextView) rowView.findViewById(R.id.nomor);
		tvNomorAyat.setText(daftarAyat.get(position).getId()+"");
		
		ImageView imgAyat = (ImageView) rowView.findViewById(R.id.namaAyat);
		imgAyat.setBackgroundDrawable(generateGambar(idSurat, position));
		
		button_love.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getId()==posisi) {
					
				}
				if(status_love){
					Log.d("oho","ada masalah "+posisi);
	        		
					button_love.setBackgroundResource(R.drawable.hafal_white);
	        		status_love = false;
	        		daftarAyat.get(posisi).setStatusSelesai(status_love);
	        	}
	        	else 
	        	{
	        		button_love.setBackgroundResource(R.drawable.hafal_pink);
	        		status_love = true;
	        		daftarAyat.get(posisi).setStatusSelesai(status_love);
	        	}
			}
		});
		
		return rowView;
	}
	
	public void setButtonBackround(int position)
	{
		if(daftarAyat.get(position).getStatusSelesai())
			button_love.setBackgroundResource(R.drawable.hafal_pink);
		else 
			button_love.setBackgroundResource(R.drawable.hafal_white);
			
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
