package com.sai.myparking.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingBook {
	
	private List<ParkingRecordsEntry> entries;

	public ParkingBook() {
		
	}
	
	public ParkingBook(List<ParkingRecordsEntry> entry) {
		super();
		entries = entry;
	}

	public List<ParkingRecordsEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<ParkingRecordsEntry> entries) {
		this.entries = entries;
	}
	
	public List<ParkingRecordsEntry> addEntryToBook(ParkingRecordsEntry entry) {
		if(entries == null)
			entries = new ArrayList<>();
		entries.add(entry);
		return entries;
	}

	@Override
	public String toString() {
		return "ParkingBook [entry=" + entries + "]";
	}
	
	
	
}
