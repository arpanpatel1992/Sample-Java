package com.arpan.contacts.servlets;

import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.arpan.entities.Address;
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
			new ContactRepository().init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
