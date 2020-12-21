package com.fdmgroup.festivalBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.festivalBookingSystem.model.Festival;

public interface FestivalDao extends JpaRepository<Festival, Long> {
	
	}