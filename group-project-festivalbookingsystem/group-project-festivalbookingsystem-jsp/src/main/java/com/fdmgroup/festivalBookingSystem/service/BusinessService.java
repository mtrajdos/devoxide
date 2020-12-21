package com.fdmgroup.festivalBookingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.festivalBookingSystem.model.Business;
import com.fdmgroup.festivalBookingSystem.repository.BusinessDao;
import com.fdmgroup.festivalBookingSystem.repository.BusinessServiceRepository;

@Service
public class BusinessService implements BusinessServiceRepository<Business> {

	@Autowired
	private BusinessDao businessDao;
	
	@Override
	public void save(Business business) {
		businessDao.save(business);
	}

	@Override
	public List<Business> findByLocation(String location) {
		return businessDao.findByLocationIgnoreCase(location);
	}

	@Override
	public List<Business> findAll() {
		return businessDao.findAll();
	}
	
	public Business findById(Long businessId) {
		return businessDao.findById(businessId).get();
	}

	
}
