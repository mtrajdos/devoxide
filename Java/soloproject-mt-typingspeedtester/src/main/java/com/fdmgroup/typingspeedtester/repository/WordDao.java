package com.fdmgroup.typingspeedtester.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.typingspeedtester.model.Word;

public interface WordDao extends JpaRepository<Word, Long> {

	List<Word> findAll();

	Word findByWord(@Param("word") String word);
	
	Word findById(@Param("wordId") long wordId);
	
	@Query(value="SELECT w.word FROM words w WHERE w.ROWNUM <= :numberOfWords ORDER BY w.word",nativeQuery=true)
	ArrayList<String> findSpecificNumberOfWords(@Param("numberOfWords")int numberOfWords);
	
}
