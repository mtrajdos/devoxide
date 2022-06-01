import { Component, OnInit } from '@angular/core';
import { RegistrationForm } from '../models/registration-form';
import { NgForm } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registrationForm = new RegistrationForm('', '', '', '', '', '');
  userSubmission;

  constructor(private auth: AuthenticationService) { }


  ngOnInit(): void {

  }

  onSubmit(userForm: NgForm){

    console.log(this.registrationForm);
    console.log(JSON.stringify(userForm.value));
    this.userSubmission = new RegistrationForm(userForm.value.username, userForm.value.firstName, userForm.value.lastName, userForm.value.address, userForm.value.email,
       userForm.value.password);
    console.log(this.userSubmission);


    // the below only works on my (simon's) setup using a node server to act as a fake
    // back end

    this.auth.register_new_user(this.userSubmission);

  }

}
