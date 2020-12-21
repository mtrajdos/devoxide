package com.fdmgroup.festivalBookingSystem.repository;

import java.util.List;

public interface BusinessServiceRepository<Business> {
	
	void save(Business business);
	
	List<Business> findByLocation(String location);
	
	List<Business> findAll();

}
