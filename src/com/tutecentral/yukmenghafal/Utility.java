package com.tutecentral.yukmenghafal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Utility {
	public boolean saveImageToInternalStorage(Bitmap image, Activity act) {

		try {
			// Use the compress method on the Bitmap object to write image to
			// the OutputStream
			FileOutputStream fos = act.openFileOutput(
					"desiredFilename.png", Context.MODE_PRIVATE);

			// Writing the bitmap to the output stream
			image.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();

			return true;
		} catch (Exception e) {
			Log.e("saveToInternalStorage()", e.getMessage());
			return false;
		}
	}

	public Bitmap getThumbnail(String filename, Activity act) {
		Bitmap thumbnail = null;
		// If no file on external storage, look in internal storage
		if (thumbnail == null) {
			try {
				File filePath = act.getFileStreamPath(filename);
				FileInputStream fi = new FileInputStream(filePath);
				thumbnail = BitmapFactory.decodeStream(fi);
			} catch (Exception ex) {
				Log.e("getThumbnail() on internal storage", ex.getMessage());
			}
		}
		return thumbnail;
	}
}
