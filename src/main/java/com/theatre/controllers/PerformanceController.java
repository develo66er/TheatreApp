package com.theatre.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.theatre.models.PerformanceDTO;
import com.theatre.services.PerformanceService;
import com.theatre.valiadtors.PerformanceValidator;

@Controller
public class PerformanceController {
	@Autowired 
	UserDetailsService userDetailsService;
	@Autowired
	PerformanceService performanceService;
	@Autowired
	@Qualifier("performanceValidator")
	PerformanceValidator performanceValidator;
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(performanceValidator);
    }
	@GetMapping("/")
	public String getPerformanceList(Model model) {
		List<PerformanceDTO> performances = performanceService.getPerformanceLIst();
		model.addAttribute("performances", performances);
		model.addAttribute("performance", new PerformanceDTO());
		return "performances";
	}

	@PostMapping("/add-performance")
	public String addPerformance(@ModelAttribute @Valid PerformanceDTO performance,BindingResult bindingResult,  Model model) {
		if(bindingResult.hasErrors()) {
			List<PerformanceDTO> performances = performanceService.getPerformanceLIst();
			model.addAttribute("performances", performances);
			model.addAttribute("performance", new PerformanceDTO());
			return "performances";
		}
		performanceService.addPerformance(performance);

		return "redirect:/";
	}
	@PostMapping("/edit-performance")
	public String editPerformance(@ModelAttribute PerformanceDTO performance, Model model) {

		performanceService.editPerformance(performance);

		return "redirect:/";
	}
	@GetMapping("/delete-performance")
	public String deletePerformance(@RequestParam String id) {
		if (id != null)
			performanceService.deletePerformance(Long.parseLong(id));

		return "redirect:/";
	}
}
