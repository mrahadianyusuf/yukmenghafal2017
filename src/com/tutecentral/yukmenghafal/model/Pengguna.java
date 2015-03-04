package com.tutecentral.yukmenghafal.model;

public class Pengguna {	
	private String name;
	private String age;
	private int id;

	public Pengguna(int i, String n, String k) {		
		name = n;
		age = k;
		id = i;
	}	

	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}
	
	public int getID(){
		return id;
	}

}
