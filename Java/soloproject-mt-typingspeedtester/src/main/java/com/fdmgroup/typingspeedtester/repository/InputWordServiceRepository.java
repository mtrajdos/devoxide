package com.fdmgroup.typingspeedtester.repository;

import java.util.List;

public interface InputWordServiceRepository<InputWord> {
	
	List<InputWord> findAll();
	
	String findByiwordId(long iwordId);

	InputWord save(InputWord inputWord);

}
