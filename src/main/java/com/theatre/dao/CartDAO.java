package com.theatre.dao;

import com.theatre.models.CartDTO;
import com.theatre.models.TicketsDTO;

public interface CartDAO {
		
	public boolean addToCart(String userName,TicketsDTO ticket);

	public CartDTO getCartForUser(String userName);

	public boolean removeTicketFromCart(long ticketId);

	public TicketsDTO getTicketFromCart(long ticketId);
	public boolean doesTicketExistsInCart(long ticketId);
	
}
