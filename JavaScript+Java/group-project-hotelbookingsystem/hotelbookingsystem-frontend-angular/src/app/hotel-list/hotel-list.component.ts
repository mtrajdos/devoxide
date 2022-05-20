import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Hotel} from '../models/hotel';
import {HotelService} from '../services/hotel.service';
import {MatPaginator} from '@angular/material/paginator';
import {tap} from 'rxjs/operators';



@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.css']
})
export class HotelListComponent implements AfterViewInit {

  hotels: Hotel[];
  resultsLength = 0;

  constructor(private http: HttpClient, private hotelService: HotelService) {}

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngAfterViewInit(){
    this.paginator.page
      .pipe(
        tap(() => this.updateHotelList())
      ).subscribe();
    this.updateHotelList();
  }

  updateHotelList(){
    this.hotelService.getAllHotels(this.paginator.pageIndex, this.paginator.pageSize).subscribe(data => {
      this.hotels = data.content as Hotel[];
      this.resultsLength = data.totalElements;
    });
  }

}
