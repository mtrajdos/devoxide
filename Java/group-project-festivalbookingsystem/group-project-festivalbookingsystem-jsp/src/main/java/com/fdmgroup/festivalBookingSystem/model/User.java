package com.fdmgroup.festivalBookingSystem.model;

import java.math.BigDecimal;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
	@SequenceGenerator(name = "user_gen", sequenceName = "USER_SEQ", allocationSize = 1)
	private long user_id;

	@Column(nullable = false, length = 80)
	private String username;

	@Column(nullable = false, length = 80)
	private String firstname;

	@Column(nullable = false, length = 80)
	private String lastname;

	@Column(nullable = false, length = 80)
	private String password;

	@Enumerated(EnumType.STRING)
	UserType userType;

	@OneToMany
	@JoinColumn(name = "user_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Ticket> userTickets;

	@Column
	private BigDecimal userFunds;

	public User() {
	}

	public User(String username, String firstname, String lastname, String password, UserType userType,
			BigDecimal userFunds) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.userType = userType;
		this.userTickets = new ArrayList<Ticket>();
		this.userFunds = userFunds;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public List<Ticket> getUserTickets() {
		return userTickets;
	}

	public void setUserTickets(List<Ticket> userTickets) {
		this.userTickets = userTickets;
	}

	public BigDecimal getUserFunds() {
		return userFunds;
	}

	public void setUserFunds(BigDecimal userFunds) {
		this.userFunds = userFunds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userFunds == null) ? 0 : userFunds.hashCode());
		result = prime * result + ((userTickets == null) ? 0 : userTickets.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		result = prime * result + (int) (user_id ^ (user_id >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userFunds == null) {
			if (other.userFunds != null)
				return false;
		} else if (!userFunds.equals(other.userFunds))
			return false;
		if (userTickets == null) {
			if (other.userTickets != null)
				return false;
		} else if (!userTickets.equals(other.userTickets))
			return false;
		if (userType != other.userType)
			return false;
		if (user_id != other.user_id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", password=" + password + ", userType=" + userType + ", userTickets=" + userTickets
				+ ", userFunds=" + userFunds + "]";
	}

}
