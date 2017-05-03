package org.webServices;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.webServices.controllers.CustomerController;
import org.webServices.models.*;

import org.webServices.utilities.MysqlConnector;

import repositories.CustomerRepository;
import repositories.RoomRepository;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;

public class Main {
	// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI = "http://localhost:8080/hotel/";

	public MysqlConnector connector;

	// static instance of the object that will deal with all of the database
	// connection handling
	public static CustomerRepository customerRepository;
	public static RoomRepository roomRepository;

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and
		// providers
		// in org.webServices package
		final ResourceConfig rc = new ResourceConfig().packages("org.webServices");

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws IOException
	 */

	public Main() {
		connector = new MysqlConnector();
		customerRepository = new CustomerRepository();
		roomRepository = new RoomRepository();
	}

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		final HttpServer server = startServer();
		System.out.println(String.format(
				"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
				BASE_URI));
		System.in.read();

	}
}
