package com.fdmgroup.typingspeedtester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.typingspeedtester.model.RandomWordList;

public interface RandomWordListDao extends JpaRepository<RandomWordList, Long>{

	RandomWordList findByRandomWordListId(@Param("randomWordListId") long randomWordListId);
	
}
