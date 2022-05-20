package com.fdmgroup.festivalBookingSystem.repository;

import java.util.Optional;

import com.fdmgroup.festivalBookingSystem.model.Festival;
import com.fdmgroup.festivalBookingSystem.model.User;

public interface FestivalServiceRepository<T> {

	void save(Festival festival);
	
	void buyTicket(Festival festival, Optional<User> user);
	
}