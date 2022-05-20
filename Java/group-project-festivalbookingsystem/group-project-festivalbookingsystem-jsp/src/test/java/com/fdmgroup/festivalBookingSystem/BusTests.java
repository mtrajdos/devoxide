package com.fdmgroup.festivalBookingSystem;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.festivalBookingSystem.model.Bus;
import com.fdmgroup.festivalBookingSystem.model.Festival;
import com.fdmgroup.festivalBookingSystem.service.BusService;
import com.fdmgroup.festivalBookingSystem.service.FestivalService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BusTests {

	@Autowired
	private BusService busService;
	
	@Autowired
	private FestivalService festivalService;

	@Test
	void test_ThatABusCanBeCreated() {
		Bus bus = new Bus(50, "Buchanan Street Bus Station", "T In The Park", LocalDateTime.parse("2020-07-30T09:30:00"), 70);
		busService.save(bus);
		assertNotNull(bus);
		
	}
	
	@Test
	void test_thatABusCanBeAddedToAFestival() {
		Bus bus = busService.findById(1L).get();
		Festival festival = festivalService.findById(1L).get();
		festivalService.save(festival);
		busService.save(bus);
		festival.getFestivalBuses().add(bus);
		festivalService.save(festival);
		assertNotNull(festival.getFestivalBuses());
	}
	
	

}
