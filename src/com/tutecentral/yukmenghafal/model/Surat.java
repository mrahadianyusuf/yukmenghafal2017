package com.tutecentral.yukmenghafal.model;

import java.util.List;

public class Surat {
	private int id;
	private String namaSurat;
	private String artiSurat;
	private List<Ayat> daftarAyat;
	private boolean statusBookmark;
	
	public Surat(int id, String namaSurat, String artiSurat, 
			List<Ayat> daftarAyat, boolean statusBookmark)
	{
		this.id = id;
		this.namaSurat = namaSurat;
		this.artiSurat = artiSurat;
		this.daftarAyat = daftarAyat;
		this.statusBookmark = statusBookmark;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getNamaSurat()
	{
		return namaSurat;
	}
	public String getArtiSurat()
	{
		return artiSurat;
	}
	
	public List<Ayat> getDaftarAyat()
	{
		return daftarAyat;
	}
	
	public boolean getStatusBookmark()
	{
		return statusBookmark;
	}
	
	public Ayat getAyat(int id)
	{
		Ayat ret = null;
		for (Ayat a : daftarAyat) {
			if(a.getId() == id){
				ret = a;
				break;
			}
		}
		return ret;
	}
}
