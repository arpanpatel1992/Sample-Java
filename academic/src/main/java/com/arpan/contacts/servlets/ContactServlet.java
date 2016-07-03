package com.arpan.contacts.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arpan.entities.Address;
import com.arpan.entities.Contact;
import com.arpan.repository.AddressRepository;
import com.arpan.repository.ContactRepository;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet{

	private final AddressRepository addressRepository = new AddressRepository();
	private final ContactRepository contactRepository = new ContactRepository();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("add") != null){
			request.getRequestDispatcher("jsp/AddContact.jsp").forward(request, response);
		}
		else{
			super.doGet(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if(request.getParameter("add") != null){
				Address address = new Address();
				address.setCity(request.getParameter("city"));
				address.setStreet(request.getParameter("street"));
				address.setState(request.getParameter("state"));
				address.setZip(request.getParameter("zip"));
				addressRepository.create(address);
				
				Contact contact = new Contact();
				contact.setName(request.getParameter("name"));
				contact.setAddressId(address.getId());
				contactRepository.create(contact);
				
				response.sendRedirect("contacts");
			}
			else
				request.getRequestDispatcher("jsp/AddContact.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
