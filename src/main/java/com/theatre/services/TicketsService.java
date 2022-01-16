package com.theatre.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theatre.dao.SeatsDAO;
import com.theatre.dao.SessionDAO;
import com.theatre.dao.TicketsDAO;
import com.theatre.exceptions.SeatsEnoughException;
import com.theatre.models.SessionDTO;
import com.theatre.models.TicketsDTO;

@Service
public class TicketsService {
	@Autowired
	private TicketsDAO ticketsDAO;
	@Autowired
	private SeatsDAO seatsDAO;
	@Transactional
	public List<TicketsDTO> getAllTickets() {
		return ticketsDAO.getAllTickets();
	}
	@Transactional
	public TicketsDTO getTicketById(long id) {
		return ticketsDAO.getTicketById(id);
	}
	@Transactional
	public List<TicketsDTO> getTicketsForPerformance(long performanceId) {
		return ticketsDAO.getAllTicketsForPerformance(performanceId);
	}
	@Transactional
	public List<TicketsDTO> getTicketsForSession(long sessionId) {
		return ticketsDAO.getAllTicketsForSession(sessionId);
	}
	@Transactional
	public List<TicketsDTO> getTicketsForHall(long hallId) {
		return ticketsDAO.getAllTicketsForHall(hallId);
	}
	@Transactional
	public boolean addTickets(TicketsDTO ticketsDTO) throws SeatsEnoughException {
		long seatId = seatsDAO.doesSeatExists(ticketsDTO.getHallId(), ticketsDTO.getRowNumber(), ticketsDTO.getSeatNumber());
		if(seatId<0) {
			seatId = seatsDAO.insertSeat(ticketsDTO.getHallId(),  ticketsDTO.getRowNumber(), ticketsDTO.getSeatNumber());
		}
		if(ticketsDAO.doesTicketExistsForSeat(seatId,ticketsDTO.getSessionId())) throw new SeatsEnoughException();
		return ticketsDAO.addTicket(seatId, ticketsDTO);
	}
	@Transactional
	public boolean deleteAllSessionTickets(long sessionId) {
		return ticketsDAO.deleteAllSessionTickets(sessionId);
	}
	@Transactional
	public boolean deleteAllPerformanceTickets(long performanceId) {
		return ticketsDAO.deleteAllPerformanceTickets(performanceId);
	}
	@Transactional
	public boolean deleteAllHallTickets(long hallId) {
		return ticketsDAO.deleteAllHallTickets(hallId);
	}
	@Transactional
	public boolean editTickets(TicketsDTO ticketsDTO) {
		ticketsDTO.setSeatId(ticketsDAO.getSeatId(ticketsDTO.getId()));
		ticketsDAO.updateTicket(ticketsDTO);
		return seatsDAO.updateSeat(ticketsDTO);
	}
	public void deleteTicket(long ticketId) {
		ticketsDAO.deleteTicket(ticketId);
		
	}
}
