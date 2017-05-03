package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import org.webServices.models.Customer;
import org.webServices.models.Room;

public class RoomRepository {
	// array of all rooms
	public ArrayList<Room> roomList = new ArrayList<>(100);
	// array of available rooms
	public LinkedList<Room> vacantRoomList;
	// array of occupied rooms
	public LinkedList<Room> occupiedRoomList;

	// sql information
	String url = "jdbc:mysql://localhost:3306/hotel";
	String username = "java";
	String password = "password";

	// do database interactions here
	public void reserveRoom(Customer inCustomer, int roomNumber) {
		Room room = roomList.get(roomNumber);
		room.setCustomerId(inCustomer.getId());

		this.vacantRoomList.remove(room);
		this.occupiedRoomList.add(room);
		this.roomList.set(roomNumber, room);

		updateRoomListInDatabase(room);
	}

	public void updateRoomListInDatabase(Room room) {

	}

	public void init() {
		populateRoomList();
	}

	public void populateRoomList() {
		// setup the roomLIst with data from DB
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected! (woohoo)");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM rooms;");

			while (rs.next()) {
				Room roomToBeAddedToList = new Room(rs.getInt(0));

				// set the customerID to the corresponding column in the
				// database
				roomToBeAddedToList.setCustomerId(rs.getInt(1));

				this.roomList.add(roomToBeAddedToList);

			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (Exception e) {
			throw new IllegalStateException("Cannot connect the database! (fuck)", e);
		}
		for (int i = 1; i < 101; i++) {

		}

		this.roomList = null;
	}

}
