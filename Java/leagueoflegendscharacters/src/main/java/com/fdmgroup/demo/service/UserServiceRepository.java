package com.fdmgroup.demo.service;

import java.util.List;
import java.util.Optional;

public interface UserServiceRepository<User> {

	Optional<User> findById(Long id);

	List<User> findAll();

	User save(User user);

	Optional<User> findByUsernameAndPassword(String username, String password);

	Optional<User> findByUsername(String username);
}
