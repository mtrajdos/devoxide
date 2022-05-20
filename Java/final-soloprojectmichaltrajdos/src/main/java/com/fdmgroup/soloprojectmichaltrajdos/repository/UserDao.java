package com.fdmgroup.soloprojectmichaltrajdos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.soloprojectmichaltrajdos.model.User;

public interface UserDao extends JpaRepository<User, Long> {

	Optional<User> findByFirstnameAndLastname(@Param("firstname") String firstname, @Param("lastname") String lastname);

	Optional<User> findByFirstname(@Param("firstname") String firstname);

}
