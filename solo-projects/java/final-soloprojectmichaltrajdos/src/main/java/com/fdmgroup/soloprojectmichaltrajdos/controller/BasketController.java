package com.fdmgroup.soloprojectmichaltrajdos.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.soloprojectmichaltrajdos.model.User;
import com.fdmgroup.soloprojectmichaltrajdos.service.ItemService;
import com.fdmgroup.soloprojectmichaltrajdos.service.UserService;

@Controller
public class BasketController {

	public final static String SESSION_ATTRIBUTE_USER = "USER";
	private final static Logger LOGGER = LoggerFactory.getLogger(BasketController.class);

	@Autowired
	UserService userService;

	@Autowired
	ItemService itemService;

	@GetMapping("")
	public String home() {
		return "home.jsp";
	}

	@GetMapping("GetBasketSearch")
	public String getBasketSearch() {
		return "basketSearch.jsp";
	}

	@PostMapping("BasketCheckout")
	public ModelAndView basketCheckout(@ModelAttribute("user") User user, ModelMap model) {

		Optional<User> userFromDatabase = userService.findById(user.getUser_id());
		if (userFromDatabase.isEmpty()) {
			model.addAttribute("errorMessage", "Error: user id not found");
			return new ModelAndView("basketSearch.jsp");
		}

		LOGGER.info("User {} viewed basket at {}", user.getFullname(), LocalDateTime.now());

		ModelAndView modelAndView = new ModelAndView("/WEB-INF/basketCheckout.jsp");
		User userCheckingBasket = userFromDatabase.get();
		userService.updateTotalBasketPrice(userCheckingBasket);
		modelAndView.addObject(userCheckingBasket);
		return modelAndView;
	}

	@PostMapping("BasketCheckoutSubmit")
	public ModelAndView basketCheckoutSubmit(@ModelAttribute("user") User user) {
		
		Optional<User> userFromDatabase1 = userService.findById(user.getUser_id());
		User userFromDatabase = userFromDatabase1.get();
		
		if (itemService.checkStock(user) == false) {
		itemService.buyBasket(userFromDatabase);
		LOGGER.info("User {} purchased items from basket at {}", user.getFullname(), LocalDateTime.now());
		ModelAndView modelAndView = new ModelAndView("WEB-INF/basketCheckoutFinal.jsp");
		modelAndView.addObject("user", userFromDatabase);
		return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView("WEB-INF/basketCheckout.jsp");
			LOGGER.info("User {} attemted to buy basked at {}, at least one item not in stock", user.getFullname(), LocalDateTime.now());
			modelAndView.addObject("errorMessage", "Error with checkout: at least one item in the basket is no longer in stock");
			modelAndView.addObject("user", userFromDatabase);
			return modelAndView;
		}

	}

}
