package com.fdmgroup.soloprojectmichaltrajdos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.soloprojectmichaltrajdos.model.Item;

public interface ItemDao extends JpaRepository<Item, Long>{

}
