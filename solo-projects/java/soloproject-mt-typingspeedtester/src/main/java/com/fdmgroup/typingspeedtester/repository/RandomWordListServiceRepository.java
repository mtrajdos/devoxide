package com.fdmgroup.typingspeedtester.repository;

import org.springframework.data.repository.query.Param;

public interface RandomWordListServiceRepository<RandomWordList> {
	
	RandomWordList findByRandomWordListId(@Param("randomWordListId") long randomWordListId);

	RandomWordList save(RandomWordList randomWordList);

}
