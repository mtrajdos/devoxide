package com.fdmgroup.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.fdmgroup.demo.model.Team;
import com.fdmgroup.demo.service.TeamService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TeamServiceTests {

	@Autowired
	TeamService teamService;
	
	@Test
	public void testThatAllTeamsCanBeRetrieved() {
		List<Team> allTeams = teamService.findAll();
		assertNotNull(allTeams);
	}
	
	@Test
	public void testThatATeamCanBeFoundByName() {
		Optional<Team> foundTeam = teamService.findByName("FirstTeam");
		assertFalse(foundTeam.isEmpty());
	}
	
	@Test
	public void testThatATeamCanBeFoundById() {
		Optional<Team> foundTeam = teamService.findById(1L);
		assertFalse(foundTeam.isEmpty());
	}
	
	@Test
	public void saveTeamAndConfirmItExists() {
		List<Team> allTeams = teamService.findAll();
		Team teamForSaving = allTeams.get(0);
		Team savedTeam = teamService.save(teamForSaving);
		assertNotNull(savedTeam);
	}
	
}
