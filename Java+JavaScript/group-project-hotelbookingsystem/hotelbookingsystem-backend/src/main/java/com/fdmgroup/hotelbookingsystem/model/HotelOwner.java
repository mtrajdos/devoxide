package com.fdmgroup.hotelbookingsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class HotelOwner extends User {

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "hotelOwner_hotel", joinColumns =
	@JoinColumn(name = "userId"), inverseJoinColumns =
	@JoinColumn(name = "hotelId"))
	private List<Hotel> hotels;

	public HotelOwner(){
		super();
	}

	public HotelOwner(String username, String password) {
		super(username, password);
	}

	public HotelOwner(String username, String password, String firstName, String lastName, String address, String email, Role role) {
		super(username, password, firstName, lastName, address, email, role);
		this.hotels = new ArrayList<>();
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof HotelOwner)) return false;
		if (!super.equals(o)) return false;
		HotelOwner that = (HotelOwner) o;
		return getHotels().equals(that.getHotels());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getHotels());
	}

	@Override
	public String toString() {
		return "HotelOwner{" +
				"hotels=" + hotels +
				'}';
	}

}