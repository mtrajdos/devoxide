package com.fdmgroup.festivalBookingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.festivalBookingSystem.model.Business;
import com.fdmgroup.festivalBookingSystem.service.BusinessService;

@Controller
public class BusinessController {

	@Autowired 
	BusinessService businessService;
	
	@RequestMapping("AllBusinessAdmin")
	public ModelAndView allBusinessAdmin() {
		return new ModelAndView("WEB-INF/allBusinessAdmin.jsp", "allBusinesses", businessService.findAll());
	}
	
	@RequestMapping("DisplayBusiness")
	public ModelAndView allBusinessAdmin(@RequestParam("id")Long businessId) {
		return new ModelAndView("WEB-INF/displayBusiness.jsp", "business", businessService.findById(businessId));
	}
	
	@PostMapping("SearchByLocation")
	public ModelAndView searchByLocation(@ModelAttribute("business") Business business, ModelMap model) {
		List<Business> businessList = businessService.findByLocation(business.getLocation());
		if (businessList.isEmpty()) {
			return new ModelAndView("WEB-INF/allBusinessAdmin.jsp", "allBusinesses", businessService.findAll());
			
		}
		return new ModelAndView("WEB-INF/allBusinessAdmin.jsp", "allBusinesses", businessList);
	}
}
