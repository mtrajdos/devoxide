package com.fdmgroup.festivalBookingSystem.controller;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

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
import com.fdmgroup.festivalBookingSystem.model.Festival;
import com.fdmgroup.festivalBookingSystem.model.Genre;
import com.fdmgroup.festivalBookingSystem.model.Stage;
import com.fdmgroup.festivalBookingSystem.service.BandService;
import com.fdmgroup.festivalBookingSystem.service.FestivalService;
import com.fdmgroup.festivalBookingSystem.service.StageService;

@Controller
public class FestivalController {

	private final static Logger LOGGER = LoggerFactory.getLogger(FestivalController.class);

	@Autowired
	private FestivalService festivalService;
	
	@Autowired
	private BandService bandService;

	@Autowired
	private StageService stageService;
	
	@RequestMapping("AllFestivals")
	public ModelAndView allFestivals() {
		List<Festival> collectedFestivals = festivalService.findAll();
		
		for (Festival festival : collectedFestivals) {
			festivalService.updateTicketPrice(festival);
			festivalService.save(festival);
		}
		
		collectedFestivals = festivalService.findAll();
		
		return new ModelAndView("WEB-INF/allFestivals.jsp", "allFestivals", collectedFestivals);
	}
	
	@RequestMapping("AllFestivalsAttendee")
	public ModelAndView allFestivalsAttendee() {
		List<Festival> collectedFestivals = festivalService.findAll();
		
		for (Festival festival : collectedFestivals) {
			festivalService.updateTicketPrice(festival);
			festivalService.save(festival);
		}
		
		collectedFestivals = festivalService.findAll();
		
		return new ModelAndView("WEB-INF/allFestivalsAttendee.jsp", "allFestivals", collectedFestivals);
	}
	
	

	@RequestMapping("AddFestival")
	public ModelAndView addFestival() {
		ModelAndView modelAndView = new ModelAndView("WEB-INF/addFestival.jsp");
		modelAndView.addObject("festival", new Festival());
		modelAndView.addObject("genres", EnumSet.allOf(Genre.class));
		return modelAndView;
	}

	@PostMapping("AddFestivalSubmit")
	public ModelAndView addFestivalSubmit(@ModelAttribute("festival") Festival festival, ModelMap model) {
		festival.setCurrentTickets(festival.getBaseTotalTickets());
		festivalService.updateTicketPrice(festival);
		LOGGER.info("Festival {} was added at {}", festival.getFestivalName(), LocalDateTime.now());
		return new ModelAndView("WEB-INF/allFestivals.jsp", "allFestivals", festivalService.findAll());
	}

	@RequestMapping("EditFestival")
	public ModelAndView editFestival(@RequestParam("id") Long festival_id) {
		Festival festival = festivalService.findById(festival_id).get();
		ModelAndView modelAndView = new ModelAndView("WEB-INF/editFestival.jsp");
		modelAndView.addObject("festival", festival);
		modelAndView.addObject("genres", EnumSet.allOf(Genre.class));
		modelAndView.addObject("bands", bandService.findAll());
		return modelAndView;
	} 
	
	@PostMapping("EditFestivalSubmit")
	public ModelAndView editFestivalSubmit(@ModelAttribute("festival") Festival festival,@ModelAttribute ("band") Band band, ModelMap model) {
		Optional<Band> bandFromDatabase = bandService.findByBandName(band.getBandName());
		if(bandFromDatabase.isPresent()) {
			model.addAttribute("errorMessage","Band already playing");
			return new ModelAndView("WEB-INF/editFestival.jsp","festival",festival);
		}
		Festival festivalfromDatabase = festivalService.findById(festival.getFestival_id()).get();
		festival.setFestivalStages(festivalfromDatabase.getFestivalStages());
		festivalService.updateTicketPrice(festival);
		LOGGER.info("Festival {} details were edited at {}", festival.getFestivalName(), LocalDateTime.now());
		return new ModelAndView("WEB-INF/allFestivals.jsp","allFestivals",festivalService.findAll());
	}
	
	@RequestMapping("ViewAllStages")
	public ModelAndView viewAllStages(@ModelAttribute("id") Long festival_id) {
		Festival festival = festivalService.findById(festival_id).get();
		List<Stage> allStages = festival.getFestivalStages();
		return new ModelAndView("WEB-INF/allStages.jsp", "allStages", allStages);
	}
	
	@RequestMapping("AllStagesAdmin")
	public ModelAndView allStagesAdmin(@ModelAttribute("id") Long festival_id) {
		Festival festival = festivalService.findById(festival_id).get();
		List<Stage> allStages = festival.getFestivalStages();
		ModelAndView model = new ModelAndView("WEB-INF/allStagesAdmin.jsp", "allStages", allStages);
		model.addObject("festival_id", festival_id);
		return model;
	}
	
	@RequestMapping("EditStage")
	public ModelAndView editStage(@ModelAttribute("id")Long stage_id) {
		Festival festival = festivalService.findById(stageService.findFestival_Id(stage_id)).get();
		List<Band> allBands = festival.getFestivalBands();
		Stage stage = stageService.findById(stage_id).get();
		ModelAndView model = new ModelAndView("WEB-INF/editStage.jsp", "stage", stage);
		model.addObject("bands", allBands);
		return model;
	}
	
	@PostMapping("EditStageSubmit")
	public ModelAndView editStageSubmit(@ModelAttribute("stage")Stage stage, ModelMap model) {
		stageService.save(stage);
		LOGGER.info("Stage {} details edited at {}", stage.getStageName(), LocalDateTime.now());
		return new ModelAndView("WEB-INF/allFestivals.jsp", "allFestivals", festivalService.findAll());
	}
	
	@RequestMapping("CreateStage")
	public ModelAndView createStage(@ModelAttribute("id") Long festival_id) {
		festivalService.createStageAddToFestival(festival_id);
		Festival festival = festivalService.findById(festival_id).get();
		List<Stage> allStages = festival.getFestivalStages();
		ModelAndView model = new ModelAndView("WEB-INF/allStagesAdmin.jsp", "allStages", allStages);
		model.addObject("festival_id", festival_id);
		return model;
	}

}
