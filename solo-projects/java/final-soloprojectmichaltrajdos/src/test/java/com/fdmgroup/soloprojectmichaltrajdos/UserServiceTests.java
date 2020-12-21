package com.fdmgroup.soloprojectmichaltrajdos;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.soloprojectmichaltrajdos.model.Item;
import com.fdmgroup.soloprojectmichaltrajdos.model.User;
import com.fdmgroup.soloprojectmichaltrajdos.service.ItemService;
import com.fdmgroup.soloprojectmichaltrajdos.service.UserService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTests {

	@Autowired
	UserService userService;
	
	@Autowired
	ItemService itemService;
	
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
	public void confirmThatUserCanBeFoundByFirstNameAndLastName() {
		Optional<User> user = userService.findByFirstnameAndLastname("Michal", "Trajdos");
		assertEquals(1, user.get().getUser_id());
	}

	@Test
	public void confirmThatUserCannotBeFoundByNonExistingFirstNameAndLastName() {
		Optional<User> user = userService.findByFirstnameAndLastname("nonexistingfirstname", "nonexistinglastname");
		assertTrue(user.isEmpty());
	}

	@Test
	public void confirmThatUserCanBeFoundByFirstName() {
		User user = userService.findByFirstname("Michal").get();
		assertEquals(1, user.getUser_id());
	}

	@Test
	public void confirmThatUserCannotBeFoundByNonExistingFirstName() {
		Optional<User> user = userService.findByFirstname("nonexistingfirstname");
		assertTrue(user.isEmpty());
	}
	
	@Test
	public void createUserAndConfirmItIsSaved() {
		List<User> allUsersBefore = userService.findAll();
		int sizeOfUserListBefore = allUsersBefore.size();
		User user = new User("bob", "vance", new BigDecimal("0.00"));
		user = userService.save(user);
		List<User> allUsersAfter = userService.findAll();
		int sizeOfUserListAfter = allUsersAfter.size();
		assertEquals(sizeOfUserListBefore + 1, sizeOfUserListAfter);
	}
	
	@Test
	public void testThatItemsAreInsertedCorrectlyIntoUserBasket() {
		Optional<User> checkedUser = userService.findById(1L);
		List<Item> checkedUserItems = checkedUser.get().getUserBasketItems();
		System.err.println(checkedUserItems);
		int expectedBasketSize = 3;
		int actualBasketSize = checkedUserItems.size();
		assertEquals(expectedBasketSize, actualBasketSize);
	}
	
	@Test
	public void testThatTotalBasketPriceIsCalculatedCorrectlyForUserWith2Items() {
		User user = new User("bob", "vance", new BigDecimal("0.00"));
		Item item1 = new Item("Test Item", "Test Item Desc", new BigDecimal("100.00"), 1);
		itemService.save(item1);
		Item item2 = new Item("Test Item2", "Test Item Desc2", new BigDecimal("50.00"), 1);
		itemService.save(item2);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		user.setUserBasketItems(items);
		userService.save(user);
		BigDecimal totalBasketPriceBefore = user.getTotalBasketPrice();
		userService.updateTotalBasketPrice(user);
		BigDecimal totalBasketPriceAfter = user.getTotalBasketPrice();
		assertEquals(totalBasketPriceBefore.add(new BigDecimal("150.00")), totalBasketPriceAfter);
	}
	
	@Test
	public void testThatTotalBasketPriceIs0ForUserWithNothingInBasket() {
		User user = new User("bob", "vance", new BigDecimal("1000.00"));
		List<Item> items = new ArrayList<>();
		user.setUserBasketItems(items);
		userService.save(user);
		userService.updateTotalBasketPrice(user);
		assertEquals(new BigDecimal("0.00"), user.getTotalBasketPrice());
	}
	
}
