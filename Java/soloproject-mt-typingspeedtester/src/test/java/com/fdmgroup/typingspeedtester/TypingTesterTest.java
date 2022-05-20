package com.fdmgroup.typingspeedtester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.typingspeedtester.model.User;
//import com.fdmgroup.typingspeedtester.model.InputWord;
import com.fdmgroup.typingspeedtester.model.Word;
import com.fdmgroup.typingspeedtester.service.UserService;
//import com.fdmgroup.typingspeedtester.service.InputWordService;
import com.fdmgroup.typingspeedtester.service.WordService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TypingTesterTest {
	
	@Autowired
	WordService wordService;
	
	@Autowired
	UserService userService;
	
	@Test
	public void test_ThatAListOFWordsCanBeReturned() {		
		List<Word> returnedListOfWords = wordService.findAll();
		System.out.println(returnedListOfWords);
		assertNotNull(returnedListOfWords);
	}
	
	@Test
	public void test_ThatASpecificWordCanBeReturned() {
		String chosenWord = "admit";
		String fetchedWord = wordService.findByWord(chosenWord);
		assertTrue(fetchedWord.equals(chosenWord));
	}
	
	@Test
	public void test_ThatASpecificNumberOfRandomWordsCanBeReturnedForTheUserWithID1() {
		int numberOfWords = 20;
		List<String> chosenWordList = wordService.findSpecificNumberOfWords(numberOfWords);
		int actualSizeOfCalledList = chosenWordList.size();
		assertEquals(20, actualSizeOfCalledList);
	}
	
	@Test
	public void test_ThatBuiltListsAreRandom() {
		int numberOfWords = 20;
		ArrayList<String> firstWordList = wordService.findSpecificNumberOfWords(numberOfWords);
		ArrayList<String> secondWordList = wordService.findSpecificNumberOfWords(numberOfWords);
		assertNotEquals(firstWordList, secondWordList);
	}
	
}
