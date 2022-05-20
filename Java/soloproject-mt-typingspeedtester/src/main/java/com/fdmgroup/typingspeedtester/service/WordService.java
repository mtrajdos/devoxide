package com.fdmgroup.typingspeedtester.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.typingspeedtester.model.Word;
import com.fdmgroup.typingspeedtester.repository.WordDao;
import com.fdmgroup.typingspeedtester.repository.WordServiceRepository;

@Service
public class WordService implements WordServiceRepository<Word> {
	
	@Autowired
	private WordDao wordDao;

	public List<Word> findAll() {
		return wordDao.findAll();
	}

	@Override
	public String findByWord(String chosenWord) {
		return wordDao.findByWord(chosenWord).getWord();
	}

	@Override
	public String findById(long wordId) {
		return wordDao.findById(wordId).getWord();
	}
	
	@Override
	public ArrayList<String> findSpecificNumberOfWords(int numberOfWords) {
		
		ArrayList<String> listOfRandomWords = new ArrayList<String>();
		int i=0;
		
		while (i<numberOfWords) {

			long raffledWordId = ThreadLocalRandom.current().nextLong(1, 1000);
			String raffledWord = wordDao.findById(raffledWordId).getWord();
			listOfRandomWords.add(raffledWord);
			i++;
		}

		return listOfRandomWords;
		
	}

	

}
