package com.fdmgroup.festivalBookingSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.festivalBookingSystem.model.Band;

public interface BandDao extends JpaRepository<Band, Long>{

	Optional<Band> findByBandName(@Param("name") String name);
	
}
