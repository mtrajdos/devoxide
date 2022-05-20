package com.fdmgroup.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.demo.service.TeamService;

@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;
	
	/*** List all the teams currently existing in the database ***/
	
	@GetMapping("AllTeams")
	public ModelAndView allTeams() {
		return new ModelAndView("WEB-INF/allTeams.jsp", "teams", teamService.findAll());
	}
	
}
