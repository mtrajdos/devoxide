package com.fdmgroup.soloprojectmichaltrajdos.repository;

import java.util.List;
import java.util.Optional;

public interface ItemServiceRepository<Item> {
	
	Optional<Item> findById(Long item_id);
	
	List<Item> findAll();
	
	Item save(Item item);
	
}
