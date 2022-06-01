package com.fdmgroup.demo.controller;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.demo.model.User;
import com.fdmgroup.demo.model.UserType;
import com.fdmgroup.demo.service.UserService;

@Controller
public class UserController {

	
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	/***
	 * allUsers - List all characters from USERS table using findAll method from Spring Boot
	 * addUser or editUser - Add new or edit existing user based on username and password passed in
	 * addUserSubmit or editUserSubmit - Submit entries into the database for new or edited user.
	 ***/

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
		LOGGER.info("New user {} added at {}", user.getUsername(), LocalDateTime.now()); /*** Add entry to logger upon new user creation.  ***/
		return new ModelAndView("forward:/AllUsers");
	}

	@RequestMapping("EditUser")
	public ModelAndView editUser(@RequestParam("id") Long id) {
		Optional<User> user = userService.findById(id);
		ModelAndView modelAndView = new ModelAndView("WEB-INF/editUser.jsp", "user", user.get());
		modelAndView.addObject("userTypes", EnumSet.allOf(UserType.class));
		return modelAndView;
	}

	@PostMapping("EditUserSubmit")
	public ModelAndView editUserSubmit(@ModelAttribute("user") User user) {

		userService.save(user);
		LOGGER.info("User {} edited at {}", user.getUsername(), LocalDateTime.now());  /*** Add entry to logger when a user has been edited.  ***/
		return new ModelAndView("forward:/AllUsers");
	}
	
}
