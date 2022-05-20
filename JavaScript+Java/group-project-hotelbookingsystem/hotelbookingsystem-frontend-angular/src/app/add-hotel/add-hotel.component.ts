import { Component, OnInit } from '@angular/core';
import { HotelService } from '../services/hotel.service';
import { BookingService } from '../services/booking.service';

import { Router } from '@angular/router';
import { Room } from '../models/room';
import { Hotel } from '../models/hotel';
import { Bookings } from '../models/bookings';
import {AuthenticationService} from '../services/authentication.service';


@Component({
  selector: 'app-add-hotel',
  templateUrl: './add-hotel.component.html',
  styleUrls: ['./add-hotel.component.css']
})
export class AddHotelComponent implements OnInit {

  rooms: Room[];
  addHotelForm = new Hotel();
  currentBooking = new Bookings();

  constructor(private hotelService: HotelService, private bookingService: BookingService, 
              private authenticationService: AuthenticationService, private router: Router) { }


  ngOnInit(): void {
    this.hotelService.getAllRooms().subscribe(data => {
      this.rooms = data as Room[];
    });
    const username = this.authenticationService.user.username;
    this.bookingService.getCustomerDetails(username).subscribe(data => {
      if(data){
        this.currentBooking=data.booking;
      }
    })
  }

  addHotel() {
    this.addHotelForm.numOfRooms = this.addHotelForm.room.length;
    this.hotelService.addHotelSubmit(this.addHotelForm).subscribe( data => {
      if (data){
        this.router.navigate(['/account']);
      }
    });
  }
}
