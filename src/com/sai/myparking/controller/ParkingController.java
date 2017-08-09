package com.sai.myparking.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.sai.myparking.model.Car;
import com.sai.myparking.model.Slot;
import com.sai.myparking.model.Ticket;

public class ParkingController {
	
	/*
	 * TODO:
	 * 	- Once all the methods are completed try segregating based on single responsibility
	 */
	
	
	public Ticket allotCar(Car car, Slot slot, LocalDateTime time) {
		
		slot.setIsOccupied(true);
		slot.setCar(car);
		
		return new Ticket.TicketBuilder(slot.getNumber(), car.getRegistrationNumber())
						 .setPrice(slot.getPrice())
						 .setTime(time)
						 .build();
		
	}
	
	
	public Slot getFreeSlot(List<Slot> slots) {
		for(Slot slot: slots)
			if(!slot.getIsOccupied())
				return slot;
		return null;
	}
	
	
	public long countNumberOfFreeSlots(List<Slot> slots) {
		return slots.stream()
			.filter(slot -> !slot.getIsOccupied())
			.count();
	}
	
	public long countNumberOfOccupiedSlots(List<Slot> slots) {
		return slots.stream()
			.filter(slot -> slot.getIsOccupied())
			.count();
	}
	
	
	public String getColorOfCarParkedAtSlot(Integer slotNumber, List<Slot> slots) {
		return slots.stream()
			.filter(slot -> slot.getIsOccupied())
			.filter(slot -> slot.getNumber().equals(slotNumber))
			.map(slot -> slot.getCar().getColor())
			.findFirst()
			.get();
	}
	
	
	public List<String> getCarNumbersByColor(String color, List<Slot> slots) {
		
		return slots.stream()
			.filter(slot -> slot.getIsOccupied())
			.filter(slot -> slot.getCar().getColor().equals(color))
			.map(slot -> slot.getCar().getRegistrationNumber())
			.collect(Collectors.toList());
		
	}
	
	public String getRegistrationNumberOfCarParkedAtSlot(Integer slotNumber, List<Slot> slots) {
		return slots.stream()
			.filter(slot -> slot.getIsOccupied())
			.filter(slot -> slot.getNumber().equals(slotNumber))
			.map(slot -> slot.getCar().getRegistrationNumber())
			.findFirst()
			.get();
	}


	public void leaveCar(int slotNumber, List<Slot> slots) {
		
		for(Slot slot: slots) {
			if(slot.getNumber().equals(slotNumber)) {
				slot.setIsOccupied(false);
				slot.setCar(null);
				break;
			}

		}		
	} 
	
	
	
}
