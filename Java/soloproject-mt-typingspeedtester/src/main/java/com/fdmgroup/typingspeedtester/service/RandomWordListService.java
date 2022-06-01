package com.fdmgroup.typingspeedtester.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.typingspeedtester.model.RandomWordList;
import com.fdmgroup.typingspeedtester.repository.RandomWordListDao;
import com.fdmgroup.typingspeedtester.repository.RandomWordListServiceRepository;

@Service
public class RandomWordListService implements RandomWordListServiceRepository<RandomWordList> {
	
	@Autowired
	private RandomWordListDao randomWordListDao;

	@Override
	public RandomWordList findByRandomWordListId(long randomWordListId) {
		return randomWordListDao.findByRandomWordListId(randomWordListId);
	}

	@Override
	public RandomWordList save(RandomWordList randomWordList) {
		return randomWordListDao.save(randomWordList);
	}

}
