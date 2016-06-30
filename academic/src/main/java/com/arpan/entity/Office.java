package com.arpan.entity;

public class Office extends UrlEntity{

	private String name;
	
	private Address address;
	
	private Company company;
	
	public Office() {
		// TODO Auto-generated constructor stub
	}

	public Office(String name, Address address, Company company) {
		super();
		this.name = name;
		this.address = address;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	
}
