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
public class ContactServlet extends HttpServlet {

	private final AddressRepository addressRepository = new AddressRepository();
	private final ContactRepository contactRepository = new ContactRepository();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter("add") != null) {
				request.getRequestDispatcher("jsp/AddContact.jsp").forward(
						request, response);
			} else {

				// get contact id from request parameter and populate model with
				// the contact and address objects.
				long id = Long.parseLong(request.getParameter("id"));

				Contact contact = contactRepository.find(id);
				Address address = addressRepository
						.find(contact.getAddressId());
				request.setAttribute("contact", contact);
				request.setAttribute("address", address);

				// dispacth either to the edit page or view page
				if (request.getParameter("edit") != null) {
					request.getRequestDispatcher("jsp/editContact.jsp")
							.forward(request, response);
				} else {
					request.getRequestDispatcher("jsp/ViewContact.jsp")
							.forward(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter("add") != null) {
				// create a new addresss and contact from paramaeter and persist
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

				// redirect to contact view page
				response.sendRedirect("contact?id=" + contact.getId());
			} else if (request.getParameter("edit") != null) {

				// look up an existing contact and addresss ,edit fields and
				// persist
				long id = Long.parseLong(request.getParameter("id"));
				Contact contact = contactRepository.find(id);
				// System.out.println();
				contact.setName(request.getParameter("name"));

				System.out.println("Inside Method");
				Address address = addressRepository
						.find(contact.getAddressId());
				address.setStreet(request.getParameter("street"));
				address.setCity(request.getParameter("city"));
				address.setState(request.getParameter("state"));
				address.setZip(request.getParameter("zip"));
				System.out.println(request.getParameter("zip"));

				contactRepository.update(contact);
				addressRepository.update(address);

				response.sendRedirect("contact?id=" + contact.getId());
			} else if (request.getParameter("delete") != null) {
				long id = Long.parseLong(request.getParameter("id"));
				Contact contact = contactRepository.find(id);
				contactRepository.delete(contact);
				Address address = addressRepository
						.find(contact.getAddressId());
				addressRepository.delete(address);
				response.sendRedirect("contacts");
			}

			else {
				super.doPost(request, response);
			}

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}
