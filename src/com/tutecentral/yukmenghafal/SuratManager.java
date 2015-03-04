package com.tutecentral.yukmenghafal;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.orm.SugarRecord;
import com.tutecentral.yukmenghafal.model.Ayat;
import com.tutecentral.yukmenghafal.model.Pengguna;
import com.tutecentral.yukmenghafal.model.Surat;
import com.tutecentral.yukmenghafal.utils.JSONParser;

public class SuratManager {
	private static SuratManager instance;
	
	private String dataDir;
	private AssetManager assetManager;
	private Context applicationContext;
	private String[] fileNames;
	private List<Surat> daftarSurat;
	private List<Ayat> daftarAyat;
	private int BUFFER_SIZE = 2048;
	public static void createSuratManager(AssetManager assetManager,
			Context applicationContext) {
		if (instance == null) {
			instance = new SuratManager(assetManager, applicationContext);
		}
	}
	
	public static SuratManager getSuratManager()
	{
		if (instance == null) 
			Log.w("Warning","Museum Manager gak ada masalah instance");
		return instance;
	}
	
	private SuratManager(AssetManager assetManager, Context applicationContext) {
		this.assetManager = assetManager;
		this.applicationContext = applicationContext;
		dataDir = applicationContext.getFilesDir().getAbsolutePath();
		fileNames = new String[]{"1.json","2.json","3.json","4.json","5.json",
				"6.json","7.json","8.json","9.json","10.json"};
		//String fileName = "1.json";
		for(int ii = 0;ii<fileNames.length;ii++)
		{
			try
			{
				File testJSON = new File(dataDir, fileNames[ii]);
				FileOutputStream fos = new FileOutputStream(testJSON.getAbsolutePath());
				InputStream is = new BufferedInputStream(assetManager.open(fileNames[ii]));
				ByteArrayOutputStream buffer =  new ByteArrayOutputStream();
				
				int nRead;
				byte[] data = new byte[BUFFER_SIZE];
				while ((nRead = is.read(data,0,data.length))!=-1) {
					buffer.write(data,0,nRead);
				}
				buffer.flush();
				
				is.close();
				fos.write(buffer.toByteArray());
				fos.close();
			} catch (Exception e)
			{
				Log.d("Museum Manager", "ada masalah "+ fileNames[ii] +" "+e.toString());
			}
			
			daftarSurat = new ArrayList<Surat>();
			File suratJSON = new File(dataDir);
			String[] jsonList = suratJSON.list();
			for (String js : jsonList) {
				Log.d("Surat Manager", "gan suratManager parsing " + js);

				try {
					File f = new File(dataDir, js);
					InputStream is = new BufferedInputStream(new FileInputStream(f));
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();

					int nRead;
					byte[] data = new byte[BUFFER_SIZE];
					while ((nRead = is.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}
					buffer.flush();

					String json = buffer.toString();

					//Log.d("MuseumManager", "gan content:" + json);
					

					Surat s = JSONParser.toSurat(json);
					daftarSurat.add(s);

				} catch (Exception e) {
					Log.d("MuseumManager", "gan MuseumManager error parsing " + js);
				}
			}	
		}
	}
	
	public Context getContext()
	{
		return applicationContext;
	}
	
	public AssetManager getAssetManager()
	{
		return assetManager;
	}
	
	public List<Surat> getDaftarSurat()
	{
		return daftarSurat;
	}
	
	public Surat getSurat(int id)
	{
		Surat s = null;
		for (Surat a : daftarSurat)
		{
			if(s.getId() == a.getId())
			{
				s = a;
			}
		}
		return s;
	}
	
	public List<Ayat> getDaftarAyat(int nomor)
	{
		List<Ayat> daftar = new ArrayList<Ayat>();
				
		for(Surat a : daftarSurat)
		{
			if(a.getId()==nomor)
			{
				Log.d("nomor : ", Integer.toString(nomor));
				List<Ayat>  tmp = a.getDaftarAyat();
				daftar.addAll(tmp);
			}
		}
		
		return daftar;
	}
	public int AyatSelesai(int nomorSurat)
	{
		List<Ayat> daftar = new ArrayList<Ayat>();
		int jumlahAyatSelesai = 0;
		for(Surat a : daftarSurat)
		{
			if(a.getId()==nomorSurat)
			{				
				if(a.getStatusSelesai()) jumlahAyatSelesai++;
			}
		}
		return jumlahAyatSelesai;
	}
	public void updateDatabase(Surat surat)
	{
		try {
			String fileName = "" + (surat.getId()+1);
			File suratJSON = new File(dataDir, fileName);
			FileOutputStream fos = new FileOutputStream(suratJSON.getAbsolutePath());
			fos.write(JSONParser.toJSON(surat).getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("Pho","ada masalah "+e.toString());
		}
	}
	public void updatePengguna(Pengguna pengguna)
	{
		try {
			String fileName = "" + (pengguna.getName());
			File suratJSON = new File(dataDir, fileName);
			FileOutputStream fos = new FileOutputStream(suratJSON.getAbsolutePath());
			fos.write(JSONParser.toJSON(pengguna).getBytes());
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("Pho","ada masalah "+e.toString());
		}
	}
	public Ayat getAyat(int id)
	{
		return null;
	}
}
