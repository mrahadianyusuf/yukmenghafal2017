package com.tutecentral.yukmenghafal.utils;

import java.util.List;

import com.tutecentral.yukmenghafal.R;
import com.tutecentral.yukmenghafal.model.Surat;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrayAdapterDaftarSurat extends ArrayAdapter<Surat> {
	private final Context context;
	private final List<Surat> daftarSurat;
	
	public ArrayAdapterDaftarSurat(Context context, List<Surat> daftarSurat) {
		super(context, R.layout.row_layout_daftar_surat, daftarSurat);
		this.context = context;
		this.daftarSurat = daftarSurat;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
//		Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/opensans.ttf");
		View rowView = inflater.inflate(R.layout.row_layout_daftar_surat, parent, false);
		
		TextView tvNomorSurat = (TextView) rowView.findViewById(R.id.nomor);
		tvNomorSurat.setText(115-daftarSurat.get(position).getId()+"   ");
		/*tvNomorSurat.setTypeface(font);*/
		
		TextView tvNamaSurat = (TextView) rowView.findViewById(R.id.namaSurat);
		tvNamaSurat.setText(daftarSurat.get(position).getNamaSurat());
		/*tvNamaSurat.setTypeface(font);*/
		
		TextView tvArtiSurat = (TextView) rowView.findViewById(R.id.artiSurat);
		tvArtiSurat.setText(daftarSurat.get(position).getArtiSurat());
		/*tvArtiSurat.setTypeface(font);*/
		
		TextView tvDaftarAyat = (TextView) rowView.findViewById(R.id.jumlahAyat);
		tvDaftarAyat.setText(daftarSurat.get(position).getDaftarAyat().size()+" Ayat");
		/*tvDaftarAyat.setTypeface(font);*/
		
//		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		
//		Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu.ttf");
//		textView.setTypeface(font);
		return rowView;
	}
}
