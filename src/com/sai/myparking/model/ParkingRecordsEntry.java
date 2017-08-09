package com.sai.myparking.model;

import java.time.LocalDateTime;

public class ParkingRecordsEntry {
	
	// Below two are mandatory parameters
	private Car car;	
	private LocalDateTime enteredAt;
	
	// Below two are optional parameters: these are present only for the cars which got pa
	private Ticket ticket;
	private LocalDateTime leftAt;
	private Slot slot;
	
	public ParkingRecordsEntry(ParkingEntryBuilder builder) {
		this.car = builder.car;
		this.enteredAt = builder.enteredAt;
		this.ticket = builder.ticket;
		this.slot = builder.slot;
		this.leftAt = builder.leftAt;
	}
	
	
	@Override
	public String toString() {
		return "ParkingRecordsEntry [car=" + car + ", enteredAt=" + enteredAt
				+ ", ticket=" + ticket + ", leftAt=" + leftAt + ", slot="
				+ slot + "]";
	}


	public static class ParkingEntryBuilder {
		
		// Below two are mandatory parameters
		private Car car;	
		private LocalDateTime enteredAt;
		
		// Below two are optional parameters: these are present only for the cars which got pa
		private Ticket ticket;
		private LocalDateTime leftAt;
		private Slot slot;
		
		public ParkingEntryBuilder(Car car, LocalDateTime enterdAt) {
			this.car = car;
			this.enteredAt = enterdAt;
		}
		
		public ParkingEntryBuilder setSlot(Slot slot) {
			this.slot = slot;
			return this;
		}
		
		public ParkingEntryBuilder setLeftAt(LocalDateTime leftAt) {
			this.leftAt = leftAt;
			return this;
		}
		
		public ParkingEntryBuilder setTicket(Ticket ticket) {
			this.ticket = ticket;
			return this;
		}
		
		public ParkingRecordsEntry build() {
			return new ParkingRecordsEntry(this);
		}
		
		
	}
	
}
