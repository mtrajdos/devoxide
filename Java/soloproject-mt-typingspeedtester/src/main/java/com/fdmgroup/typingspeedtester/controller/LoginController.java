package com.fdmgroup.typingspeedtester.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.typingspeedtester.model.User;
import com.fdmgroup.typingspeedtester.service.UserService;

@Controller
public class LoginController {
	
	public final static String SESSION_ATTRIBUTE_USER = "USER";
	
	@Autowired
	UserService userService;
	
	// For empty default url
	@GetMapping("")
	public String home() {
		return "home.jsp";
	}
	
	@GetMapping("/Login")
	public String login() {
		return "login.jsp";
	}

	@PostMapping("/LoginSubmit")
	public ModelAndView loginSubmit(@ModelAttribute("user") User user, ModelMap model, HttpSession session) {
		
		User userFromDatabase = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (userFromDatabase == null) {
			model.addAttribute("errorMessage", "Incorrect username or password");
			return new ModelAndView("login.jsp");
		}
		session.setAttribute(SESSION_ATTRIBUTE_USER, userFromDatabase);
		return new ModelAndView("/WEB-INF/main.jsp");
	}
	
	@GetMapping("Logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "home.jsp";
	}
	
}
