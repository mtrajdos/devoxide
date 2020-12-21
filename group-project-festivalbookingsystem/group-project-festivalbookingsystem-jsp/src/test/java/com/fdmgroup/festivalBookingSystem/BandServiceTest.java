package com.fdmgroup.festivalBookingSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.festivalBookingSystem.model.Band;
import com.fdmgroup.festivalBookingSystem.model.Genre;
import com.fdmgroup.festivalBookingSystem.service.BandService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BandServiceTest {

	@Autowired
	private BandService bandService;

	private Band band;

	@BeforeEach
	void setUp() throws Exception {
		band = new Band("Band 1", Genre.METAL);
	}

	@Test
	void test_CanBeAddedToDataBase() {
		int numberOfBandsBeforeSave = bandService.findAll().size();
		bandService.save(band);
		int numberOfBandsAfterSave = bandService.findAll().size();
		assertNotEquals(numberOfBandsBeforeSave, numberOfBandsAfterSave);
	}

	@Test
	void testThatBandCanBeFoundByName() {
		bandService.save(band);
		Optional<Band> foundBand = bandService.findByBandName("Band 1");
		assertFalse(foundBand.isEmpty());
	}

	@Test
	void testThatBandCanBeFoundByBandId() {
		Optional<Band> foundBand = bandService.findByBand_id(1L);
		assertFalse(foundBand.isEmpty());
	}

}
