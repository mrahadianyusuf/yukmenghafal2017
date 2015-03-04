package com.tutecentral.yukmenghafal.controller;

import java.util.List;

import com.tutecentral.yukmenghafal.DaftarSurat;
import com.tutecentral.yukmenghafal.SuratManager;
import com.tutecentral.yukmenghafal.model.Ayat;
import com.tutecentral.yukmenghafal.model.Surat;

public class ControllerAyat {
	private SuratManager suratManager;
	
	public ControllerAyat()
	{
		suratManager = SuratManager.getSuratManager();
	}
	
	public List<Ayat> getDaftarAyat(int nomor)
	{
		return suratManager.getDaftarAyat(nomor);
	}
	
	public List<Surat> getDaftarSurat()
	{
		return suratManager.getDaftarSurat();
	}
	
	public boolean cekStatusSelesai(int idAyat)
	{
		Ayat a = suratManager.getAyat(idAyat);
		return a.getStatusSelesai();
	}
	
	public boolean cekStatusBookmark(int idAyat)
	{
		Ayat a = suratManager.getAyat(idAyat);
		return a.getStatusBookmark();
	}
	
	public String getGambarVisual(int nomorSurat, int nomorAyat)
	{
		return suratManager.getDaftarAyat(nomorSurat).get(nomorAyat).getNamaGambarVisual();
	}
	
	public String getGambarAyat(int nomorSurat, int nomorAyat)
	{
		return suratManager.getDaftarAyat(nomorSurat).get(nomorAyat).getNamaGambarAyat();
	}
}
