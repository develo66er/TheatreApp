package com.theatre.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login")
	public String homePage(Model model) {
 		return "login";
 	}  
		
}