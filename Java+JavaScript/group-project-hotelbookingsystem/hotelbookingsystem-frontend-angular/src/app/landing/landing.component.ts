import {Component, OnInit} from '@angular/core';
import {Hotel} from 'src/app/models/hotel';
import {HotelService} from '../services/hotel.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  hotels: Hotel[] = [];
  foundHotels: Hotel[] = [];
  searchQuery = '';

  constructor(private hotelService: HotelService) {}

  ngOnInit(): void {
    this.hotelService.getAllHotels(0, 10).subscribe(allHotelsResult => {
      this.hotels = allHotelsResult.content as Hotel[];
    });
  }

  searchHotels() {
    const search = this.searchQuery.toLowerCase();
    this.foundHotels = this.hotels.filter(hotel => {
      return hotel.city.toLowerCase().includes(search) || hotel.hotelName.toLowerCase().includes(search);
    });
    // TODO: Can probably do the second filter inside the first too.
    if (this.foundHotels.length < 1){
      this.foundHotels = this.hotels.filter(hotel => {
        for (const room of hotel.room){
          if (room.roomType.toLowerCase().includes(search)){
            return hotel;
          }
        }
      });
    }
  }
}
