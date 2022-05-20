package com.fdmgroup.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.demo.model.Character;

public interface CharacterDao extends JpaRepository<Character, Long> {

	Optional<Character> findByNameContainingIgnoreCase(@Param("name") String name);

	Optional<Character> findById(@Param("id") Long id);

}
