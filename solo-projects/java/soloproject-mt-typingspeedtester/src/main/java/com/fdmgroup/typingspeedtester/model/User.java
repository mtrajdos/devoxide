package com.fdmgroup.typingspeedtester.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_gen")
	@SequenceGenerator(name="user_gen", sequenceName="USER_SEQ", allocationSize=1)
	private long userId;
	
	@Column(nullable=false, length=80, unique=true)
	private String username;
	
	@Column(nullable=false, length=80)
	private String password;
	
	@OneToMany
	@JoinColumn(name = "randomWordListId")
	private List<RandomWordList> listOfRandomWordSets;
	
	public User() {
	}

	public User(String username, String password, List<RandomWordList> listOfRandomWordSets) {
		super();
		this.username = username;
		this.password = password;
		this.listOfRandomWordSets = listOfRandomWordSets;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public List<RandomWordList> getListOfRandomWordSets() {
		return listOfRandomWordSets;
	}

	public void setListOfRandomWordSets(List<RandomWordList> listOfRandomWordSets) {
		this.listOfRandomWordSets = listOfRandomWordSets;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password
				+ ", listOfRandomWordSets=" + listOfRandomWordSets + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listOfRandomWordSets == null) ? 0 : listOfRandomWordSets.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		if (listOfRandomWordSets == null) {
			if (other.listOfRandomWordSets != null)
				return false;
		} else if (!listOfRandomWordSets.equals(other.listOfRandomWordSets))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
