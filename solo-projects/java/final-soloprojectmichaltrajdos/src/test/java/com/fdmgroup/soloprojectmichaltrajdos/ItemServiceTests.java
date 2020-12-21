package com.fdmgroup.soloprojectmichaltrajdos;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
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
public class ItemServiceTests {

	@Autowired
	ItemService itemService;
	
	@Autowired
	UserService userService;
	
	@Test
	public void confirmThatAllItemsCanBeRetrieved() {
		List<Item> allItems = itemService.findAll();
		assertNotNull(allItems);
	}
	
	@Test
	public void confirmThatItemCanBeFoundById() {
		long chosenItemId = 1;
		Optional<Item> foundItem = itemService.findById(chosenItemId);
		assertFalse(foundItem.isEmpty());
	}
	
	@Test
	public void confirmThatItemCanBeCreatedAndSaved() {
		int allItemsBefore = itemService.findAll().size();
		Item item = new Item("Test Item", "Test Item Desc", new BigDecimal("100.00"), 1);
		itemService.save(item);
		int allItemsAfter = itemService.findAll().size();
		assertEquals(allItemsBefore + 1, allItemsAfter);
	}
	
	@Test
	public void confirmThatCheckStockReturnsFalseIfAtLeastOneItemInStockForEachItemInUserBasket() {
		User buyingUser = userService.findById(1L).get();
		assertFalse(itemService.checkStock(buyingUser));
	}
	
	@Test
	public void confirmThatCheckStockReturnsTrueIfAtLeastOneItemNotInStockInUserBasket() {
		User buyingUser = userService.findById(2L).get();
		assertTrue(itemService.checkStock(buyingUser));
	}
	
	@Test
	public void confirmThatBuyBasketClearsBasketIfAllItemsInStock() {
		User buyingUser = userService.findById(1L).get();
		itemService.buyBasket(buyingUser);
		assertEquals(0, buyingUser.getUserBasketItems().size());
	}
	
	@Test
	public void confirmThatBuyBasketDoesNotClearBasketIfAtLeastOneItemNotInStock() {
		User buyingUser = userService.findById(2L).get();
		int sizeOfBasketBeforeBuying = buyingUser.getUserBasketItems().size();
		itemService.buyBasket(buyingUser);
		int sizeOfBasketAfterBuying = buyingUser.getUserBasketItems().size();
		assertEquals(sizeOfBasketBeforeBuying, sizeOfBasketAfterBuying);
	}
	
}
