package com.arpan.contacts.servlets;

import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.arpan.entities.Address;
import com.arpan.repository.AddressRepository;

/**
 * @author arpan
 *
 */
@WebListener
public class Setup implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//instiate address repository
		//init
		//go nuts!!
		AddressRepository addressRepository = new AddressRepository();
		try {
			addressRepository.init();
			Address address = new Address("600 Langsdorf Dr","Fullerton","CA","92831");
			addressRepository.create(address);
			System.out.println("Done");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
