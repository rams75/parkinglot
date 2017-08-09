package com.sai.myparking.model;

public class Slot {
	
	private Integer number;
	private boolean isOccupied;
	private Car car;
	private Double price;
	
	
	
	public Slot(Integer number, boolean isOccupied, Car car, Double price) {
		super();
		this.number = number;
		this.isOccupied = isOccupied;
		this.car = car;
		this.price = price;
	}
	public Slot(Integer number) {
		super();
		this.number = number;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public boolean getIsOccupied() {
		return isOccupied;
	}
	public void setIsOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@Override
	public String toString() {
		return "Slot [number=" + number + ", isOccupied=" + isOccupied
				+ ", car=" + car + ", price=" + price + "]";
	}
	
	
	
}
