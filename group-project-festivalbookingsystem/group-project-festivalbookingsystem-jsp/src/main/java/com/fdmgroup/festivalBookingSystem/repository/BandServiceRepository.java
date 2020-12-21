package com.fdmgroup.festivalBookingSystem.repository;

import java.util.Optional;

import com.fdmgroup.festivalBookingSystem.model.Band;

public interface BandServiceRepository<T> {
	
	Optional<Band> findByBandName(String bandName);

	void save(T t);

	Optional<Band> findByBand_id(Long band_id);
}
