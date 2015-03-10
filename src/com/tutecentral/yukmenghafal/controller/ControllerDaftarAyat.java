package com.tutecentral.yukmenghafal.controller;

import java.util.List;

import android.R.bool;

import com.tutecentral.yukmenghafal.SuratManager;
import com.tutecentral.yukmenghafal.model.Ayat;
import com.tutecentral.yukmenghafal.model.Surat;

public class ControllerDaftarAyat {
	private SuratManager suratManager;
	
	public ControllerDaftarAyat()
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
	public void ubahSelesai(int nomorSurat)
	{
		suratManager.updateDatabase(suratManager.getDaftarSurat().get(nomorSurat));
	}
	
	public void hapusBookmark()
	{
		suratManager.hapusBookmark();
	}
}
