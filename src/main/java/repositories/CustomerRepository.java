package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.webServices.models.Customer;

public class CustomerRepository {
	String url = "jdbc:mysql://localhost:3306/hotel";
	String username = "java";
	String password = "password";

	// maintain a list of customers

	// do database interactions here
	public Customer getCustomerById(int id) {
		Customer outCustomer = new Customer();

		System.out.println("pls connect to database");

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected! (woohoo)");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE id=" + id + ";");

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
				outCustomer = customer;
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database! (fuck)", e);
		}
		return outCustomer;
	}

	public boolean insertCustomerIntoDatabase(Customer inCustomer) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected! (woohoo)");

			// insert query for customers
			String query = "INSERT INTO customers(firstname,lastname,phonenumber,billingaddress,billingcity,billingstate,billingzip,checkindate,checkoutdate)"
					+ "VALUES (?,?,?,?,?,?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, inCustomer.getFirstName());
			preparedStatement.setString(2, inCustomer.getLastName());
			preparedStatement.setString(3, inCustomer.getPhoneNumber());
			preparedStatement.setString(4, inCustomer.getBillingAddress());
			preparedStatement.setString(5, inCustomer.getBillingCity());
			preparedStatement.setString(6, inCustomer.getBillingState());
			preparedStatement.setString(7, inCustomer.getBillingZip());
			preparedStatement.setDate(8, inCustomer.getCheckinDate());
			preparedStatement.setDate(9, inCustomer.getCheckoutDate());

			preparedStatement.execute();
			// close everything after execution because i'm a conformist
			preparedStatement.close();
			connection.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Customer> getCustomersByName() {
		LinkedList<Customer> outCustomerList = new LinkedList();

		return outCustomerList;
	}

}
