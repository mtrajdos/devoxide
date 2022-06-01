package com.fdmgroup.hotelbookingsystem.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.fdmgroup.hotelbookingsystem.model.Customer;
import com.fdmgroup.hotelbookingsystem.model.HotelOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fdmgroup.hotelbookingsystem.model.Role;
import com.fdmgroup.hotelbookingsystem.model.User;
import com.fdmgroup.hotelbookingsystem.repository.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	CustomerService customerService;

	@Autowired
	HotelOwnerService hotelOwnerService;

	public Page<User> findAll(int page, int size) {
		Pageable pageRequest = PageRequest.of(page, size);
		return userDao.findAll(pageRequest);
	}

	public User save(User user) {
		return userDao.save(user);
	}

	public Optional<User> findById(long userId){
		return userDao.findById(userId);
	}

	public Optional<User> findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public List<User> findAllHotelOwners(int page, int size){
		Page<User> users = findAll(page, size);
		List<User> owners = new ArrayList<>();
		for (User user : users){
			if (user.getRoles().equals("HOTELOWNER")){
				owners.add(user);
			}
		}
		return owners;
	}

	public User updateUser(long userId, String username, String firstName, String lastName, List<Role> role) throws NoSuchElementException {
		User user = userDao.findById(userId).get();
		if (username != null){
			user.setUsername(username);
		}
		if (firstName != null){
			user.setFirstName(firstName);
		}
		if (lastName != null) {
			user.setLastName(lastName);
		}
		return userDao.save(user);
	}

	public User updateRole(long userId, List<Role> role) throws NoSuchElementException {
		User user = userDao.findById(userId).get();
		user.setRoles(role);
		if (role.equals("ROLE_CUSTOMER")){
			Customer customer = new Customer();
			customerService.save(customer);
		}
		if (role.equals("ROLE_HOTELOWNER")){
			HotelOwner hotelOwner = new HotelOwner();
			hotelOwnerService.save(hotelOwner);
		}
		return userDao.save(user);
	}

}
