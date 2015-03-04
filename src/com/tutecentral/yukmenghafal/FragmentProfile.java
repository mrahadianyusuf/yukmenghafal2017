package com.tutecentral.yukmenghafal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;





import com.tutecentral.yukmenghafal.R;
import com.tutecentral.yukmenghafal.R.id;
import com.tutecentral.yukmenghafal.R.layout;
import com.tutecentral.yukmenghafal.Utility;
import com.tutecentral.yukmenghafal.model.Pengguna;
import com.tutecentral.yukmenghafal.utils.JSONParser;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentProfile extends Fragment {

	ImageView ivIcon;
	TextView tvItemName;
	EditText namaText;
	EditText umurText;
	private static Bitmap Image = null;
	private static Bitmap rotateImage = null;
	private ImageView imageView;
	ImageButton save;
	
	private static final int GALLERY = 1;
	private Utility util = new Utility();

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";
	private String namaPengguna;
	private String umurPengguna;
	private String json;
	public FragmentProfile() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_profile2, container,
				false);
		
		imageView = (ImageView) view.findViewById(R.id.imageView1);
		namaText = (EditText) view.findViewById(R.id.nama);
		umurText = (EditText) view.findViewById(R.id.umur);
		save = (ImageButton) view.findViewById(R.id.button2);
		Bitmap gettumb = util.getThumbnail("desiredFilename.png", getActivity());
		imageView.setImageBitmap(gettumb);
		
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imageView.setImageBitmap(null);
				if (Image != null)
					Image.recycle();
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(
						Intent.createChooser(intent, "Select Picture"), GALLERY);
			}
		});
		
		namaPengguna = namaText.getText().toString();
		umurPengguna = umurText.getText().toString();
		
			
		
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int id = 1;
				Pengguna user = new Pengguna(id, namaPengguna, umurPengguna);
				json = JSONParser.toJSON(user);
				
			}
		});
		
		Pengguna newUser = JSONParser.toPengguna(json);
		
		
		Log.d("Phi","ada masalah "+namaPengguna.equals(""));
		if(namaPengguna.equals("")){
			namaText.setHint("Nama Anda");
			umurText.setHint("Umur Anda");
			
		}else{
			namaText.setText(newUser.getName());
			umurText.setText(newUser.getAge());
		}
		
		
		
		return view;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == GALLERY && resultCode != 0) {
			Uri mImageUri = data.getData();
			try {

				Image = Media.getBitmap(getActivity().getContentResolver(),
						mImageUri);
				if (getOrientation(getActivity().getApplicationContext(),
						mImageUri) != 0) {
					Matrix matrix = new Matrix();
					matrix.postRotate(getOrientation(getActivity()
							.getApplicationContext(), mImageUri));
					if (rotateImage != null)
						rotateImage.recycle();
					rotateImage = Bitmap.createBitmap(Image, 0, 0,
							Image.getWidth(), Image.getHeight(), matrix, true);
					imageView.setImageBitmap(rotateImage);
					util.saveImageToInternalStorage(rotateImage, getActivity());
				} else
					imageView.setImageBitmap(Image);
				util.saveImageToInternalStorage(Image, getActivity());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static int getOrientation(Context context, Uri photoUri) {
		/* it's on the external media. */
		Cursor cursor = context.getContentResolver().query(photoUri,
				new String[] { MediaStore.Images.ImageColumns.ORIENTATION },
				null, null, null);

		if (cursor.getCount() != 1) {
			return -1;
		}

		cursor.moveToFirst();
		return cursor.getInt(0);
	}

	
}