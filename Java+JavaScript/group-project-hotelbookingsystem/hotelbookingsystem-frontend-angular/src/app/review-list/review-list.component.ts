import { Component, OnInit } from '@angular/core';
import { Review } from '../models/review';
import { HotelService } from '../services/hotel.service';
import { Router, ActivatedRoute } from '@angular/router';
import {Hotel} from '../models/hotel';

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css']
})
export class ReviewListComponent implements OnInit {

  reviews: Review[];
  hotel: Hotel;

  constructor(private hotelService: HotelService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.hotelService.getHotelById(id).subscribe(data => {
      this.hotel = data as Hotel;
    });
    this.hotelService.getAllReviews(id).subscribe(data => {
      this.reviews = data as Review[];
    });
  }

}
