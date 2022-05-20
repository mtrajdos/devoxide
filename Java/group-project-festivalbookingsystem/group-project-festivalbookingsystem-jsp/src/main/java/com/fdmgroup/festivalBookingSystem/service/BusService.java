package com.fdmgroup.festivalBookingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.festivalBookingSystem.model.Bus;
import com.fdmgroup.festivalBookingSystem.repository.BusDao;

@Service
public class BusService {

	@Autowired
	BusDao busDao;
	
	public void save(Bus bus) {
		busDao.save(bus);
	}

	public Optional<Bus> findById(long bus_id) {
		return busDao.findById(bus_id);
	}

}
