package com.fdmgroup.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.demo.model.Character;
import com.fdmgroup.demo.repository.CharacterDao;

@Service
public class CharacterService implements CharacterServiceRepository<Character> {

	@Autowired
	private CharacterDao characterDao;

	@Override
	public List<Character> findAll() {
		return characterDao.findAll();
	}

	@Override
	public Character save(Character character) {
		return characterDao.save(character);
	}

	public void delete(Long id) {
		characterDao.deleteById(id);
	}

	public Optional<Character> findByName(String name) {
		return characterDao.findByNameContainingIgnoreCase(name);
	}

	@Override
	public Optional<Character> findById(Long id) {
		return characterDao.findById(id);
	}

}
