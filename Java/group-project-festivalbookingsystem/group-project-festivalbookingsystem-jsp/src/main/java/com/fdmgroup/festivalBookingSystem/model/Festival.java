package com.fdmgroup.festivalBookingSystem.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Festival {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "festival_gen")
	@SequenceGenerator(name = "festival_gen", sequenceName = "FESTIVAL_SEQ", allocationSize = 1)
	private long festival_id;

	@Column
	private String festivalName;

	@Column
	private String location;

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startDate;

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endDate;

	@ManyToMany
	@JoinColumn(name = "festival_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Band> festivalBands;
	
	@OneToMany
	@JoinColumn(name = "festival_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Stage> festivalStages;
	
	@OneToMany
	@JoinColumn(name = "festival_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Bus> festivalBuses;

	@Enumerated(EnumType.STRING)
	Genre genre;

	@Column
	private int baseTotalTickets;

	@Column
	private int currentTickets;

	@Column
	private BigDecimal ticketPrice;
	
	@Column
	private BigDecimal originalPrice;

	@Column
	private boolean earlyBird;
	
	@Column
	private boolean secondRelease;
	
	@Column
	private String onSiteAccommodation;

	public Festival() {
	}

	public Festival(String festivalName, String location, LocalDateTime startDate, LocalDateTime endDate, Genre genre,
			int currentTickets, BigDecimal ticketPrice, boolean earlyBird, boolean secondRelease, String onSiteAccommodation) {
		super();
		this.festivalName = festivalName;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.festivalBands = new ArrayList<Band>();
		this.festivalStages = new ArrayList<Stage>();
		this.festivalBuses = new ArrayList<Bus>();
		this.genre = genre;
		this.baseTotalTickets = currentTickets;
		this.currentTickets = currentTickets;
		this.originalPrice = ticketPrice;
		this.ticketPrice = ticketPrice;
		this.earlyBird = earlyBird;
		this.secondRelease = secondRelease;
		this.onSiteAccommodation = onSiteAccommodation;
	}

	public long getFestival_id() {
		return festival_id;
	}

	public void setFestival_id(long festival_id) {
		this.festival_id = festival_id;
	}

	public String getFestivalName() {
		return festivalName;
	}

	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public List<Band> getFestivalBands() {
		return festivalBands;
	}

	public void setFestivalBands(List<Band> festivalBands) {
		this.festivalBands = festivalBands;
	}
	
	public List<Bus> getFestivalBuses() {
		return festivalBuses;
	}

	public void setFestivalBuses(List<Bus> festivalBuses) {
		this.festivalBuses = festivalBuses;
	}

	public List<Stage> getFestivalStages() {
		return festivalStages;
	}

	public void setFestivalStages(List<Stage> festivalStages) {
		this.festivalStages = festivalStages;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public int getBaseTotalTickets() {
		return baseTotalTickets;
	}

	public void setBaseTotalTickets(int baseTotalTickets) {
		this.baseTotalTickets = baseTotalTickets;
	}

	public int getCurrentTickets() {
		return currentTickets;
	}

	public void setCurrentTickets(int currentTickets) {
		this.currentTickets = currentTickets;
	}

	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public boolean isEarlyBird() {
		return earlyBird;
	}

	public void setEarlyBird(boolean earlyBird) {
		this.earlyBird = earlyBird;
	}

	public boolean isSecondRelease() {
		return secondRelease;
	}

	public void setSecondRelease(boolean secondRelease) {
		this.secondRelease = secondRelease;
	}

	public String getOnSiteAccommodation() {
		return onSiteAccommodation;
	}

	public void setOnSiteAccommodation(String onSiteAccommodation) {
		this.onSiteAccommodation = onSiteAccommodation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baseTotalTickets;
		result = prime * result + currentTickets;
		result = prime * result + (earlyBird ? 1231 : 1237);
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((festivalBands == null) ? 0 : festivalBands.hashCode());
		result = prime * result + ((festivalBuses == null) ? 0 : festivalBuses.hashCode());
		result = prime * result + ((festivalName == null) ? 0 : festivalName.hashCode());
		result = prime * result + ((festivalStages == null) ? 0 : festivalStages.hashCode());
		result = prime * result + (int) (festival_id ^ (festival_id >>> 32));
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((onSiteAccommodation == null) ? 0 : onSiteAccommodation.hashCode());
		result = prime * result + ((originalPrice == null) ? 0 : originalPrice.hashCode());
		result = prime * result + (secondRelease ? 1231 : 1237);
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((ticketPrice == null) ? 0 : ticketPrice.hashCode());
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
		Festival other = (Festival) obj;
		if (baseTotalTickets != other.baseTotalTickets)
			return false;
		if (currentTickets != other.currentTickets)
			return false;
		if (earlyBird != other.earlyBird)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (festivalBands == null) {
			if (other.festivalBands != null)
				return false;
		} else if (!festivalBands.equals(other.festivalBands))
			return false;
		if (festivalBuses == null) {
			if (other.festivalBuses != null)
				return false;
		} else if (!festivalBuses.equals(other.festivalBuses))
			return false;
		if (festivalName == null) {
			if (other.festivalName != null)
				return false;
		} else if (!festivalName.equals(other.festivalName))
			return false;
		if (festivalStages == null) {
			if (other.festivalStages != null)
				return false;
		} else if (!festivalStages.equals(other.festivalStages))
			return false;
		if (festival_id != other.festival_id)
			return false;
		if (genre != other.genre)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (onSiteAccommodation == null) {
			if (other.onSiteAccommodation != null)
				return false;
		} else if (!onSiteAccommodation.equals(other.onSiteAccommodation))
			return false;
		if (originalPrice == null) {
			if (other.originalPrice != null)
				return false;
		} else if (!originalPrice.equals(other.originalPrice))
			return false;
		if (secondRelease != other.secondRelease)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (ticketPrice == null) {
			if (other.ticketPrice != null)
				return false;
		} else if (!ticketPrice.equals(other.ticketPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Festival [festival_id=" + festival_id + ", festivalName=" + festivalName + ", location=" + location
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", festivalBands=" + festivalBands
				+ ", festivalStages=" + festivalStages + ", festivalBuses=" + festivalBuses + ", genre=" + genre
				+ ", baseTotalTickets=" + baseTotalTickets + ", currentTickets=" + currentTickets + ", ticketPrice="
				+ ticketPrice + ", originalPrice=" + originalPrice + ", earlyBird=" + earlyBird + ", secondRelease="
				+ secondRelease + ", onSiteAccommodation=" + onSiteAccommodation + "]";
	}

	
}