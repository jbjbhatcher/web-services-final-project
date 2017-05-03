package org.webServices.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.webServices.Main;
import org.webServices.models.Customer;
import org.webServices.models.Transaction;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/")

public class CustomerController {

	@GET
	@Path("/customer/view/{id}")
	@Produces("application/json")
	public Response getCustomer(@PathParam(value = "id") int id) {
		ObjectMapper mapper = new ObjectMapper();
		Customer outCustomer;

		outCustomer = Main.customerRepository.getCustomerById(id);

		String mt = new MimetypesFileTypeMap().getContentType(outCustomer.toString());
		try {
			return Response.ok(mapper.writeValueAsString(outCustomer), mt).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	// project requirements

	@POST
	@Path("/customer/create")
	public void insertCustomerIntoDatabase(String inputFromClient) {
		ObjectMapper mapper = new ObjectMapper();
		Customer outCustomer = new Customer();
		System.out.println("\nCreating a customer");

		// IMPORTANT - date must be in this format: yyyy-mm-dd

		try {
			outCustomer = mapper.readValue(inputFromClient, Customer.class);

			Main.customerRepository.insertCustomerIntoDatabase(outCustomer);

		} catch (JsonParseException e) {
			System.err.println("Had a problem parsing JSON");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.err.println("Had a problem mapping JSON");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Some kind of IO exception happened");
			e.printStackTrace();
		}

	}

	@GET
	@Path("/customers/view/{customerName}")
	public Response getCustomersByName(@PathParam(value = "customerName") String customerName) {
		System.out.println("Customers method called. value is" + customerName);
		LinkedList<Customer> outList = new LinkedList<>();
		ObjectMapper mapper = new ObjectMapper();

		Customer outCustomer = new Customer();
		Customer outCustomer2 = new Customer();

		outList.add(outCustomer);
		outList.add(outCustomer2);

		String outString;
		try {
			outString = mapper.writeValueAsString(outList);
			return outString;
		} catch (JsonProcessingException e) {
			System.err.println("Didn't work with returning a list");
			e.printStackTrace();
			return ":(";
		}

	}

	@GET
	@Path("/customer/view/transactions/{id}")
	public Response getTransactions(@PathParam(value = "customerId") int customerId) {
		LinkedList<Transaction> outList = new LinkedList<>();

		String outString = "";

	}

}
