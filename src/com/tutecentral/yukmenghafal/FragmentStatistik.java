package com.tutecentral.yukmenghafal;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tutecentral.yukmenghafal.R;
import com.tutecentral.yukmenghafal.R.id;
import com.tutecentral.yukmenghafal.R.layout;

import android.R.drawable;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentStatistik extends Fragment {

	ImageView ivIcon;
	TextView tvItemName;
	ImageView foto;
	ImageButton sex;
	private Utility util = new Utility();	
	TextView namaPengguna;
	TextView umurPengguna;
	
	public FragmentStatistik() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_statistik, container,
				false);
		
		foto = (ImageView) view.findViewById(R.id.foto);
		Bitmap gettumb = util.getThumbnail("desiredFilename.png", getActivity());
		foto.setImageBitmap(gettumb);	
		
		namaPengguna = (TextView) view.findViewById(R.id.namaText);
		umurPengguna = (TextView) view.findViewById(R.id.umur);
		
		namaPengguna.setText(readNama());
		umurPengguna.setText(readUmur() + " Tahun");
				
		return view;
	}
	
	public String readUmur() {				
		String umur = "UmurPengguna";
		StringBuffer stringBuffer = new StringBuffer();
		try {
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(getActivity().openFileInput(umur)));
			String inputString;

			while ((inputString = inputReader.readLine()) != null) {
				stringBuffer.append(inputString);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return stringBuffer.toString(); 
	}
	
	public String readNama() {				
		String nama = "NamaPengguna";
		StringBuffer stringBuffer = new StringBuffer();
		try {
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(getActivity().openFileInput(nama)));
			String inputString;

			while ((inputString = inputReader.readLine()) != null) {
				stringBuffer.append(inputString);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return stringBuffer.toString(); 
	}

}
