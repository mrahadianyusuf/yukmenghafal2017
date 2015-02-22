package com.tutecentral.yukmenghafal;

import com.tutecentral.yukmenghafal.R;
import com.tutecentral.yukmenghafal.R.id;
import com.tutecentral.yukmenghafal.R.layout;

import android.R.drawable;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentProfil extends Fragment {

	ImageView ivIcon;
	TextView tvItemName;
	ImageButton foto;
	ImageButton sex;
	EditText nama;
	EditText umur;
	
	public FragmentProfil() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_profil, container,
				false);
		
		foto = (ImageButton) view.findViewById(R.id.foto);
		sex = (ImageButton) view.findViewById(R.id.changeColor);
		nama = (EditText) view.findViewById(R.id.nama);
		umur = (EditText) view.findViewById(R.id.umur);			
		return view;
	}

}
