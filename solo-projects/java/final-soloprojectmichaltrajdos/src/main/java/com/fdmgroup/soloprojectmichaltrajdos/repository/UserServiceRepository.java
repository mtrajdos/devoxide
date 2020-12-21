package com.fdmgroup.soloprojectmichaltrajdos.repository;

import java.util.List;
import java.util.Optional;

public interface UserServiceRepository<User> {

	Optional<User> findById(Long user_id);

	List<User> findAll();

	User save(User user);

	Optional<User> findByFirstnameAndLastname(String firstname, String lastname);

	Optional<User> findByFirstname(String firstname);
	
}
