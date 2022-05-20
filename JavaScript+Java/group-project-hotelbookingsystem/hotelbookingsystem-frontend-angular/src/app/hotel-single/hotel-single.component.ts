import { Component, OnInit} from '@angular/core';
import { Hotel } from 'src/app/models/hotel';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';
import {HotelService} from '../services/hotel.service';

@Component({
  selector: 'app-hotel-single',
  templateUrl: './hotel-single.component.html',
  styleUrls: ['./hotel-single.component.css']
})
export class HotelSingleComponent implements OnInit {

  hotel: Hotel = new Hotel();

  constructor(private router: Router, private route: ActivatedRoute,
              private hotelService: HotelService,
              public authenticationService: AuthenticationService) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.hotelService.getHotelById(id).subscribe( data => {
      if (data){
        this.hotel = data;
      }
    });
  }
}
