package com.arpan.entity;

public class Person extends Contact {

	private Address address;
	
	private Company employer;
	
	private Person manager;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(Company employer, Address address, Person manager) {
		super();
		this.employer = employer;
		this.address = address;
		this.manager = manager;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getEmployer() {
		return employer;
	}

	public void setEmployer(Company employer) {
		this.employer = employer;
	}

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}
	
	
	
}
