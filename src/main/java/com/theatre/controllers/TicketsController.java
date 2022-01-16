package com.theatre.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.theatre.exceptions.CartIsEmptyException;
import com.theatre.exceptions.NoTicketsAvailableException;
import com.theatre.exceptions.SeatsEnoughException;
import com.theatre.models.HallDTO;
import com.theatre.models.SessionDTO;
import com.theatre.models.TicketsDTO;
import com.theatre.services.HallService;
import com.theatre.services.PerformanceService;
import com.theatre.services.SeatsService;
import com.theatre.services.SessionService;
import com.theatre.services.TicketsService;

@Controller
public class TicketsController {
	@Autowired
	SessionService sessionService;
	@Autowired
	PerformanceService performanceService;
	@Autowired
	TicketsService ticketsService;
	@Autowired
	HallService hallService;
	@Autowired
	SeatsService seatsService;

	@GetMapping("/tickets")
	public String getPerformanceList(Model model) throws NoTicketsAvailableException {
		List<TicketsDTO> tickets = ticketsService.getAllTickets();
		if(tickets == null || tickets.isEmpty())throw new NoTicketsAvailableException();
		List<SessionDTO> sessions = sessionService.getSessionsList();
		sessions.stream().forEach(s -> s.setPerformance(performanceService.getPerformanceById(s.getPerformanceId())));
		List<HallDTO> halls = hallService.getAllHalls();
		model.addAttribute("sessions", sessions);
		model.addAttribute("tickets", tickets);
		model.addAttribute("halls", halls);

		model.addAttribute("ticket", new TicketsDTO());
		return "tickets";
	}

	@PostMapping("/add-tickets")
	public String addTicket(@ModelAttribute TicketsDTO ticket) throws SeatsEnoughException {

		ticketsService.addTickets(ticket);

		return "redirect:/tickets";
	}
	@PostMapping("/edit-ticket")
	public String addSession(@ModelAttribute TicketsDTO ticket) {

		ticketsService.editTickets(ticket);

		return "redirect:/tickets";
	}
	@GetMapping("/delete-ticket")
	public String deleteTicket(@RequestParam String id) throws SeatsEnoughException {
		if(id!=null && !id.isEmpty())
		ticketsService.deleteTicket(Long.parseLong(id));
		return "redirect:/tickets";
	}
	@ExceptionHandler(SeatsEnoughException.class)
	public String handleCustomException(SeatsEnoughException ex, Model model) {

		model.addAttribute("errMsg", ex.getMessage());
		List<TicketsDTO> tickets = ticketsService.getAllTickets();
		List<SessionDTO> sessions = sessionService.getSessionsList();
		sessions.stream().forEach(s -> s.setPerformance(performanceService.getPerformanceById(s.getPerformanceId())));
		List<HallDTO> halls = hallService.getAllHalls();
		model.addAttribute("sessions", sessions);
		model.addAttribute("tickets", tickets);
		model.addAttribute("halls", halls);

		model.addAttribute("ticket", new TicketsDTO());
		return "tickets";

	}
	@ExceptionHandler(NoTicketsAvailableException.class)
	public String handleCustomException(NoTicketsAvailableException ex, Model model) {
		model.addAttribute("ticketsErrMsg", ex.getMessage());
		List<SessionDTO> sessions = sessionService.getSessionsList();
		sessions.stream().forEach(s -> s.setPerformance(performanceService.getPerformanceById(s.getPerformanceId())));
		List<HallDTO> halls = hallService.getAllHalls();
		model.addAttribute("sessions", sessions);
		model.addAttribute("tickets", new ArrayList<TicketsDTO>());
		model.addAttribute("halls", halls);

		model.addAttribute("ticket", new TicketsDTO());
		return "tickets";

	}
}
