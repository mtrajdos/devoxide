package com.fdmgroup.festivalBookingSystem.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bus_gen")
	@SequenceGenerator(name = "bus_gen", sequenceName = "BUS_SEQ", allocationSize = 1)
	private long bus_id;

	@Column
	private int capacity;
	
	@Column
	private String destination;
	
	@Column
	private String startingLocation;
	
	@Column 
	private LocalDateTime departureTime;
	
	@Column
	private int journeyTimeInMinutes;

	
	
	public Bus() {
		super();
	}



	public Bus(int capacity, String destination, String startingLocation, LocalDateTime departureTime,
			int journeyTimeInMinutes) {
		super();
		this.capacity = capacity;
		this.destination = destination;
		this.startingLocation = startingLocation;
		this.departureTime = departureTime;
		this.journeyTimeInMinutes = journeyTimeInMinutes;
	}



	public long getBus_id() {
		return bus_id;
	}



	public void setBus_id(long bus_id) {
		this.bus_id = bus_id;
	}



	public int getCapacity() {
		return capacity;
	}



	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}



	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}



	public String getStartingLocation() {
		return startingLocation;
	}



	public void setStartingLocation(String startingLocation) {
		this.startingLocation = startingLocation;
	}



	public LocalDateTime getDepartureTime() {
		return departureTime;
	}



	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}



	public int getJourneyTimeInMinutes() {
		return journeyTimeInMinutes;
	}



	public void setJourneyTimeInMinutes(int journeyTimeInMinutes) {
		this.journeyTimeInMinutes = journeyTimeInMinutes;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + journeyTimeInMinutes;
		result = prime * result + ((startingLocation == null) ? 0 : startingLocation.hashCode());
		result = prime * result + (int) (bus_id ^ (bus_id >>> 32));
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
		Bus other = (Bus) obj;
		if (capacity != other.capacity)
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (journeyTimeInMinutes != other.journeyTimeInMinutes)
			return false;
		if (startingLocation == null) {
			if (other.startingLocation != null)
				return false;
		} else if (!startingLocation.equals(other.startingLocation))
			return false;
		if (bus_id != other.bus_id)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Bus [bus_id=" + bus_id + ", capacity=" + capacity + ", destination=" + destination
				+ ", startingLocation=" + startingLocation + ", departureTime=" + departureTime
				+ ", journeyTimeInMinutes=" + journeyTimeInMinutes + "]";
	}
	
	
	
	
}
