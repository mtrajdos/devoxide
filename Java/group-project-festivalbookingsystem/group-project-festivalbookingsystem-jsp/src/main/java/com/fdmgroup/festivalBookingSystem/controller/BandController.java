package com.fdmgroup.festivalBookingSystem.controller;

import java.time.LocalDateTime;
import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.festivalBookingSystem.model.Band;
import com.fdmgroup.festivalBookingSystem.model.Genre;
import com.fdmgroup.festivalBookingSystem.service.BandService;

@Controller
public class BandController {

	private final static Logger LOGGER = LoggerFactory.getLogger(BandController.class);
	
	@Autowired
	BandService bandService;
	
	@RequestMapping("AllBands")
	public ModelAndView allBands() {
		return new ModelAndView("WEB-INF/allBands.jsp", "allBands", bandService.findAll());
	}

	@RequestMapping("EditBand")
	public ModelAndView editBand(@RequestParam("id") Long band_id) {
		Band band = bandService.findByBand_id(band_id).get();
		ModelAndView modelAndView = new ModelAndView("WEB-INF/editBand.jsp");
		modelAndView.addObject("genres", EnumSet.allOf(Genre.class));
		modelAndView.addObject("band", band);
		return modelAndView;
	}
	@PostMapping("EditBandSubmit")
	public ModelAndView editBandSubmit(@ModelAttribute("band") Band band, ModelMap model) {
		bandService.save(band);
		LOGGER.info("Band {} details were updated at {}", band.getBandName(), LocalDateTime.now());
		return new ModelAndView("WEB-INF/allBands.jsp","allBands",bandService.findAll());
	}
}
