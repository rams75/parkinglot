package com.sai.myparking.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.sai.myparking.model.Car;
import com.sai.myparking.model.ParkingBook;
import com.sai.myparking.model.ParkingRecordsEntry;
import com.sai.myparking.model.Slot;
import com.sai.myparking.model.Ticket;

public class ParkingSystem {

	
	
	public static void main(String[] args) {
		
		List<Slot> slots = Arrays.asList(new Slot(1), new Slot(2)); //new Slot(3), new Slot(4), new Slot(5), new Slot(6));
		
		ParkingController controller = new ParkingController(); // TODO: should we keep all methods static inside this class !!
		
		ParkingBook book = new ParkingBook();
		
		while(true) {
			
			try {
			
			// TODO: can add show total parking lot that we can internally represent using 2D array
				System.out.println("1. Park Car\n2. Registration Numbers of All Parked Cars\n3. Avaliable Free Slots\n4. Registration Numbers of Particular Color"
						+ "\n5. Slot Number in which a Car with a given Registration number is Parked"
			 			+ "\n6. Slot Numbers of All Slots where a Car of a particular Color is parked."
			 			+ "\n7. Leave Car"
			 			+ "\n8. Cars Rejected due to no slot"
			 			+ "\n9. Show Book"
			 			+ "\n10. Parking Slots Status"
			 			+ "\n0.Exit");
				
				// for now use switch, later if required replace with Command pattern
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				int choice = Integer.parseInt(br.readLine());
				
				switch(choice) {
				
					case 1:
						
						System.out.print("Enter registration number:");
						String carNumber = br.readLine();
						System.out.print("Enter color of car: ");
						String color = br.readLine();
						
						Slot slot = controller.getFreeSlot(slots);
						if(slot == null) {
							displayStatus("All slots are full");
							Car car = new Car(carNumber, color, slot); //TODO: try if we can create static factory for this later
							LocalDateTime enteredAt = LocalDateTime.now();
							ParkingRecordsEntry entry = 
									new ParkingRecordsEntry.ParkingEntryBuilder(car, enteredAt).build();
							book.addEntryToBook(entry);
										
						} else {
							Car car = new Car(carNumber, color, slot); //TODO: try if we can create static factory for this later
							LocalDateTime enteredAt = LocalDateTime.now();
							Ticket ticket = controller.allotCar(car, slot, enteredAt);
							ParkingRecordsEntry entry = 
									new ParkingRecordsEntry.ParkingEntryBuilder(car, enteredAt)
															.setTicket(ticket)
															.setSlot(slot)
															.build();
							book.addEntryToBook(entry);
							displayStatus("Car parked successfully");
						}
						
						break;
						
					case 7:
						System.out.println("Enter slot number");
						int slotNumber = Integer.parseInt(br.readLine());
						controller.leaveCar(slotNumber, slots);
						break;
					case 0:
						displayStatus("See you soon !!");
						System.exit(0);
						
					default: 
						displayStatus("See you soon !!");
						System.exit(0);
				
				}
				
			} catch(IOException ie) {
				System.out.println(ie);
			}
			
		}
		
	}

	private static void displayStatus(String message) {
		System.out.println("****************" + message + "****************");
	}
	
}
