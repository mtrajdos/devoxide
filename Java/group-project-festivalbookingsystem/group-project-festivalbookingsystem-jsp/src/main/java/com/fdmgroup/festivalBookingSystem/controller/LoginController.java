package com.fdmgroup.festivalBookingSystem.controller;

import java.time.LocalDateTime;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.festivalBookingSystem.model.Festival;
import com.fdmgroup.festivalBookingSystem.model.User;
import com.fdmgroup.festivalBookingSystem.model.UserType;
import com.fdmgroup.festivalBookingSystem.service.FestivalService;
import com.fdmgroup.festivalBookingSystem.service.UserService;

@Controller
public class LoginController {

	public final static String SESSION_ATTRIBUTE_USER = "USER";
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@Autowired
	FestivalService festivalService;

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

		LOGGER.info("User {} logged in at {}", user.getUsername(), LocalDateTime.now());
		session.setAttribute(SESSION_ATTRIBUTE_USER, userFromDatabase);
		if (userFromDatabase.get().getUserType().equals(UserType.ATTENDEE)) {
			List<Festival> earlyBirdList = festivalService.findAll();
			earlyBirdList.removeIf(festival -> !festival.isEarlyBird());
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/attendeeMain.jsp");
			modelAndView.addObject("earlyBirdList", earlyBirdList);
			User userLoggedIn = userService.findByUsername(user.getUsername()).get();
			modelAndView.addObject(userLoggedIn);
			return modelAndView;
			
		} else {
			ModelAndView modelAndView = new ModelAndView("/WEB-INF/adminMain.jsp");
			User userLoggedIn = userService.findByUsername(user.getUsername()).get();
			modelAndView.addObject(userLoggedIn);
			return modelAndView;
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping("Main")
	public ModelAndView Main(@ModelAttribute("user") Optional<User> user, HttpSession session) {
		user = (Optional<User>) session.getAttribute(SESSION_ATTRIBUTE_USER);
		User userDetails = user.get();
		if (user.get().getUserType().equals(UserType.ATTENDEE)) {
			List<Festival> earlyBirdList = festivalService.findAll();
			earlyBirdList.removeIf(festival -> !festival.isEarlyBird());
			ModelAndView modelAndView = new ModelAndView("WEB-INF/attendeeMain.jsp");
			modelAndView.addObject("earlyBirdList", earlyBirdList);
			modelAndView.addObject("user", userDetails);
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("WEB-INF/adminMain.jsp");
			modelAndView.addObject("user", userDetails);
			return modelAndView;
		}
		
	}

	@GetMapping("Logout")
	public String logout(HttpSession session) {
		@SuppressWarnings("unchecked")
		Optional<User> user = (Optional<User>) session.getAttribute(SESSION_ATTRIBUTE_USER);
		LOGGER.warn("User {} logged out at {}", user.get().getUsername(), LocalDateTime.now());
		session.invalidate();
		return "home.jsp";
	}
	
	@RequestMapping("BuyTicket")
	public ModelAndView buyTicket(@RequestParam("id") Long festival_id, HttpSession session)
			 {
		@SuppressWarnings("unchecked")
		Optional<User> userBuyingTickets = (Optional<User>) session.getAttribute(SESSION_ATTRIBUTE_USER);
		Festival festival = festivalService.findById(festival_id).get();
		festivalService.buyTicket(festival, userBuyingTickets);
		LOGGER.info("User {} bought tickets for festival {} at {}", userBuyingTickets.get().getUsername(), festival.getFestivalName(), LocalDateTime.now());
		return new ModelAndView("WEB-INF/allTickets.jsp", "user", userBuyingTickets.get());
	}
}
