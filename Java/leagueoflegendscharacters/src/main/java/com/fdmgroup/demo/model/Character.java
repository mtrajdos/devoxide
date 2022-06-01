package com.fdmgroup.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Character {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_gen")
	@SequenceGenerator(name = "character_gen", sequenceName = "CHARACTER_SEQ", allocationSize = 1)
	private long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Enumerated(EnumType.STRING)
	private Position position;

	@Enumerated(EnumType.STRING)
	private DamageSource damageSource;

	@Column(nullable = false, length = 100)
	private String titleQ;

	@Column(nullable = false, length = 10000)
	private String abilityQDescription;

	@Column(nullable = false, length = 100)
	private String titleW;

	@Column(nullable = false, length = 10000)
	private String abilityWDescription;

	@Column(nullable = false, length = 100)
	private String titleE;

	@Column(nullable = false, length = 10000)
	private String abilityEDescription;

	@Column(nullable = false, length = 100)
	private String titleR;

	@Column(nullable = false, length = 10000)
	private String abilityRDescription;

	@Column(nullable = false, length = 100)
	private String titlePassive;

	@Column(nullable = false, length = 10000)
	private String passiveAbilityDescription;

	public Character() {
	}

	public Character(String name, Position position, DamageSource damageSource, String titleQ,
			String abilityQDescription, String titleW, String abilityWDescription, String titleE,
			String abilityEDescription, String titleR, String abilityRDescription, String titlePassive,
			String passiveAbilityDescription) {
		super();
		this.name = name;
		this.position = position;
		this.damageSource = damageSource;
		this.titleQ = titleQ;
		this.abilityQDescription = abilityQDescription;
		this.titleW = titleW;
		this.abilityWDescription = abilityWDescription;
		this.titleE = titleE;
		this.abilityEDescription = abilityEDescription;
		this.titleR = titleR;
		this.abilityRDescription = abilityRDescription;
		this.titlePassive = titlePassive;
		this.passiveAbilityDescription = passiveAbilityDescription;
	}

	public Character(String name, Position position) {
		super();
		this.name = name;
		this.position = position;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public DamageSource getDamageSource() {
		return damageSource;
	}

	public void setDamageSource(DamageSource damageSource) {
		this.damageSource = damageSource;
	}

	public String getTitleQ() {
		return titleQ;
	}

	public void setTitleQ(String titleQ) {
		this.titleQ = titleQ;
	}

	public String getAbilityQDescription() {
		return abilityQDescription;
	}

	public void setAbilityQDescription(String abilityQDescription) {
		this.abilityQDescription = abilityQDescription;
	}

	public String getTitleW() {
		return titleW;
	}

	public void setTitleW(String titleW) {
		this.titleW = titleW;
	}

	public String getAbilityWDescription() {
		return abilityWDescription;
	}

	public void setAbilityWDescription(String abilityWDescription) {
		this.abilityWDescription = abilityWDescription;
	}

	public String getTitleE() {
		return titleE;
	}

	public void setTitleE(String titleE) {
		this.titleE = titleE;
	}

	public String getAbilityEDescription() {
		return abilityEDescription;
	}

	public void setAbilityEDescription(String abilityEDescription) {
		this.abilityEDescription = abilityEDescription;
	}

	public String getTitleR() {
		return titleR;
	}

	public void setTitleR(String titleR) {
		this.titleR = titleR;
	}

	public String getAbilityRDescription() {
		return abilityRDescription;
	}

	public void setAbilityRDescription(String abilityRDescription) {
		this.abilityRDescription = abilityRDescription;
	}

	public String getTitlePassive() {
		return titlePassive;
	}

	public void setTitlePassive(String titlePassive) {
		this.titlePassive = titlePassive;
	}

	public String getPassiveAbilityDescription() {
		return passiveAbilityDescription;
	}

	public void setPassiveAbilityDescription(String passiveAbilityDescription) {
		this.passiveAbilityDescription = passiveAbilityDescription;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", position=" + position + ", damageSource=" + damageSource
				+ ", titleQ=" + titleQ + ", abilityQDescription=" + abilityQDescription + ", titleW=" + titleW
				+ ", abilityWDescription=" + abilityWDescription + ", titleE=" + titleE + ", abilityEDescription="
				+ abilityEDescription + ", titleR=" + titleR + ", abilityRDescription=" + abilityRDescription
				+ ", titlePassive=" + titlePassive + ", passiveAbilityDescription=" + passiveAbilityDescription + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abilityEDescription == null) ? 0 : abilityEDescription.hashCode());
		result = prime * result + ((abilityQDescription == null) ? 0 : abilityQDescription.hashCode());
		result = prime * result + ((abilityRDescription == null) ? 0 : abilityRDescription.hashCode());
		result = prime * result + ((abilityWDescription == null) ? 0 : abilityWDescription.hashCode());
		result = prime * result + ((damageSource == null) ? 0 : damageSource.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passiveAbilityDescription == null) ? 0 : passiveAbilityDescription.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((titleE == null) ? 0 : titleE.hashCode());
		result = prime * result + ((titlePassive == null) ? 0 : titlePassive.hashCode());
		result = prime * result + ((titleQ == null) ? 0 : titleQ.hashCode());
		result = prime * result + ((titleR == null) ? 0 : titleR.hashCode());
		result = prime * result + ((titleW == null) ? 0 : titleW.hashCode());
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
		Character other = (Character) obj;
		if (abilityEDescription == null) {
			if (other.abilityEDescription != null)
				return false;
		} else if (!abilityEDescription.equals(other.abilityEDescription))
			return false;
		if (abilityQDescription == null) {
			if (other.abilityQDescription != null)
				return false;
		} else if (!abilityQDescription.equals(other.abilityQDescription))
			return false;
		if (abilityRDescription == null) {
			if (other.abilityRDescription != null)
				return false;
		} else if (!abilityRDescription.equals(other.abilityRDescription))
			return false;
		if (abilityWDescription == null) {
			if (other.abilityWDescription != null)
				return false;
		} else if (!abilityWDescription.equals(other.abilityWDescription))
			return false;
		if (damageSource != other.damageSource)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passiveAbilityDescription == null) {
			if (other.passiveAbilityDescription != null)
				return false;
		} else if (!passiveAbilityDescription.equals(other.passiveAbilityDescription))
			return false;
		if (position != other.position)
			return false;
		if (titleE == null) {
			if (other.titleE != null)
				return false;
		} else if (!titleE.equals(other.titleE))
			return false;
		if (titlePassive == null) {
			if (other.titlePassive != null)
				return false;
		} else if (!titlePassive.equals(other.titlePassive))
			return false;
		if (titleQ == null) {
			if (other.titleQ != null)
				return false;
		} else if (!titleQ.equals(other.titleQ))
			return false;
		if (titleR == null) {
			if (other.titleR != null)
				return false;
		} else if (!titleR.equals(other.titleR))
			return false;
		if (titleW == null) {
			if (other.titleW != null)
				return false;
		} else if (!titleW.equals(other.titleW))
			return false;
		return true;
	}

}