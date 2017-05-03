package org.webServices.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

import org.webServices.*;
import org.webServices.models.Customer;

public class MysqlConnector {

	public void readCustomers() {
		String url = "jdbc:mysql://localhost:3306/hotel";
		String username = "java";
		String password = "password";

		System.out.println("pls connect to database");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected! (woohoo)");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM customers;");

			System.out.println("Trying to print out the result set:");

			while (rs.next()) {
				System.out.println(rs.getString(1)); // gets the first column's
														// rows.
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database! (fuck)", e);
		}
	}

	public LinkedList<Customer> createCustomerObjectsFromDatabase() {
		String url = "jdbc:mysql://localhost:3306/hotel";
		String username = "java";
		String password = "password";
		LinkedList<Customer> customerList = new LinkedList<Customer>();

		System.out.println("pls connect to database");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected! (woohoo)");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM customers;");

			System.out.println("Trying to create objects:");

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setPhoneNumber(rs.getString(4));
				customer.setBillingAddress(rs.getString(5));
				customer.setBillingCity(rs.getString(6));
				customer.setBillingState(rs.getString(7));
				customer.setBillingZip(rs.getString(8));
				customer.setCheckinDate(rs.getDate(9));
				customer.setCheckoutDate(rs.getDate(10));
				customerList.add(customer);
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return customerList;
	}

	public void insertCustomerIntoDatabase(Customer inCustomer) {
		String url = "jdbc:mysql://localhost:3306/hotel";
		String username = "java";
		String password = "password";

		System.out.println("pls connect to database");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected! (woohoo)");

			// insert query for customers
			String query = "INSERT INTO customers(id,firstname,lastname,phonenumber,billingaddress,billingcity,billingstate,billingzip,checkindate,checkoutdate)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, 4);
			preparedStatement.setString(2, "testInsert");
			preparedStatement.setString(3, "testInsert");
			preparedStatement.setString(4, "testInsert");
			preparedStatement.setString(5, "testInsert");
			preparedStatement.setString(6, "testInsert");
			preparedStatement.setString(7, "testInsert");
			preparedStatement.setString(8, "testInsert");
			preparedStatement.setString(9, "2011-01-01");
			preparedStatement.setString(10, "2011-01-01");

			preparedStatement.execute();
			// close everything after execution because i'm a conformist
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void testConn() {
		String url = "jdbc:mysql://localhost:3306/hotel";
		String username = "java";
		String password = "password";

		System.out.println("pls connect to database");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected! (woohoo)");
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database! (fuck)", e);
		}

	}
}
