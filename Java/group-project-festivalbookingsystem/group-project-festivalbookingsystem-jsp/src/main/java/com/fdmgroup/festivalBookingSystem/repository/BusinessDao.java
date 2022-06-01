package com.fdmgroup.festivalBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.festivalBookingSystem.model.Business;

public interface BusinessDao extends JpaRepository<Business, Long>{

	List<Business> findByLocationIgnoreCase (@Param("location") String location);	

}