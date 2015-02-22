package com.tutecentral.yukmenghafal.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

import com.tutecentral.yukmenghafal.model.Ayat;
import com.tutecentral.yukmenghafal.model.Surat;

public class JSONParser {
	public static final String SURAT_ID = "_id";
	public static final String SURAT_NAMA = "nama";
	public static final String SURAT_ARTI = "arti";
	public static final String SURAT_STATUS_BOOKMARK ="status";
	public static final String SURAT_DAFTAR_AYAT = "daftar_ayat";
	
	public static final String AYAT_ID_SURAT = "_id_surat";
	public static final String AYAT_ID = "_id";
	public static final String AYAT_NAMA_GAMBAR = "nama_gambar";
	public static final String AYAT_NAMA_AYAT = "nama_ayat";
	public static final String AYAT_TERJEMAHAN = "terjemahan";
	public static final String AYAT_STATUS_BOOKMARK = "status";
	
	public static Surat toSurat(String suratJSON)
	{
		Surat s = null;
		try {
			s = toSurat(new JSONObject(suratJSON));
		} catch (Exception e) {
			Log.d("JSON Parser", "json bermasalah");
		}
		return s;
	}
	
	public static Ayat toAyat(String ayatJSON)
	{
		Ayat a = null;
		try {
			a = toAyat(new JSONObject(ayatJSON));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
	
	public static Surat toSurat(JSONObject obj) throws JSONException
	{
		int id = obj.getInt(SURAT_ID);
		String nama = obj.getString(SURAT_NAMA);
		String arti = obj.getString(SURAT_ARTI);
		boolean status = obj.getBoolean(SURAT_STATUS_BOOKMARK);
		List<Ayat> daftarAyat = new ArrayList<Ayat>();
		
		JSONArray daftarAyatJSON = new JSONArray(obj.get(SURAT_DAFTAR_AYAT).toString());
		for(int ii = 0; ii < daftarAyatJSON.length(); ii++)
		{
			JSONObject ayatJSON = daftarAyatJSON.getJSONObject(ii);
			daftarAyat.add(toAyat(ayatJSON));
		}
		Surat s = new Surat(id, nama, arti, daftarAyat, status);
		return s;
	}
	
	public static Ayat toAyat(JSONObject obj) throws JSONException
	{
		int idSurat = obj.getInt(AYAT_ID_SURAT);
		int id = obj.getInt(AYAT_ID);
		String namaGambar = obj.getString(AYAT_NAMA_GAMBAR);
		String namaAyat = obj.getString(AYAT_NAMA_AYAT);
		String terjemahan = obj.getString(AYAT_TERJEMAHAN);
		Boolean status = obj.getBoolean(AYAT_STATUS_BOOKMARK);
		
		Ayat a = new Ayat(idSurat, id, namaGambar, namaAyat, terjemahan, status);
		return a;
	}
	
	public static String toJSON(Surat s)
	{
		String jsonnya = "";
		try {
			JSONObject obj = new JSONObject();
			obj.put(SURAT_ID, s.getId());
			obj.put(SURAT_NAMA, s.getNamaSurat());
			obj.put(SURAT_ARTI, s.getArtiSurat());
			obj.put(SURAT_STATUS_BOOKMARK, s.getStatusBookmark());
			
			JSONArray daftarAyatJSON = new JSONArray();
			List<Ayat> daftarAyat = s.getDaftarAyat();
			for(Ayat a : daftarAyat)
			{
				JSONObject jsonObj = new JSONObject(toJSON(a));
				daftarAyatJSON.put(jsonObj);
			}
			obj.put(SURAT_DAFTAR_AYAT, daftarAyatJSON);
			jsonnya = obj.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonnya;
	}
	
	public static String toJSON(Ayat a)
	{
		String jsonnya = "";
		try {
			JSONObject obj = new JSONObject();
			obj.put(AYAT_ID_SURAT, a.getIdSurat());
			obj.put(AYAT_ID, a.getId());
			obj.put(AYAT_NAMA_GAMBAR, a.getNamaGambarVisual());
			obj.put(AYAT_NAMA_AYAT, a.getNamaGambarAyat());
			obj.put(AYAT_TERJEMAHAN, a.getTerjemahan());
			obj.put(AYAT_STATUS_BOOKMARK, a.getStatusBookmark());
			
			jsonnya = obj.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonnya;
	}
}
