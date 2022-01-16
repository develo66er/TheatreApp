package com.theatre.models;

public class TicketsDTO {
	private long id;
	private long sessionId;
	private long seatId;
	private long hallId;
	private double price;
	private int rowNumber;
	private int seatNumber;
	private PerformanceDTO performance;
	private SessionDTO session;
	private SeatsDTO seats;
	private HallDTO hall;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public PerformanceDTO getPerformance() {
		return performance;
	}
	public void setPerformance(PerformanceDTO performance) {
		this.performance = performance;
	}
	public SessionDTO getSession() {
		return session;
	}
	public void setSession(SessionDTO session) {
		this.session = session;
	}
	public SeatsDTO getSeats() {
		return seats;
	}
	public void setSeats(SeatsDTO seats) {
		this.seats = seats;
	}
	public HallDTO getHall() {
		return hall;
	}
	public void setHall(HallDTO hall) {
		this.hall = hall;
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public long getSeatId() {
		return seatId;
	}
	public void setSeatId(long seatId) {
		this.seatId = seatId;
	}
	public long getHallId() {
		return hallId;
	}
	public void setHallId(long hallId) {
		this.hallId = hallId;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	
}
