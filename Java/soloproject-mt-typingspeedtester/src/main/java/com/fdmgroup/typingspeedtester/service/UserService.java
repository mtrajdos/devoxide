package com.fdmgroup.typingspeedtester.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.typingspeedtester.model.User;
import com.fdmgroup.typingspeedtester.repository.UserDao;

@Service
public class UserService implements UserServiceRepository<User> {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public User findByUserId(Long userId) {
		return userDao.findByUserId(userId);
	}
}
