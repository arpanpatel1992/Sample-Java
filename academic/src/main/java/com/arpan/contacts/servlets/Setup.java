package com.arpan.contacts.servlets;

import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

import com.arpan.entities.Address;
import com.arpan.entities.Contact;
import com.arpan.repository.AddressRepository;
import com.arpan.repository.ContactRepository;

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
		try {
			
			new AddressRepository().init();
			new AddressRepository().create(new Address("600 Langsdorf Dr", "Fullerton", "CA", "92831"));
			new ContactRepository().init();
			new ContactRepository().create(new Contact("arpan",1L));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	
	
}
