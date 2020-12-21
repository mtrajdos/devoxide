package com.fdmgroup.festivalBookingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.festivalBookingSystem.model.Ticket;
import com.fdmgroup.festivalBookingSystem.repository.TicketDao;
import com.fdmgroup.festivalBookingSystem.repository.TicketServiceRepository;

@Service
public class TicketService implements TicketServiceRepository<Ticket> {

	@Autowired
	private TicketDao ticketDao;
	
	@Override
	public void save(Ticket ticket) {
		ticketDao.save(ticket);
		
	}
}
