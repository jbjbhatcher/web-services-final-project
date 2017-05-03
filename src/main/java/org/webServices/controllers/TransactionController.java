package org.webServices.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/transactionController")

public class TransactionController {

	// project requirements
	@POST
	@Path("/transaction/new/")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createPayment(String jsonRequest) {

		return true;
	}
}
