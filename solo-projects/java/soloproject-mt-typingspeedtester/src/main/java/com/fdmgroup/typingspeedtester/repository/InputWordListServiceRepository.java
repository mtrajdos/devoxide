package com.fdmgroup.typingspeedtester.repository;

import java.util.List;

import com.fdmgroup.typingspeedtester.model.InputWordList;

public interface InputWordListServiceRepository<InputWord> {
	
	List<InputWordList> findAll();
	
	InputWordList findByInputWordListId(long inputWordListId);

	InputWordList save(InputWordList inputWordList);

}
