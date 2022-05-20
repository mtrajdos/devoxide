package com.fdmgroup.festivalBookingSystem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Stage {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stage_gen")
	@SequenceGenerator(name = "stage_gen", sequenceName = "STAGE_SEQ", allocationSize = 1)
	private long stage_id;

	@Column
	private String stageName;

	@ManyToMany
	@JoinColumn(name = "stage_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Band> bandsInStage;

	public Stage() {
	}

	public Stage(String stageName) {
		super();
		this.stageName = stageName;
		this.bandsInStage = new ArrayList<Band>();

	}

	public long getStage_id() {
		return stage_id;
	}

	public void setStage_id(long stage_id) {
		this.stage_id = stage_id;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public List<Band> getBandsInStage() {
		return bandsInStage;
	}

	public void setBandsInStage(List<Band> bandsInStage) {
		this.bandsInStage = bandsInStage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bandsInStage == null) ? 0 : bandsInStage.hashCode());
		result = prime * result + ((stageName == null) ? 0 : stageName.hashCode());
		result = prime * result + (int) (stage_id ^ (stage_id >>> 32));
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
		Stage other = (Stage) obj;
		if (bandsInStage == null) {
			if (other.bandsInStage != null)
				return false;
		} else if (!bandsInStage.equals(other.bandsInStage))
			return false;
		if (stageName == null) {
			if (other.stageName != null)
				return false;
		} else if (!stageName.equals(other.stageName))
			return false;
		if (stage_id != other.stage_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stage [stage_id=" + stage_id + ", stageName=" + stageName + ", bandsInStage=" + bandsInStage + "]";
	}

}
