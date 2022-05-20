import { Component, OnInit, Injectable } from '@angular/core';
import { Hotel } from '../models/hotel';
import { StateService } from '../services/state.service';
import {ActivatedRoute, Router} from '@angular/router';
import { BookingRequest } from '../models/booking-request';
import { BookingService } from '../services/booking.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {HotelService} from '../services/hotel.service';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})

@Injectable()
export class BookingsComponent implements OnInit {

  bookingRequest = new BookingRequest();

  startDate = new Date();
  endDate = new Date();
  hotel: Hotel = new Hotel();
  checked = false;
  submitted = false;
  response: any;
  roomTypeSelected: string;
  minDate: Date;
  minEndDate: Date;
  start: string;
  end: string;

  constructor(private bookingService: BookingService,
              private hotelService: HotelService,
              private state: StateService,
              private router: Router,
              private snackBar: MatSnackBar,
              private route: ActivatedRoute) {

    const today = new Date();
    const tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);
    tomorrow.setHours(0, 0, 0, 0);
    this.minDate = tomorrow;
    this.minEndDate = tomorrow;
    console.log(this.minDate);
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.hotelService.getHotelById(id).subscribe( data => {
      if (data){
        this.hotel = data;
      }
    });
  }

  onChange(){
    console.log('change?');
  }
  inputStartDate(event){
    this.startDate = event.target.value;

  }
  changeStartDate(event){
    this.startDate = event.target.value;
    this.minEndDate.setDate(this.startDate.getDate() + 1);
  }

  inputEndDate(event){
    this.endDate = event.target.value;
  }
  changeEndDate(event){
    this.endDate = event.target.value;
  }

  createAndSubmitBooking(startDate, endDate) {

    const splitStartDate = this.startDate.toString().split(' ');
    const splitEndDate = this.endDate.toString().split(' ');

    this.bookingRequest.hotel = this.hotel.hotelName;
    this.bookingRequest.checkInDate = this.dateFormatter(splitStartDate[1], splitStartDate[2], splitStartDate[3]).toString();
    this.bookingRequest.checkOutDate = this.dateFormatter(splitEndDate[1], splitEndDate[2], splitEndDate[3]).toString();
    this.bookingRequest.roomType = this.roomTypeSelected;
    if (this.checked){
      this.bookingRequest.extras = 'AIRPORTTRANSFER';
    } else {
      this.bookingRequest.extras = 'NO_EXTRAS';
    }
    if ((!this.start || this.start === '') || (!this.end || this.end === '') || (!this.roomTypeSelected || this.roomTypeSelected === '') ){
      this.snackBar.open('Incomplete form', 'OK', {
        duration: 3000,
      });
    } else {
      this.submitted = true;
      this.bookingService.submitBookingRequest(this.bookingRequest).subscribe( data => {
        if (data){
          this.router.navigate(['/view-bookings']);
        }
      });
    }
  }

  dateFormatter(month, day, year){
    const dict = new Map<string, string>();
    dict['Jan'] = '01';
    dict['Feb'] = '02';
    dict['Mar'] = '03';
    dict['Apr'] = '04';
    dict['May'] = '05';
    dict['Jun'] = '06';
    dict['Jul'] = '07';
    dict['Aug'] = '08';
    dict['Sep'] = '09';
    dict['Oct'] = '10';
    dict['Nov'] = '11';
    dict['Dec'] = '12';

    return (year + '-' + dict[month] + '-' + day);
  }


}
