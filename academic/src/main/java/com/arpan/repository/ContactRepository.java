package com.arpan.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.arpan.entities.Address;
import com.arpan.entities.Contact;

public class ContactRepository {

	private DataSource ds;

	public ContactRepository() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:comp/env");
			try {
				ds = (DataSource) envContext.lookup("jdbc/AcademicDB");
			} finally {
				context.close();
			}

		} catch (NamingException ne) {
			throw new RuntimeException();
		}
	}

	public void init() throws SQLException {
		Connection con = ds.getConnection();
		try {
			Statement statement = con.createStatement();
			try {
				statement
						.execute("CREATE TABLE Contact(id int NOT NULL AUTO_INCREMENT,name varchar(50) NOT NULL,address_id int NOT NULL, FOREIGN KEY (address_id) REFERENCES Address)");
			} finally {
				statement.close();
			}

		} finally {
			con.close();
		}

	}

	public Contact find(Long id) throws SQLException {
		Connection con = ds.getConnection();
		try {
			Statement statement = con.createStatement();
			try {
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM Contact WHERE id = " + id);
				if (!resultSet.next()) {
					return null;
				} else {
					return unmarshal(resultSet);
				}
			} finally {
				statement.close();
			}
		} finally {
			con.close();
		}
	}

	private Contact unmarshal(ResultSet resultSet) throws SQLException {
		Contact contact = new Contact();
		contact.setId(resultSet.getLong("id"));
		contact.setName(resultSet.getString("name"));
		contact.setAddressId(resultSet.getLong("address_id"));
		
		return contact;
	}

	public void create(Contact contact) throws SQLException {
		Connection con = ds.getConnection();
		try {
			Statement statement = con.createStatement();
			try {
				statement
						.executeUpdate("INSERT INTO Contact (name,address_id) VALUES ('"
								+ contact.getName()
								+ "','"
								+ contact.getAddressId()
								+ "')",statement.RETURN_GENERATED_KEYS);
				ResultSet resultSet = statement.getGeneratedKeys();
				try {
					if (resultSet.next()) {
						contact.setId(resultSet.getLong(1));
						System.out.println(contact.getId());
					}
				} finally {
					resultSet.close();
				}
			} finally {
				statement.close();
			}


		} finally {
			con.close();
		}

	}

	public void delete(Contact contact) throws SQLException {
		Connection con = ds.getConnection();
		try {
			Statement statement = con.createStatement();
			try {
				statement.executeUpdate("DELETE FROM Contact WHERE id="
						+ contact.getId());

			} finally {
				statement.close();
			}

		} finally {
			con.close();
		}

	}


	public void update(Contact contact) throws SQLException {
		Connection con = ds.getConnection();
		try {
			Statement statement = con.createStatement();
			try {
				 statement.executeUpdate("UPDATE Contact SET name='"
						+ contact.getName()+ "'");
					
			} finally {
				statement.close();
			}

		} finally {
			con.close();
		}

	}

}
