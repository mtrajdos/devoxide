package com.fdmgroup.typingspeedtester.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.typingspeedtester.model.InputWordList;
import com.fdmgroup.typingspeedtester.repository.InputWordListDao;
import com.fdmgroup.typingspeedtester.repository.InputWordListServiceRepository;

@Service
public class InputWordListService implements InputWordListServiceRepository<InputWordList> {

	@Autowired
	private InputWordListDao inputWordListDao;

	public List<InputWordList> findAll() {
		return inputWordListDao.findAll();
	}

	@Override
	public InputWordList findByInputWordListId(long inputWordListId) {
		return inputWordListDao.findByInputWordListId(inputWordListId);
	}

	@Override
	public InputWordList save(InputWordList inputWordList) {
		return inputWordListDao.save(inputWordList);
	}
	
}
