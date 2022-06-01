import { Component, OnInit} from '@angular/core';

import {AuthenticationService} from '../services/authentication.service';

import {HotelService} from '../services/hotel.service';
import { Hotel } from '../models/hotel';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  hotels: Hotel[];
  hotel: Hotel;

  constructor(public authenticationService: AuthenticationService, private hotelService: HotelService) { }

  ngOnInit(): void {

    if (this.authenticationService.hasRole('HOTELOWNER')){
      const hotelOwnerUsername = this.authenticationService.user.username;
      this.hotelService.getHotelOwnerDetails(hotelOwnerUsername).subscribe ( data => {
      if (data){
        this.hotels = data.hotels;
        console.log(this.hotels);
      }
      });

    }
}
}

