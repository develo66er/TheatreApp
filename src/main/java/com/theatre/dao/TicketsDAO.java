package com.theatre.dao;

import java.util.List;

import com.theatre.exceptions.SeatsEnoughException;
import com.theatre.models.TicketsDTO;

public interface TicketsDAO {
		
	public List<TicketsDTO> getAllTickets();
	public List<TicketsDTO> getAllTicketsForPerformance(long performanceId);
	public List<TicketsDTO> getAllTicketsForSession(long sessionId);
	public List<TicketsDTO> getAllTicketsForHall(long sessionId);
	public boolean addTicket(long seatId, TicketsDTO ticketsDTO);
	public boolean deleteTicket( long id);
	public boolean deleteAllPerformanceTickets(long performanceId);
	public boolean deleteAllSessionTickets(long performanceId);
	public boolean deleteAllHallTickets(long performanceId);

	public boolean editTickets(long oldHallId, TicketsDTO ticket);
	public long getSeatId(long id);
	public boolean updateTicket(TicketsDTO ticketsDTO);
	public TicketsDTO getTicketById(long id);
	public boolean doesTicketExistsForSeat(long seatId,long sessionId);
}
