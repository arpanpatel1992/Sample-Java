package com.arpan.entity;

import java.util.Set;

public class Company extends Contact{

	private Set<Office> offices;
	
	public Company() {
		// TODO Auto-generated constructor stub
	}

		
	public Company(Set<Office> offices) {
		super();
		this.offices = offices;
	}


	public Set<Office> getOffices() {
		return offices;
	}

	public void setOffices(Set<Office> offices) {
		this.offices = offices;
	}
	
	
}
