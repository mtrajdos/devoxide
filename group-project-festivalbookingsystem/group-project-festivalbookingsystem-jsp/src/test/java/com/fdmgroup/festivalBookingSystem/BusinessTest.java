package com.fdmgroup.festivalBookingSystem;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.festivalBookingSystem.model.Business;
import com.fdmgroup.festivalBookingSystem.model.BusinessType;
import com.fdmgroup.festivalBookingSystem.service.BusinessService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BusinessTest {

	@Autowired
	private BusinessService businessService;
	
	@Test
	void test_ThatABusinessCanBeCreated() {
		Business business = new Business("McDonalds", BusinessType.RESTAURANT, "Glasgow","1 Happy Lane", "013975749834", "mcdonalds@clown.com");
		businessService.save(business);
		assertNotNull(business);
	}
	
	@Test
	void test_ThatAllBusinessesCanBeRetrieved() {
		List<Business> allBusinesses = businessService.findAll();
		assertNotNull(allBusinesses);
	}
	
	@Test
	void test_ThatABusinessCanBeFoundById() {
		Business foundBusiness = businessService.findById(1L);
		assertNotNull(foundBusiness);
	}
	
	@Test
	void test_ThatABusinessCanBeFoundByLocation() {
		List<Business> allBusiness = businessService.findByLocation("Glasgow");
		assertNotNull(allBusiness);
	}
	
	@Test
	void test_ThatABusinessCanBeFoundByLocationIgnoringCase() {
		List<Business> allBusiness = businessService.findByLocation("gLaSgOw");
		assertNotNull(allBusiness);
	}
	
}
