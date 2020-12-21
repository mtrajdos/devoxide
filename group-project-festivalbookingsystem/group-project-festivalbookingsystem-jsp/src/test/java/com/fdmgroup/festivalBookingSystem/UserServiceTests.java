package com.fdmgroup.festivalBookingSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.festivalBookingSystem.model.User;
import com.fdmgroup.festivalBookingSystem.model.UserType;
import com.fdmgroup.festivalBookingSystem.service.UserService;

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
	public void whenKnownUserFindByUsernameAndPasswordThenUserExists() {
		Optional<User> user = userService.findByUsernameAndPassword("something@gmail.com", "1234");
		assertEquals(1, user.get().getUser_id());
	}

	@Test
	public void whenUnknownUserFindByUsernameAndPasswordThenUserDoesNotExist() {
		Optional<User> user = userService.findByUsernameAndPassword("unknown", "password");
		assertTrue(user.isEmpty());
	}

	@Test
	public void whenKnownUserFindByUsernameThenUserExists() {
		User user = userService.findByUsername("something@gmail.com").get();
		assertEquals(1, user.getUser_id());
	}

	@Test
	public void whenUnknownUserFindByUsernameThenUserDoesNotExist() {
		Optional<User> user = userService.findByUsername("unknown");
		assertTrue(user.isEmpty());
	}
	
	@Test
	public void confirmThatUserCanBeDeletedById() {
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("100.00"));
		user = userService.save(user);
		userService.deleteById(2L);
		Optional<User> deletedUser = userService.findById(2L);
		assertTrue(deletedUser.isEmpty());
	}
	
	@Test
	public void createUserAndConfirmItIsSaved(){
		List<User> allUsersBefore = userService.findAll();
		int sizeOfUserListBefore = allUsersBefore.size();
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("100.00"));
		user = userService.save(user);
		List<User> allUsersAfter = userService.findAll();
		int sizeOfUserListAfter = allUsersAfter.size();
		assertEquals(sizeOfUserListBefore + 1, sizeOfUserListAfter);
	}
	
}
