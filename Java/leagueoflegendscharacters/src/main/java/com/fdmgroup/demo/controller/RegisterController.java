package com.fdmgroup.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.demo.model.User;
import com.fdmgroup.demo.service.UserService;

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

		Optional<User> userFromDatabase = userService.findByUsername(user.getUsername());
		if (userFromDatabase.isPresent()) {
			model.addAttribute("errorMessage", "User already exists");
			return "register.jsp";
		}
		userService.save(user);
		return "login.jsp";
	}

}
