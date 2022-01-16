package com.theatre.models;

public class HallDTO {
	private long id;
	private String name;
	private int rowsNumber;
	private int seatsNumber;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRowsNumber() {
		return rowsNumber;
	}
	public void setRowsNumber(int rowsNumber) {
		this.rowsNumber = rowsNumber;
	}
	public int getSeatsNumber() {
		return seatsNumber;
	}
	public void setSeatsNumber(int seatsNumber) {  
		this.seatsNumber = seatsNumber;
	}
	
}
