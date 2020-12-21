import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import {Role} from '../models/role';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private allUsers = `http://localhost:8088/hotelbookingsystem/admin/AllUsers`;
  private UserByUsername = `http://localhost:8088/hotelbookingsystem/admin/SeeHotelOwner/`;
  private editUser = `http://localhost:8088/hotelbookingsystem/admin/EditUser`;
  private allRoles = `http://localhost:8088/hotelbookingsystem/admin/AllRoles`;

  constructor(private http: HttpClient) { }

  getAllUsers(page: number, size: number): Observable<any>{
    return this.http.get(this.allUsers + `?page=${page}&size=${size}`);
  }

  getUserByUsername(username: string): Observable<any>{
    return this.http.get(this.UserByUsername + username);
  }

  editUserSubmit(userForm: User): Observable<any> {
    return this.http.patch(this.editUser, userForm);
  }

  getAllRoles(): Observable<any>{
    return this.http.get(this.allRoles);
  }

}

