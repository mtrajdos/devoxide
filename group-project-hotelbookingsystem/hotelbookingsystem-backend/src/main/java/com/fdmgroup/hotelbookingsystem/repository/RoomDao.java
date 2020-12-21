package com.fdmgroup.hotelbookingsystem.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.hotelbookingsystem.model.Room;

public interface RoomDao extends JpaRepository<Room, Long> {

	List<Room> findByRoomType(String roomType);

	List<Room> findByPrice(BigDecimal price);

	Optional<Room> findByRoomId(Long roomId);

	Optional<Room> findByRoomTypeAndPrice(String roomType, BigDecimal price);

}
