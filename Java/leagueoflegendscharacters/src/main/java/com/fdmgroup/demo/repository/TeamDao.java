package com.fdmgroup.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.demo.model.Team;

public interface TeamDao extends JpaRepository<Team, Long> {

	Optional<Team> findByNameContainingIgnoreCase(@Param("name") String name);

	Optional<Team> findById(@Param("id") Long id);

}
