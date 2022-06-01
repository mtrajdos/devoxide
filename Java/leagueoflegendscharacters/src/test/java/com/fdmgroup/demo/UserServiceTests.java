package com.fdmgroup.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.demo.model.User;
import com.fdmgroup.demo.service.UserService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTests {

	@Autowired
	UserService userService;
	
	@Test
	public void confirmThatAllUsersCanBeRetrieved() {
		List<User> allUsers = userService.findAll();
		assertNotNull(allUsers);
	}
	
	@Test
	public void confirmThatUserCanBeFoundById() {
		long chosenUserId = 1;
		Optional<User> foundUser = userService.findById(chosenUserId);
		assertFalse(foundUser.isEmpty());
	}
	
	@Test
	public void confirmThatUserCanBeDeletedById() {
		long chosenUserId = 1;
		userService.deleteById(chosenUserId);
		Optional<User> deletedUser = userService.findById(chosenUserId);
		assertTrue(deletedUser.isEmpty());
	}
	
	@Test
	public void whenKnownUserFindByUsernameAndPasswordThenUserExists() {
		Optional<User> user = userService.findByUsernameAndPassword("michal.trajdos", "1234");
		assertEquals(1, user.get().getId());
	}

	@Test
	public void whenUnknownUserFindByUsernameAndPasswordThenUserDoesNotExist() {
		Optional<User> user = userService.findByUsernameAndPassword("unknown", "password");
		assertTrue(user.isEmpty());
	}

	@Test
	public void whenKnownUserFindByUsernameThenUserExists() {
		User user = userService.findByUsername("michal.trajdos").get();
		assertEquals(1, user.getId());
	}

	@Test
	public void whenUnknownUserFindByUsernameThenUserDoesNotExist() {
		Optional<User> user = userService.findByUsername("unknown");
		assertTrue(user.isEmpty());
	}

	@Test
	public void createUserAndConfirmItIsSaved(){
		User user = new User("newuserusername", "newuserpassword");
		user = userService.save(user);
		assertEquals(4L, user.getId());
	}
}
