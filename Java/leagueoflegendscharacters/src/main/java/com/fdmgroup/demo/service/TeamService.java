package com.fdmgroup.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.demo.model.Team;
import com.fdmgroup.demo.repository.TeamDao;

@Service
public class TeamService implements TeamServiceRepository<Team> {

	@Autowired
	private TeamDao teamDao;

	@Override
	public Optional<Team> findByName(String name) {
		return teamDao.findByNameContainingIgnoreCase(name);
	}

	@Override
	public Optional<Team> findById(Long id) {
		return teamDao.findById(id);
	}

	@Override
	public List<Team> findAll() {
		return teamDao.findAll();
	}

	@Override
	public Team save(Team team) {
		return teamDao.save(team);
	}

}
