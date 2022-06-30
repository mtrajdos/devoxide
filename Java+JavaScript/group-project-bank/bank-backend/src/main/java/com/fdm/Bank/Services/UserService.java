package com.fdm.Bank.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fdm.Bank.Models.NewUserRequest;
import com.fdm.Bank.Models.User;
import com.fdm.Bank.Repository.UserDao;

@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public Optional<User> findById(long userId) {
		return userDao.findById(userId);
	}
	
	public Optional<User> findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public Optional<User> findByEmailAndPassword(String email, String password) {
		return userDao.findByEmailAndPassword(email, password);
	}

	public Optional<User> signup(String firstName, String lastName, String email, String password) {
//		User user = new User(firstName, lastName, email, password);
//		userDao.save(user);
//		return Optional.of(user);
		Optional<User> user = Optional.empty();
		if(!userDao.findByEmail(email).isPresent()) {
			user = Optional.of(userDao.save(new User(firstName, lastName, email, password)));
		}
		return null;
	}

	
	public Optional<User> signin(String email, String password) {
		LOGGER.info("User attempting to sign in");
		Optional<User> user = userDao.findByEmailAndPassword(email, password);
		if(user.isPresent()) {
			LOGGER.info("Log in success for user {}", email);
			return user;
		}
		LOGGER.info("Log in failure for user {}", email);
		return Optional.empty();
	}


	public List<User> findAll() {
		return userDao.findAll();
	}

	public void save(User user) {
		 userDao.save(user);
	}


}
