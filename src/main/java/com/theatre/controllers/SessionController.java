package com.theatre.controllers;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.theatre.models.PerformanceDTO;
import com.theatre.models.SessionDTO;
import com.theatre.services.PerformanceService;
import com.theatre.services.SessionService;

@Controller
public class SessionController {
	@Autowired
	SessionService sessionService;
	@Autowired
	PerformanceService performanceService;

	@GetMapping("/sessions")
	public String getPerformanceList(Model model) {
		List<SessionDTO> sessions = sessionService.getSessionsList();
		List<PerformanceDTO> performances = performanceService.getPerformanceLIst();
		//fill performances in sessions
		sessions.stream().forEach(s->s.setPerformance(performanceService.getPerformanceById(s.getPerformanceId())));
		model.addAttribute("sessions", sessions);
		model.addAttribute("performances", performances);
		model.addAttribute("session", new SessionDTO());
		return "sessions";
	}
	@RequestMapping(value="/filter-session",params={"date-filter"})
	public String filterSessionsByDate(@RequestParam String filterDate,Model model) {
		List<SessionDTO> sessions = sessionService.getSessionsList();
		sessions = sessions.stream().filter(s->{
			 Timestamp ts = s.getStartDateTime();
			 LocalDateTime ldt = ts.toLocalDateTime();
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 String sessionDate = ldt.format(dtf);
			 System.out.println("sessionDate ------------"+ sessionDate);
			 System.out.println("Date ------------"+ filterDate);
			
			 
			 return sessionDate.equals(filterDate);
		 }).collect(Collectors.toList());
		List<PerformanceDTO> performances = performanceService.getPerformanceLIst();
		//fill performances in sessions
		sessions.stream().forEach(s->s.setPerformance(performanceService.getPerformanceById(s.getPerformanceId())));
		model.addAttribute("sessions", sessions);
		model.addAttribute("performances", performances);
		model.addAttribute("session", new SessionDTO());
		return "sessions";
	}
	@RequestMapping(value="/filter-session",params={"performance-filter"})
	public String filterSessionsByPerformance(@RequestParam String performanceId,Model model) {
		List<SessionDTO> sessions = sessionService.getSessionsList();
		sessions = sessions.stream().filter(s->s.getPerformanceId()==Long.parseLong(performanceId)).collect(Collectors.toList());
		List<PerformanceDTO> performances = performanceService.getPerformanceLIst();
		//fill performances in sessions
		sessions.stream().forEach(s->s.setPerformance(performanceService.getPerformanceById(s.getPerformanceId())));
		model.addAttribute("sessions", sessions);
		model.addAttribute("performances", performances);
		model.addAttribute("session", new SessionDTO());
		return "sessions";
	}
	@RequestMapping(value="/filter-session",params={"undo-filter"})
	public String undoFilterSessions(@RequestParam String filterDate,Model model) {
		
		return "redirect:/sessions";
	}
	@PostMapping("/add-session")
	public String addSession(@ModelAttribute SessionDTO session,final BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("error","wrong datetime format");
			return "redirect:/sessions";
		}
		
		sessionService.addSession(session);

		return "redirect:/sessions";
	}
	@PostMapping("/edit-session")
	public String editSession(@ModelAttribute SessionDTO session, Model model) {
		String oldPerformanceId = session.getOldPerformanceId();
		if(oldPerformanceId!=null && !oldPerformanceId.isEmpty()) {
		sessionService.editSession(Long.parseLong(oldPerformanceId), session);
		}

		return "redirect:/sessions";
	}
	@GetMapping("/delete-session")
	public String deletePerformance(@RequestParam String id) {
		if (id != null&& !id.isEmpty()) {
			sessionService.deleteSession(Long.parseLong(id));
		}
		return "redirect:/sessions";
	}
}
