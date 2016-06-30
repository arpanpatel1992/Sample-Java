package com.arpan.entity;

public class Contact extends UrlEntity{
	
	private String name;

	public Contact() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Contact(String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
