package com.indus.training.domain;

import java.io.Serializable;

public class Person implements Serializable {

	private String name;
	private String address;
	private double phoneNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + "]";
	}

}
