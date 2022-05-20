package com.fdmgroup.festivalBookingSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Band {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "band_gen")
	@SequenceGenerator(name = "band_gen", sequenceName = "BAND_SEQ", allocationSize = 1)
	private long band_id;
	
	@Column(unique = true)
	private String bandName;
	
	@Enumerated(EnumType.STRING)
	Genre genre;
	
	public Band() {}

	public Band(String bandName, Genre genre) {
		super();
		this.bandName = bandName;
		this.genre = genre;
	}

	public long getBand_id() {
		return band_id;
	}

	public void setBand_id(long band_id) {
		this.band_id = band_id;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandname) {
		this.bandName = bandname;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Band [band_id=" + band_id + ", bandName=" + bandName + ", genre=" + genre + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bandName == null) ? 0 : bandName.hashCode());
		result = prime * result + (int) (band_id ^ (band_id >>> 32));
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
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
		Band other = (Band) obj;
		if (bandName == null) {
			if (other.bandName != null)
				return false;
		} else if (!bandName.equals(other.bandName))
			return false;
		if (band_id != other.band_id)
			return false;
		if (genre != other.genre)
			return false;
		return true;
	}
	
	
	
}
