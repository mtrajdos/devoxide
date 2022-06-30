import {Component, OnInit} from '@angular/core';
import {Bookings} from '../models/bookings';
import {BookingService} from '../services/booking.service';

import {ReviewService} from '../services/review.service';
import {HotelService} from '../services/hotel.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Review} from '../models/review';
import {Hotel} from '../models/hotel';
import {AuthenticationService} from '../services/authentication.service';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {

  addReviewForm = new Review();
  currentBooking = new Bookings();
  hotel: Hotel;

  constructor(private hotelService: HotelService, private authenticationService: AuthenticationService,
              private router: Router, private route: ActivatedRoute,
              private bookingService: BookingService, private reviewService: ReviewService) {
  }

  ngOnInit(): void {
    const bookingId = this.route.snapshot.paramMap.get('bookingId');
    const hotel = this.route.snapshot.paramMap.get('hotel');

    this.reviewService.getBookingById(bookingId).subscribe(data => {
      this.currentBooking = data as Bookings;
      console.log(this.currentBooking);
    });
    this.hotelService.getHotelByName(hotel).subscribe(data => {
      this.hotel = data;
      console.log(this.hotel);
    });
  }

  addReview() {
    this.addReviewForm.hotel = this.hotel;
    this.addReviewForm.customer = this.authenticationService.user;
    console.log(this.addReviewForm);
    this.reviewService.submitReviewRequest(this.addReviewForm, this.hotel.hotelId).subscribe(data => {
      if (data) {
        this.router.navigate(['/review-list/' + this.hotel.hotelId]);
      }
    });
  }

}
