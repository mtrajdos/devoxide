package com.fdmgroup.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface TeamServiceRepository<Team> {

	Optional<Team> findById(Long id);

	Optional<Team> findByName(String name);

	List<Team> findAll();

	Team save(Team team);

}
