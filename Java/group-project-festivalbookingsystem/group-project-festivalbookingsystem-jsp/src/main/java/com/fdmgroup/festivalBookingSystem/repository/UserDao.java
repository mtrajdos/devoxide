package com.fdmgroup.festivalBookingSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.festivalBookingSystem.model.User;

public interface UserDao extends JpaRepository<User, Long> {

	Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	Optional<User> findByUsername(@Param("username") String username);

}
