import { Component, OnInit } from '@angular/core';
import { Room } from '../models/room';
import { Router } from '@angular/router';
import { HotelService } from '../services/hotel.service';

@Component({
  selector: 'app-add-room',
  templateUrl: './add-room.component.html',
  styleUrls: ['./add-room.component.css']
})
export class AddRoomComponent implements OnInit {

  addRoomForm = new Room();

  constructor(private router: Router,
    private hotelService: HotelService) { }

  ngOnInit(): void {
  }

  addRoom() {
    this.hotelService.addRoomSubmit(this.addRoomForm).subscribe(data => {
      if(data) {
        this.router.navigate(['/hotel-list']);
      }
    })
  }

}
