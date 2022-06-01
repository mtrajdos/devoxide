package com.fdmgroup.demo.controller;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.demo.model.Character;
import com.fdmgroup.demo.model.DamageSource;
import com.fdmgroup.demo.model.Position;
import com.fdmgroup.demo.service.CharacterService;

@Controller
public class CharacterController {
	
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CharacterController.class);

	@Autowired
	private CharacterService characterService;
	
	/***
	 * allCharacters - List all characters from CHARACTER table using findAll method from Spring Boot
	 * addCharacter or editCharacter - Add new or edit existing character based on required fields put in (name, position, damage source, titles and descriptions for abilities)
	 * addCharacterSubmit or editCharacterSubmit - Submit entries into the database for new or edited character. Extra check in addCharacterSubmit using the name to prevent duplicate characters.
	 ***/
	
	@GetMapping("AllCharacters")
	public ModelAndView allCharacters() {
		return new ModelAndView("WEB-INF/allCharacters.jsp", "characters", characterService.findAll());
	}

	@GetMapping("AddCharacter")
	public ModelAndView addCharacter() {
		ModelAndView modelAndView = new ModelAndView("WEB-INF/addCharacter.jsp");
		modelAndView.addObject("character", new Character());
		modelAndView.addObject("positions", EnumSet.allOf(Position.class));
		modelAndView.addObject("damagesources", EnumSet.allOf(DamageSource.class));
		return modelAndView;
	}

	@PostMapping("AddCharacterSubmit")
	public ModelAndView addCharacterSubmit(@ModelAttribute("character") Character character, ModelMap model) {
		Optional<Character> characterFromDatabase = characterService.findByName(character.getName());

		if (characterFromDatabase.isPresent()) {
			model.addAttribute("errorMessage", "Character already exists");
			return new ModelAndView("WEB-INF/allCharacters.jsp", "characters", characterService.findAll());
		} else {
			characterService.save(character);
			LOGGER.info("New character with name {} added at {}", character.getName(), LocalDateTime.now()); 	/*** Add entry to logger upon new character creation.  ***/
			return new ModelAndView("WEB-INF/allCharacters.jsp", "characters", characterService.findAll());
		}

	}

	@GetMapping("EditCharacter")
	public ModelAndView editCharacter(@RequestParam("id") Long id) {
		Optional<Character> character = characterService.findById(id);
		ModelAndView modelAndView = new ModelAndView("WEB-INF/editCharacter.jsp");
		modelAndView.addObject("character", character.get());
		modelAndView.addObject("allCharacters", characterService.findAll());
		modelAndView.addObject("positions", EnumSet.allOf(Position.class));
		modelAndView.addObject("damagesources", EnumSet.allOf(DamageSource.class));
		return modelAndView;
	}

	@PostMapping("EditCharacterSubmit")
	public ModelAndView editBookSubmit(@ModelAttribute("character") Character character) {
		characterService.save(character);
		LOGGER.info("Character {} edited at {}", character.getName(), LocalDateTime.now()); 					/*** Add entry to logger when a character has been edited. ***/
		return new ModelAndView("WEB-INF/allCharacters.jsp", "characters", characterService.findAll());
	}
	
	@PostMapping("EditCharacterSubmitDelete")
	public ModelAndView editCharacterSubmitDelete(@ModelAttribute("character") @RequestParam("id") Long id) {
		Optional<Character> characterForDeletion = characterService.findById(id);
		characterService.delete(id);
		LOGGER.info("Character {} removed at {}", characterForDeletion.get().getName(), LocalDateTime.now()); 	/*** Add entry to logger when a character has been removed. ***/
		return new ModelAndView("WEB-INF/allCharacters.jsp", "characters", characterService.findAll());
	}
}
