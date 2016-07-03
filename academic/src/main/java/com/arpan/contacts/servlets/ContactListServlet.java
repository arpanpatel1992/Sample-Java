package com.arpan.contacts.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arpan.repository.ContactRepository;

@WebServlet("/contacts")
public class ContactListServlet extends HttpServlet {

	private final ContactRepository contactRepsitory = new ContactRepository();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("contacts", contactRepsitory.findAll());
			RequestDispatcher view = request
					.getRequestDispatcher("jsp/ContactList.jsp");
			view.forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}

	}

}
