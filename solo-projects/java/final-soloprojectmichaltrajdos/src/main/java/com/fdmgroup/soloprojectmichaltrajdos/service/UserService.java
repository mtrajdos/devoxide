package com.fdmgroup.soloprojectmichaltrajdos.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.soloprojectmichaltrajdos.model.Item;
import com.fdmgroup.soloprojectmichaltrajdos.model.User;
import com.fdmgroup.soloprojectmichaltrajdos.repository.UserDao;
import com.fdmgroup.soloprojectmichaltrajdos.repository.UserServiceRepository;

@Service
public class UserService implements UserServiceRepository<User> {

	@Autowired
	private UserDao userDao;

	@Override
	public Optional<User> findById(Long user_id) {
		return userDao.findById(user_id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public Optional<User> findByFirstnameAndLastname(String firstname, String lastname) {
		return userDao.findByFirstnameAndLastname(firstname, lastname);
	}

	@Override
	public Optional<User> findByFirstname(String firstname) {
		return userDao.findByFirstname(firstname);
	}

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	
	public void updateTotalBasketPrice(User user) {
		BigDecimal calculatedTotalPrice = new BigDecimal("0.00");
		
		if (user.getUserBasketItems().size() > 0) {
			
			for (Item item : user.getUserBasketItems()) {
				calculatedTotalPrice = calculatedTotalPrice.add(item.getItemPrice());
			}
			
			user.setTotalBasketPrice(calculatedTotalPrice);
			userDao.save(user);
			
		} else {
			user.setTotalBasketPrice(calculatedTotalPrice);
			userDao.save(user);
		}

}
}