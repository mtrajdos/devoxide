package com.fdmgroup.soloprojectmichaltrajdos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.soloprojectmichaltrajdos.model.Item;
import com.fdmgroup.soloprojectmichaltrajdos.model.User;
import com.fdmgroup.soloprojectmichaltrajdos.repository.ItemDao;
import com.fdmgroup.soloprojectmichaltrajdos.repository.ItemServiceRepository;

@Service
public class ItemService implements ItemServiceRepository<Item> {

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private UserService userService;

	@Override
	public Optional<Item> findById(Long item_id) {
		return itemDao.findById(item_id);
	}

	@Override
	public List<Item> findAll() {
		return itemDao.findAll();
	}

	@Override
	public Item save(Item item) {
		return itemDao.save(item);
	}

	public boolean checkStock(User user) {
		boolean nothingInStock = false;

		for (Item item : user.getUserBasketItems()) {
			if (item.getItemStock() < 1) {
				nothingInStock = true;
			}
		}
		return nothingInStock;
	}

	public void buyBasket(User user) {

		boolean nothingInStock = checkStock(user);

		if (nothingInStock == false) {
			for (Item item : user.getUserBasketItems()) {
				item.setItemStock(item.getItemStock() - 1);
				itemDao.save(item);
			}
			user.getUserBasketItems().clear();
			userService.save(user);
		}
}
}
