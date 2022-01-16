package com.theatre.dao;

import java.util.List;

import com.theatre.models.SeatsDTO;
import com.theatre.models.TicketsDTO;

public interface SeatsDAO {
		
	public List<SeatsDTO> getSeatsForHall(long hallId);
	public boolean updateSeat(TicketsDTO ticket);
	public long doesSeatExists(long hallId, int rowNumber, int seatNumber);
	public long insertSeat(long hallId, int rowNumber, int seatNumber);
}
