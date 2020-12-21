import { Component, OnInit } from '@angular/core';
import {BookingService} from '../services/booking.service';
import {Bookings} from '../models/bookings';
import {AuthenticationService} from '../services/authentication.service';

@Component({
  selector: 'app-view-bookings',
  templateUrl: './view-bookings.component.html',
  styleUrls: ['./view-bookings.component.css']
})
export class ViewBookingsComponent implements OnInit {

  bookings: Bookings[] = [];

  constructor(private bookingService: BookingService, private authenticationService: AuthenticationService) {}

  ngOnInit(): void {
    const username = this.authenticationService.user.username;
    console.log(this.authenticationService.user);
    this.bookingService.getCustomerDetails(username).subscribe( data => {
      if (data){
        this.bookings = data.bookings;
      }
    });
  }

}
