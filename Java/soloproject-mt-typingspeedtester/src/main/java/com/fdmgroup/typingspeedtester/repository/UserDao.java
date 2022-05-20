package com.fdmgroup.typingspeedtester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.typingspeedtester.model.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	User findByUsername(@Param("username") String username);

	User findByUserId(@Param("userId") Long userId);
	
}
