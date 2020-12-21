package com.fdmgroup.festivalBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fdmgroup.festivalBookingSystem.model.Stage;

public interface StageDao extends JpaRepository<Stage, Long>  {
	
	@Query(value = "SELECT stage.festival_id  FROM STAGE where stage.stage_id = ?", nativeQuery = true)
	long findFestivalId(long stage_id);
}