import { Component, OnInit } from '@angular/core';
import { HotelService } from '../services/hotel.service';
import {ActivatedRoute, Router} from '@angular/router';
import { Room } from '../models/room';
import { Hotel } from '../models/hotel';

@Component({
  selector: 'app-edit-hotel',
  templateUrl: './edit-hotel.component.html',
  styleUrls: ['./edit-hotel.component.css']

})
export class EditHotelComponent implements OnInit {

  rooms: Room[];
  currentHotel = new Hotel();
  editHotelForm = new Hotel();

  constructor(private hotelService: HotelService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    const hotelId = this.route.snapshot.paramMap.get('id');
    this.hotelService.getAllRooms().subscribe(data => {
      this.rooms = data as Room[];
    });
    this.hotelService.getHotelById(hotelId).subscribe(data => {
      this.editHotelForm = data as Hotel;
      this.currentHotel = data as Hotel;
      for (const room of this.rooms){
         if (!this.editHotelForm.room){
          this.editHotelForm.room.push(room);
         }
       }
      console.log('HOTELFORM: ' + this.editHotelForm.room);
    });
  }

  roomComparer(o1: Room, o2: Room): boolean {
    return o1 && o2 ? o1.roomType === o2.roomType : o2 === o2;
  }

  editHotel() {
    this.editHotelForm.numOfRooms = this.editHotelForm.room.length;
    this.hotelService.editHotelSubmit(this.editHotelForm).subscribe(data => {
      this.router.navigate(['/account']);
    });
  }
}
