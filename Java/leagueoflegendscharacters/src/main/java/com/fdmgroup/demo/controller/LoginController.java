package com.fdmgroup.demo.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.demo.model.User;
import com.fdmgroup.demo.service.UserService;

@Controller
public class LoginController {

	public final static String SESSION_ATTRIBUTE_USER = "USER";
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@GetMapping("")
	public String home() {
		return "home.jsp";
	}

	@GetMapping("Login")
	public String login() {
		return "login.jsp";
	}

	@PostMapping("LoginSubmit")
	public ModelAndView loginSubmit(@ModelAttribute("user") User user, ModelMap model, HttpSession session) {

		Optional<User> userFromDatabase = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (userFromDatabase.isEmpty()) {
			model.addAttribute("errorMessage", "Incorrect username or password");
			return new ModelAndView("login.jsp");
		}

		LOGGER.info("User {} logged in at {}", user.getUsername(), LocalDateTime.now()); /*** Add entry to logger when an user has logged into the application. ***/
		session.setAttribute(SESSION_ATTRIBUTE_USER, userFromDatabase);
		return new ModelAndView("/WEB-INF/main.jsp");
	}

	@GetMapping("Logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "home.jsp";
	}
}
