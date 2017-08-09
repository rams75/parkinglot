package com.sai.myparking.model;

import java.time.LocalDateTime;

public class Ticket {
	
	// Mandatory parameters
	Integer slotNumber;
	String carNumber;
	
	// Optional parameters
	LocalDateTime time;
	Double price;
	
	public Integer getSlotNumber() {
		return slotNumber;
	}
	public String getCarNumber() {
		return carNumber;
	}
	
	
	public LocalDateTime getTime() {
		return time;
	}
	@Override
	public String toString() {
		return "Ticket [slotNumber=" + slotNumber + ", carNumber=" + carNumber
				+ ", time=" + time + ", price=" + price + "]";
	}
	public Double getPrice() {
		return price;
	}


	public static class TicketBuilder {
		
		// let say all are optional: not required to use builder
		private Integer slotNumber;
		private String carNumber;
		
		// Optional parameters
		LocalDateTime time;
		Double price;
		
		
		public TicketBuilder(Integer slotNumber, String carNumber) {
			this.slotNumber = slotNumber;
			this.carNumber = carNumber;
		}
		
		public TicketBuilder setTime(LocalDateTime time) {
			this.time = time;
			return this;
		}
		
		public TicketBuilder setPrice(Double price) {
			this.price = price;
			return this;
		}
		
		public Ticket build() {
			return new Ticket(this);
		}
		
		
		
	}
	
	Ticket(TicketBuilder builder) {
		this.carNumber = builder.carNumber;
		this.price = builder.price;
		this.slotNumber = builder.slotNumber;
		this.time = builder.time;
	}
	
}
