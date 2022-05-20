package com.fdmgroup.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.fdmgroup.demo.model.Character;
import com.fdmgroup.demo.service.CharacterService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class CharacterServiceTests {	
	
	@Autowired
	CharacterService characterService;
	
	@Test
	public void findAllCharactersAndConfirmTheyHaveBeenRetrieved() {
		List<Character> allCharacters = characterService.findAll();
		assertNotNull(allCharacters);
	}
	
	@Test
	public void findCharacterByNameAndCheckThatItExists() {
		String name = "Zoe";
		Optional<Character> character = characterService.findByName(name);
		assertFalse(character.isEmpty());
	}
	
	@Test
	public void findCharacterByIdAndCheckThatItExists() {
		Optional<Character> character = characterService.findById(1L);
		assertFalse(character.isEmpty());
	}
	
	@Test
	public void deleteCharacterByIdAndCheckThatIsGone() {
		characterService.delete(3L);
		Optional<Character> character = characterService.findById(3L);
		assertTrue(character.isEmpty());
	}
	
	@Test
	public void saveCharacterAndConfirmItExists() {
		List<Character> allCharacters = characterService.findAll();
		Character characterForSaving = allCharacters.get(0);
		Character savedCharacter = characterService.save(characterForSaving);
		assertNotNull(savedCharacter);
	}
	
}
