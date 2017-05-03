package org.webServices.models;

import java.awt.List;

public class Room {
	public int roomNumber;
	// 1 - 41 - single
	// 41 - 91 - double
	// 91 - 101 - pres

	public String roomType;
	public int cost;
	// id of customer who reserved the room
	private int customerId;

	public Room(int number) throws Exception {
		if (number < 41 && number > 0) {
			this.roomType = "Double";
			this.cost = 100;
		}
		if (number >= 41 && number <= 91) {
			this.roomType = "Single";
			this.cost = 150;
		}
		if (number >= 91 && number <= 101) {
			this.roomType = "Presedential";
			this.cost = 300;
		} else {
			System.out.println("Did something wrong with making a room");
			throw new Exception();
		}
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
