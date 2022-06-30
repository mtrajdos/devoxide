package com.fdmgroup.hotelbookingsystem.controller;

import com.fdmgroup.hotelbookingsystem.model.Hotel;
import com.fdmgroup.hotelbookingsystem.model.HotelOwner;
import com.fdmgroup.hotelbookingsystem.model.Room;
import com.fdmgroup.hotelbookingsystem.services.HotelOwnerService;
import com.fdmgroup.hotelbookingsystem.services.HotelService;
import com.fdmgroup.hotelbookingsystem.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/hotelOwner")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class HotelOwnerController {

	@Autowired
	HotelOwnerService hotelOwnerService;

	@Autowired
	HotelService hotelService;

	@Autowired
	RoomService roomService;



	@PostMapping("/AddHotelSubmit")
	public ResponseEntity<HttpStatus> addHotelSubmit(@RequestBody Hotel hotel, Principal principal) {
		Optional<Hotel> hotelFromDB = hotelService.findById(hotel.getHotelId());
		Optional<HotelOwner> optionalHotelOwner = hotelOwnerService.findByUsername(principal.getName());
		if (optionalHotelOwner.isEmpty()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		HotelOwner hotelOwner = optionalHotelOwner.get();
		if (hotelFromDB.isPresent()) {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
		if (hotel.isAirportTransfers() != true) {
			hotel.setTransferPrice(0);
		}
		try {
			hotelOwner.getHotels().add(hotel);
			hotelService.save(hotel);
			hotelOwnerService.save(hotelOwner);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return ResponseEntity.ok(HttpStatus.CREATED);
	}

	@PutMapping("/EditHotelSubmit")
	public ResponseEntity<HttpStatus> editHotelSubmit(@RequestBody Hotel hotel) {
		Optional<Hotel> hotelFromDB = hotelService.findById(hotel.getHotelId());
		if(hotelFromDB.isPresent()){
			hotelService.save(hotel);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}


	@PostMapping("/AddNewRoomTypeSubmit")
	public ResponseEntity<HttpStatus> newRoomTypeSubmit(@RequestBody Room room) {
		Optional<Room> roomFromDB = roomService.findByRoomTypeAndPrice(room.getRoomType(), room.getPrice());
		if (roomFromDB.isPresent()) {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
		try {
			roomService.save(room);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return ResponseEntity.ok(HttpStatus.CREATED);
	}
}
