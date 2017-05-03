package org.webServices.models;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String billingAddress;
	private String billingCity;
	private String billingState;
	private String billingZip;
	private Date checkinDate;
	private Date checkoutDate;

	public Customer() {
	}

	public Customer(int id, String inFirstName) {
		this.id = id;
		this.firstName = inFirstName;
	}

	public String toString() {
		String output = "";
		output += "name: " + this.firstName;
		output += "\nlast name: " + this.lastName;
		output += "\nphoneNumber: " + this.phoneNumber;
		output += "\nbillingAddress: " + this.billingAddress;
		output += "\nbillingCity: " + this.billingCity;
		output += "\nbillingState: " + this.billingState;
		output += "\ncheckinDate: " + this.checkinDate;
		output += "\ncheckoutDate: " + this.checkoutDate;
		output += "\n";
		return output;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingState() {
		return billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public String getBillingZip() {
		return billingZip;
	}

	public void setBillingZip(String billingZip) {
		this.billingZip = billingZip;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

}
