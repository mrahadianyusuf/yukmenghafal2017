package com.tutecentral.yukmenghafal.controller;

import java.util.List;

import com.tutecentral.yukmenghafal.SuratManager;
import com.tutecentral.yukmenghafal.model.Surat;

public class ControllerDaftarSurat {
	private SuratManager suratManager;
	
	public ControllerDaftarSurat()
	{
		suratManager = SuratManager.getSuratManager();
	}
	
	public List<Surat> getDaftarSurat()
	{
		
		return suratManager.getDaftarSurat();
	}
	
	public boolean cekStatusBookmark(int idSurat)
	{
		Surat s = suratManager.getSurat(idSurat);
		return s.getStatusBookmark();
	}
}
