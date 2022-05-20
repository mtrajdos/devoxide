package com.fdmgroup.festivalBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.festivalBookingSystem.model.Band;
import com.fdmgroup.festivalBookingSystem.repository.BandDao;
import com.fdmgroup.festivalBookingSystem.repository.BandServiceRepository;


@Service
public class BandService implements BandServiceRepository<Band> {

	@Autowired
	private BandDao bandDao;

	@Override
	public void save(Band band) {
		bandDao.save(band);
	} 
	
	public List<Band> findAll() {
		return bandDao.findAll();
	}

	@Override
	public Optional<Band> findByBandName(String bandName) {
		return bandDao.findByBandName(bandName);
	}

	@Override
	public Optional<Band> findByBand_id(Long band_id) {
		return bandDao.findById(band_id);
	}
}

