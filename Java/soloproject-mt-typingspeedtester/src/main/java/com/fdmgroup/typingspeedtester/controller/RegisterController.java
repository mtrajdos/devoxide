package com.fdmgroup.typingspeedtester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.typingspeedtester.model.User;
import com.fdmgroup.typingspeedtester.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("Register")
	public String register() {
		return "register.jsp";
	}
	
	@PostMapping("RegisterSubmit")
	public String registerSubmit(@ModelAttribute("user") User user, ModelMap model) {
		
		User userFromDatabase = userService.findByUsername(user.getUsername());
		if (userFromDatabase != null) {
			model.addAttribute("errorMessage", "User already exists");
			return "register.jsp";
		}
		userService.save(user);
		return "login.jsp";
	}

}
