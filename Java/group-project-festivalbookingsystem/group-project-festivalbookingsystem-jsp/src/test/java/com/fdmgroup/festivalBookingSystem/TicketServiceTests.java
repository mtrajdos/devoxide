package com.fdmgroup.festivalBookingSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.festivalBookingSystem.model.Ticket;
import com.fdmgroup.festivalBookingSystem.service.TicketService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TicketServiceTests {
	
	@Autowired
	private TicketService ticketService;
	
	@Test
	public void testThatATicketCanBeSaved() {
		Ticket ticket = new Ticket("One way ticket", LocalDateTime.parse("2145-01-01T05:00:00"), LocalDateTime.parse("2145-01-01T05:00:01"));
		ticketService.save(ticket);
		assertNotNull(ticket);
	}

}
