import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-warning-alert',
  templateUrl: './warning-alert.component.html',
  styles: [`
  p { padding: 10px;
      color: darkred;
      font-weight: bold;
      text-shadow: 1px 0 0 red, 0 -1px 0 red, 0 1px 0 red, -1px 0 0 red;
      background-color: coral;}
  `]
})
export class WarningAlertComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
