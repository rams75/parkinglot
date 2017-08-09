package com.sai.myparking.model;

public class Car {
	
	private String registrationNumber;
	private String color;
	private Slot allottedSlot;
	
	
	
	public Car(String registrationNumber, String color, Slot allottedSlot) {
		super();
		this.registrationNumber = registrationNumber;
		this.color = color;
		this.allottedSlot = allottedSlot;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Slot getAllottedSlot() {
		return allottedSlot;
	}
	public void setAllottedSlot(Slot allottedSlot) {
		this.allottedSlot = allottedSlot;
	}
	
	@Override
	public String toString() {
		return "Car [registrationNumber=" + registrationNumber + ", color="
				+ color + ", allottedSlot=" + allottedSlot + "]";
	}
	
	
}
