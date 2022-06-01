package com.fdmgroup.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.demo.model.User;
import com.fdmgroup.demo.repository.UserDao;

@Service
public class UserService implements UserServiceRepository<User> {

	@Autowired
	private UserDao userDao;

	@Override
	public Optional<User> findById(Long id) {
		return userDao.findById(id);
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

	public boolean deleteById(long id) {
		boolean result = false;
		if (userDao.count() > 1) {
			userDao.deleteById(id);
			result = true;
		}
		return result;
	}

}
