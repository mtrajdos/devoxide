package com.fdmgroup.hotelbookingsystem.repository;

import com.fdmgroup.hotelbookingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
