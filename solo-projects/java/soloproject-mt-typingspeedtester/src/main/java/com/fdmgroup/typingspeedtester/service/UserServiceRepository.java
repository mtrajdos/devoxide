package com.fdmgroup.typingspeedtester.service;

public interface UserServiceRepository<User> {

	User findByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
	
	void save(User user);
	
	User findByUserId(Long userId);
	
}
