import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })

  export class ReviewService {

    private submitReview = `http://localhost:8088/hotelbookingsystem/reviews/createReview`;
    private bookingByID = 'http://localhost:8088/hotelbookingsystem/booking/BookingConfirmation/';

    constructor(private http: HttpClient) { }

    submitReviewRequest(reviewRequest, hotelId: number): Observable<any>{
       return this.http.post(this.submitReview + '?hotelId=' + hotelId, reviewRequest);
    }

    getBookingById(id: string): Observable<any>{
      return this.http.get(this.bookingByID + id);
    }
  }

