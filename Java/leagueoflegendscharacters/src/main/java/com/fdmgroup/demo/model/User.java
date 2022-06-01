package com.fdmgroup.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
	@SequenceGenerator(name = "user_gen", sequenceName = "USER_SEQ", allocationSize = 1)
	private long id;

	@Column(nullable = false, length = 80, unique = true)
	private String username;

	@Column(nullable = false, length = 80)
	private String password;

	@Column
	private boolean verified = false;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	public User() {
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.verified = false;
		this.userType = UserType.SUMMONER;
	}

	public User(String username, String password, boolean verified, UserType userType) {
		super();
		this.username = username;
		this.password = password;
		this.verified = verified;
		this.userType = userType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", verified=" + verified
				+ ", userType=" + userType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		return true;
	}
}
