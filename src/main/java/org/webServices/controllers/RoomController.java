package org.webServices.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.webServices.Main;
import org.webServices.models.Customer;
import org.webServices.models.Room;

@Path("/roomController")

public class RoomController {

	// @POST
	// @Path("/room/reserve")
	// public boolean reserveRoom(Customer inCustomer, int roomNumber) {
	// if (Main.roomRepository.vacantRoomList.contains(roomNumber)) {
	// System.out.println("Room is vacant");
	//
	// Main.roomRepository.reserveRoom(inCustomer, roomNumber);
	//
	// return true;
	// }
	// // If the room is not in the list:
	// else {
	// System.out.println("Room is occupied");
	// // ask to reserve another number
	// return false;
	// }
	// }

	@GET
	@Path("/room/view/vacancies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> getVacancies() {
		return Main.roomRepository.vacantRoomList;
	}

	@GET
	@Path("/room/view/reservations")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> getReservations() {
		return Main.roomRepository.occupiedRoomList;
	}

}
