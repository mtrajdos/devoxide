import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Room } from '../models/room';
import { Role } from '../models/role';
// import { timeStamp } from 'console';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  allRoles: Role[];
  editUserForm = new User();

  constructor(private userService: UserService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    const userUsername = this.route.snapshot.paramMap.get('username');
    this.userService.getUserByUsername(userUsername).subscribe(data => {
      this.editUserForm = data as User;
    });
    this.userService.getAllRoles().subscribe(data => {
      this.allRoles = data as Role[];
    });
  }

  roleComparer(o1: Role, o2: Role): boolean {
    return o1 && o2 ? o1.roleName === o2.roleName : o2 === o2;
  }

   editUser() {
    this.userService.editUserSubmit(this.editUserForm).subscribe(data => {
      this.router.navigate(['/user-list']);
    });
   }

}
