package com.fdmgroup.festivalBookingSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.festivalBookingSystem.model.User;
import com.fdmgroup.festivalBookingSystem.repository.UserDao;
import com.fdmgroup.festivalBookingSystem.repository.UserServiceRepository;

@Service
public class UserService implements UserServiceRepository<User> {

	@Autowired
	private UserDao userDao;

	@Override
	public Optional<User> findById(Long user_id) {
		return userDao.findById(user_id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public Optional<User> findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public boolean deleteById(Long user_id) {
		boolean result = false;
		if (userDao.count() > 1) {
			userDao.deleteById(user_id);
			result = true;
		}
		return result;
	}

}
