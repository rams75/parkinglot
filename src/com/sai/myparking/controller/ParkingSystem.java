package com.sai.myparking.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.sai.myparking.model.Car;
import com.sai.myparking.model.ParkingBook;
import com.sai.myparking.model.ParkingRecordsEntry;
import com.sai.myparking.model.Slot;
import com.sai.myparking.model.Ticket;

public class ParkingSystem {

	public static void main(String[] args) {

		List<Slot> slots = Arrays.asList(new Slot(1), new Slot(2), new Slot(3),
				new Slot(4));// new Slot(5), new Slot(6));

		ParkingController controller = new ParkingController(); // TODO: should
																// we keep all
																// methods
																// static inside
																// this class !!

		ParkingBook book = new ParkingBook();

		try {

			FileReader fr = new FileReader(
					new File(
							"/Users/RAMS/RAMS SSD/myworkspace/parkinglot/src/com/sai/myparking/controller/commands.txt"));

			BufferedReader br = new BufferedReader(fr);

			while (true) {

				// TODO: can add show total parking lot that we can internally
				// represent using 2D array
				System.out.println("1. Park Car" + "\n2. Leave Car"
						+ "\n3. Show Parking Slots" + "\n4. Show Book"
						+ "\n0.Exit");

				// for now use switch, later if required replace with Command
				// pattern

				int choice = Integer.parseInt(br.readLine());
				System.out.println(choice);
				switch (choice) {

				case 1:

					System.out.print("Enter registration number:");
					String carNumber = br.readLine();
					System.out.println(carNumber);
					System.out.print("Enter color of car: ");
					String color = br.readLine();
					System.out.println(color);

					Slot slot = controller.getFreeSlot(slots);
					if (slot == null) {
						displayStatus("All slots are full");
						Car car = new Car(carNumber, color, slot); // TODO: try
																	// if we can
																	// create
																	// static
																	// factory
																	// for this
																	// later
						LocalDateTime enteredAt = LocalDateTime.now();
						ParkingRecordsEntry entry = new ParkingRecordsEntry.ParkingEntryBuilder(
								car, enteredAt).build();
						book.addEntryToBook(entry);

					} else {
						Car car = new Car(carNumber, color, slot); // TODO: try
																	// if we can
																	// create
																	// static
																	// factory
																	// for this
																	// later
						LocalDateTime enteredAt = LocalDateTime.now();
						Ticket ticket = controller.allotCar(car, slot,
								enteredAt);
						ParkingRecordsEntry entry = new ParkingRecordsEntry.ParkingEntryBuilder(
								car, enteredAt).setTicket(ticket).setSlot(slot)
								.build();
						book.addEntryToBook(entry);
						displayStatus("Car parked successfully");
					}

					break;

				case 2:
					System.out.println("Enter slot number");
					int slotNumber = Integer.parseInt(br.readLine());
					System.out.println(slotNumber);
					boolean isCarLeft = controller.leaveCar(slotNumber, slots);
					if (isCarLeft) {
						displayStatus("Car Exit from Parking");

						// TODO: divide book to quickly retrieved current parked
						// entries only
						Optional<ParkingRecordsEntry> freedEntry = book
								.getEntries()
								.stream()
								.filter(entry -> entry.getTicket() != null
										&& entry.getLeftAt() != null)
								.filter(entry -> entry.getSlot().getNumber()
										.equals(slotNumber)).findFirst();

						if (freedEntry.isPresent())
							freedEntry.get().setLeftAt(LocalDateTime.now());

					} else
						displayStatus("No Car Parked at Slot: " + slotNumber);
					break;

				case 3:
					controller.showParkingArea(slots);
					break;

				case 4:
					book.getEntries()
							.stream()
							.forEach(
									entry -> System.out.println("Car "
											+ entry.getCar()
													.getRegistrationNumber()
											+ " Parked at Slot "
											+ entry.getSlot().getNumber()
											+ " entered at: "
											+ entry.getEnteredAt()
											+ " exit at: " + entry.getLeftAt()
											+ " ticket: " + entry.getTicket()));
					break;

				case 0:
					displayStatus("See you soon !!");
					br.close();
					System.exit(0);
					break;
				default:
					displayStatus("See you soon !!");
					br.close();
					System.exit(0);
					break;
				}
			}
			
		} catch (IOException ie) {
			System.out.println(ie);
		} 

		return;

	}

	private static void displayStatus(String message) {
		System.out.println("****************" + message + "****************");
	}

}
