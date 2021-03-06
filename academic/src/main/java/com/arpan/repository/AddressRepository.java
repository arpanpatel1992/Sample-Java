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

public class AddressRepository {

	private DataSource ds;

	public AddressRepository() {
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
						.execute("CREATE TABLE Address(id int NOT NULL AUTO_INCREMENT,street varchar(20) NOT NULL,city varchar(20) NOT NULL,state varchar(20) NOT NULL,zip varchar(20) NOT NULL,PRIMARY KEY (id))");
			} finally {
				statement.close();
			}

		} finally {
			con.close();
		}

	}

	public Address find(Long id) throws SQLException {
		Connection con = ds.getConnection();
		try {
			Statement statement = con.createStatement();
			try {
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM Address WHERE id = " + id);
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

	private Address unmarshal(ResultSet resultSet) throws SQLException {
		Address address = new Address();
		address.setId(resultSet.getLong("id"));
		address.setStreet(resultSet.getString("street"));
		address.setCity(resultSet.getString("city"));
		address.setState(resultSet.getString("state"));
		address.setZip(resultSet.getString("zip"));
		return address;
	}

	public void create(Address address) throws SQLException {
		Connection con = ds.getConnection();
		try {
			Statement statement = con.createStatement();
			try {
				statement
						.executeUpdate("INSERT INTO Address (street,city,state,zip) VALUES ('"
								+ address.getStreet()
								+ "','"
								+ address.getCity()
								+ "','"
								+ address.getState()
								+ "','"
								+ address.getZip()
								+ "')",statement.RETURN_GENERATED_KEYS);
				ResultSet resultSet = statement.getGeneratedKeys();
				try {
					if (resultSet.next()) {
						address.setId(resultSet.getLong(1));
						System.out.println(address.getId());
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

	public void delete(Address address) throws SQLException {
		Connection con = ds.getConnection();
		try {
			Statement statement = con.createStatement();
			try {
				 statement.executeUpdate("DELETE FROM Address WHERE id=" + address.getId());
					
			} finally {
				statement.close();
			}

		} finally {
			con.close();
		}

	}


	public void update(Address address) throws SQLException {
		Connection con = ds.getConnection();
		try {
			Statement statement = con.createStatement();
			try {
				 statement.executeUpdate("UPDATE Address SET street='"
						+ address.getStreet() + "',city='" + address.getCity()
						+ "',state='" + address.getState() + "',zip='"
						+ address.getZip() + "' WHERE id=" + address.getId());
					
			} finally {
				statement.close();
			}

		} finally {
			con.close();
		}

	}

}
