package com.fdmgroup.festivalBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.festivalBookingSystem.model.Ticket;

public interface TicketDao extends JpaRepository<Ticket, Long> {

}