package com.tutecentral.yukmenghafal.model;

public class Pengguna {	
	private String name;
	private String age;
	private int id;

	public Pengguna() {
    }
	
	public Pengguna(int i, String n, String k) {		
		this.name = n;
		this.age = k;
		this.id = i;
	}	

	public String getNama() {
		return name;
	}

	public String getUmur() {
		return age;
	}
	
	public int getID(){
		return id;
	}
	
	public void setNama(String nm) {
		this.name = nm;
	}

	public void setUmur(String ag) {
		this.age = ag;
	}
	
	public void setID(int id){
		this.id = id;
	}

	
	

}
