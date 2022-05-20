package com.fdmgroup.festivalBookingSystem.controller;

import java.time.LocalDateTime;
import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.festivalBookingSystem.model.User;
import com.fdmgroup.festivalBookingSystem.model.UserType;
import com.fdmgroup.festivalBookingSystem.service.UserService;

@Controller
public class UserController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("AllUsers")
	public ModelAndView allUsers() {
		return new ModelAndView("WEB-INF/allUsers.jsp", "allUsers", userService.findAll());
	}

	@RequestMapping("AddUser")
	public ModelAndView addUser() {
		ModelAndView modelAndView = new ModelAndView("WEB-INF/addUser.jsp", "user", new User());
		modelAndView.addObject("userTypes", EnumSet.allOf(UserType.class));
		return modelAndView;
	}

	@PostMapping("AddUserSubmit")
	public ModelAndView addUserSubmit(@ModelAttribute("user") User user) {
		userService.save(user);
		LOGGER.info("New user {} added at {}", user.getFirstname(), LocalDateTime.now());
		return new ModelAndView("forward:/AllUsers");
	}
}
