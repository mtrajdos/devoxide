package com.fdmgroup.typingspeedtester.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.typingspeedtester.model.InputWordList;

public interface InputWordListDao extends JpaRepository<InputWordList, Long>{
	
	List<InputWordList> findAll();
	
	InputWordList findByInputWordListId(@Param("inputWordListId") long inputWordListId);
	
	InputWordList save(InputWordList inputWordList);

}
