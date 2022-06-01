package com.fdmgroup.typingspeedtester.repository;

import java.util.List;

import com.fdmgroup.typingspeedtester.model.Word;

public interface WordServiceRepository<Word> {
	
	List<Word> findAll();

	String findByWord(String chosenWord);
	
	String findById(long wordId);
	
	List<String> findSpecificNumberOfWords(int numberOfWords);

}
