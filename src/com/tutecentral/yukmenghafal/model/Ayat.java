package com.tutecentral.yukmenghafal.model;

import android.R.bool;

public class Ayat {
	private int idSurat;
	private int id;
	private boolean statusBookmark;
	private String namaGambarVisual;
	private String namaGambarAyat;
	private String terjemahan;
	
	public Ayat(int idSurat, int id, String namaGambarVisual, String namaGambarAyat, 
			String terjemahan, boolean statusBookmark)
	{
		this.idSurat = idSurat;
		this.id = id;
		this.statusBookmark = statusBookmark;
		this.namaGambarAyat = namaGambarAyat;
		this.namaGambarVisual = namaGambarVisual;
		this.terjemahan = terjemahan;
	}
	
	public int getIdSurat()
	{
		return idSurat;	
	}
	
	public int getId()
	{
		return id;
	}
	
	public boolean getStatusBookmark()
	{
		return statusBookmark;
	}
	
	public String getNamaGambarAyat()
	{
		return namaGambarAyat;
	}
	
	public String getNamaGambarVisual()
	{
		return namaGambarVisual;
	}
	
	public String getTerjemahan()
	{
		return terjemahan;
	}
}