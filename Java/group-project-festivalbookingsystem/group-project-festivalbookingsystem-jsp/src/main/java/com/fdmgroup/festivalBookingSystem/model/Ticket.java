package com.fdmgroup.festivalBookingSystem.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_gen")
	@SequenceGenerator(name = "ticket_gen", sequenceName = "TICKET_SEQ", allocationSize = 1)
	private long ticket_id;
	
	@Column(nullable = false)
	private String ticketName;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime ticketStartDate;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime ticketEndDate;

	public Ticket(String ticketName, LocalDateTime ticketStartDate, LocalDateTime ticketEndDate) {
		super();
		this.ticketName = ticketName;
		this.ticketStartDate = ticketStartDate;
		this.ticketEndDate = ticketEndDate;
	}

	public Ticket() {}

	public long getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(long ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public LocalDateTime getTicketStartDate() {
		return ticketStartDate;
	}

	public void setTicketStartDate(LocalDateTime ticketStartDate) {
		this.ticketStartDate = ticketStartDate;
	}

	public LocalDateTime getTicketEndDate() {
		return ticketEndDate;
	}

	public void setTicketEndDate(LocalDateTime ticketEndDate) {
		this.ticketEndDate = ticketEndDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ticketEndDate == null) ? 0 : ticketEndDate.hashCode());
		result = prime * result + ((ticketName == null) ? 0 : ticketName.hashCode());
		result = prime * result + ((ticketStartDate == null) ? 0 : ticketStartDate.hashCode());
		result = prime * result + (int) (ticket_id ^ (ticket_id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (ticketEndDate == null) {
			if (other.ticketEndDate != null)
				return false;
		} else if (!ticketEndDate.equals(other.ticketEndDate))
			return false;
		if (ticketName == null) {
			if (other.ticketName != null)
				return false;
		} else if (!ticketName.equals(other.ticketName))
			return false;
		if (ticketStartDate == null) {
			if (other.ticketStartDate != null)
				return false;
		} else if (!ticketStartDate.equals(other.ticketStartDate))
			return false;
		if (ticket_id != other.ticket_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [ticket_id=" + ticket_id + ", ticketName=" + ticketName + ", ticketStartDate=" + ticketStartDate
				+ ", ticketEndDate=" + ticketEndDate + "]";
	}

}
