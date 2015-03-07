package com.tutecentral.yukmenghafal.model;

import android.R.bool;

public class Ayat {
	private int idSurat;
	private int id;
	private boolean statusBookmark;
	private boolean statusSelesai;
	private String namaGambarVisual;
	private String namaGambarAyat;
	private String terjemahan;
	
	public Ayat(int idSurat, int id, String namaGambarVisual, String namaGambarAyat, 
			String terjemahan, boolean statusBookmark, boolean statusSelesai)
	{
		this.idSurat = idSurat;
		this.id = id;
		this.statusBookmark = statusBookmark;
		this.namaGambarAyat = namaGambarAyat;
		this.namaGambarVisual = namaGambarVisual;
		this.terjemahan = terjemahan;
		this.statusSelesai = statusSelesai;
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
	
	public void setStatusBookmark(boolean status_bookmark)
	{
		statusBookmark = status_bookmark;
	}
	
	public boolean getStatusSelesai()
	{
		return statusSelesai;
	}
	
	public void setStatusSelesai(boolean status_selesai)
	{
		statusSelesai = status_selesai;
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