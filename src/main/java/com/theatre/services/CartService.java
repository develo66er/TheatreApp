package com.theatre.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theatre.dao.CartDAO;
import com.theatre.dao.SeatsDAO;
import com.theatre.dao.TicketsDAO;
import com.theatre.models.CartDTO;
import com.theatre.models.TicketsDTO;

@Service
public class CartService {
	@Autowired
	CartDAO cartDAO;
	@Autowired
	TicketsDAO ticketsDAO;

	@Transactional
	public void addToCart(String userName,TicketsDTO ticket) {
		boolean res = cartDAO.addToCart(userName,ticket);
		if(res) {
			ticketsDAO.deleteTicket(ticket.getId());
		}
	}
	public CartDTO getCartForUser(String userName) {
		return cartDAO.getCartForUser(userName);
	}
	public TicketsDTO getTicketFromCart(long ticketId) {
		return cartDAO.getTicketFromCart(ticketId);
	}
	public boolean removeTicketFromCart(long ticketId) {
		return cartDAO.removeTicketFromCart(ticketId);
	}
}
