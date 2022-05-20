package com.fdmgroup.festivalBookingSystem.repository;

public interface TicketServiceRepository<Ticket> {

	void save(Ticket ticket);
}
