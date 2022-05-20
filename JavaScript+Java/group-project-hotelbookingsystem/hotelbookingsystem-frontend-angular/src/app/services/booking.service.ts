import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import {User} from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private submitBooking = `http://localhost:8088/hotelbookingsystem/booking/BookingSubmit`;
  private customerDetails = `http://localhost:8088/hotelbookingsystem/customer/Details/`;

  constructor(private http: HttpClient) { }

  submitBookingRequest(bookingRequest): Observable<any>{
     return this.http.post(this.submitBooking, bookingRequest);
  }

  getCustomerDetails(username: string): Observable<any>{
     return this.http.get<User>(this.customerDetails + username);
  }

}
