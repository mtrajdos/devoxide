package com.fdmgroup.soloprojectmichaltrajdos.model;

import java.math.BigDecimal;
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

@Entity(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
	@SequenceGenerator(name = "user_gen", sequenceName = "USER_SEQ", allocationSize = 1)
	private long user_id;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;
	
	@ManyToMany
	@JoinColumn(name = "user_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Item> userBasketItems;
	
	@Column
	private BigDecimal totalBasketPrice;

	public User() {
	}

	public User(String firstname, String lastname, BigDecimal totalBasketPrice) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.userBasketItems = new ArrayList<Item>();
		this.totalBasketPrice = totalBasketPrice;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFullname() {
		return firstname + " " + lastname;
	}

	public List<Item> getUserBasketItems() {
		return userBasketItems;
	}

	public void setUserBasketItems(List<Item> userBasketItems) {
		this.userBasketItems = userBasketItems;
	}

	public BigDecimal getTotalBasketPrice() {
		return totalBasketPrice;
	}

	public void setTotalBasketPrice(BigDecimal totalBasketPrice) {
		this.totalBasketPrice = totalBasketPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((totalBasketPrice == null) ? 0 : totalBasketPrice.hashCode());
		result = prime * result + ((userBasketItems == null) ? 0 : userBasketItems.hashCode());
		result = prime * result + (int) (user_id ^ (user_id >>> 32));
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
		User other = (User) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (totalBasketPrice == null) {
			if (other.totalBasketPrice != null)
				return false;
		} else if (!totalBasketPrice.equals(other.totalBasketPrice))
			return false;
		if (userBasketItems == null) {
			if (other.userBasketItems != null)
				return false;
		} else if (!userBasketItems.equals(other.userBasketItems))
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", firstname=" + firstname + ", lastname=" + lastname + ", userBasketItems="
				+ userBasketItems + ", totalBasketPrice=" + totalBasketPrice + "]";
	}

	
}
