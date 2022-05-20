package com.fdmgroup.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@SuppressWarnings("hiding")
@Service
public interface CharacterServiceRepository<Character> {

	Optional<Character> findById(Long id);
	List<Character> findAll();
	Character save(Character character);
}
