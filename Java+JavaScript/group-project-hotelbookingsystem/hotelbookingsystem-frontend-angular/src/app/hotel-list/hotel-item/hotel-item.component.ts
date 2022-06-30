import { Component, OnInit, Input } from '@angular/core';
import { Hotel } from 'src/app/models/hotel';
import {AuthenticationService} from '../../services/authentication.service';

@Component({
  selector: 'app-hotel-item',
  templateUrl: './hotel-item.component.html',
  styleUrls: ['./hotel-item.component.css']
})

export class HotelItemComponent implements OnInit {

  @Input() hotel: Hotel;

  constructor(public authenticationService: AuthenticationService) {}

  ngOnInit(): void {
  }
}
