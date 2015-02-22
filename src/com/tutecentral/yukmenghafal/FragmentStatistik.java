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

public class FragmentStatistik extends Fragment {

	ImageView ivIcon;
	TextView tvItemName;
	ImageButton foto;
	ImageButton sex;
	EditText nama;
	EditText umur;
	
	public FragmentStatistik() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_statistik, container,
				false);
		
				
		return view;
	}

}
