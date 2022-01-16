package com.theatre.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.theatre.exceptions.CartIsEmptyException;
import com.theatre.exceptions.SeatsEnoughException;
import com.theatre.models.CartDTO;
import com.theatre.models.HallDTO;
import com.theatre.models.SessionDTO;
import com.theatre.models.TicketsDTO;
import com.theatre.services.CartService;
import com.theatre.services.TicketsService;

@Controller
public class CartController {
	@Autowired
	CartService cartService;
	@Autowired
	TicketsService ticketsService;

	@GetMapping("/cart")
	public String getCartItems( Model model, Principal principal) throws CartIsEmptyException{
		CartDTO carts = cartService.getCartForUser(principal.getName());
		if(carts.getCartItems()==null || carts.getCartItems().isEmpty())throw new CartIsEmptyException();
		model.addAttribute("tickets", carts.getCartItems());
		return "cart";
	}
	@GetMapping("/add-to-cart")
	public String addToCart(@RequestParam String ticket_id, Model model, Principal principal) {
		long ticketId = -1;
		if (ticket_id != null && !ticket_id.isEmpty()) {
			ticketId = Long.parseLong(ticket_id);
			TicketsDTO ticket = ticketsService.getTicketById(ticketId);
			if (ticket != null) {
				cartService.addToCart(principal.getName(), ticket);
			}
		}
		return "redirect:/tickets";
	}
	@GetMapping("/remove-cart-item")
	public String removeFromCart(@RequestParam String ticket_id, Model model, Principal principal) throws SeatsEnoughException {
		long ticketId = -1;
		if (ticket_id != null && !ticket_id.isEmpty()) {
			ticketId = Long.parseLong(ticket_id);
			TicketsDTO ticket = cartService.getTicketFromCart(ticketId);
			if (ticket != null) {
				ticketsService.addTickets(ticket);
				cartService.removeTicketFromCart(ticketId);
				
			}
		}
		return "redirect:/cart";
	}
	@ExceptionHandler(CartIsEmptyException.class)
	public String handleCustomException(CartIsEmptyException ex, Model model) {
		model.addAttribute("errMsg", ex.getMessage());
		return "cart";

	}
}
