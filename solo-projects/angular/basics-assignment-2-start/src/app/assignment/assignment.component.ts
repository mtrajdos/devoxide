import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-assignment',
  templateUrl: './assignment.component.html',
  styles: ['']
})
export class AssignmentComponent implements OnInit {

  usernameEmpty = true;
  username = '';

  constructor() { 

  }

  ngOnInit(): void {
  }

  usernameContentCheck (event: any) {
    if (this.username) {
      this.usernameEmpty = false;
    } else {
      this.usernameEmpty = true;
    }
  }

  clearUsername() {
    this.username = '';
    this.usernameEmpty = true;
  }

}
