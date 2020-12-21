import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router, ActivatedRoute} from '@angular/router';
import {Login} from '../models/login';
import {RegistrationForm} from '../models/registration-form';
import {User} from '../models/user';
import {Role} from '../models/role';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  loginUrl = 'http://localhost:8088/hotelbookingsystem/login/LoginUser';
  registrationUrl = 'http://localhost:8088/hotelbookingsystem/login/RegisterUser/';
  userDetailsUrl = `http://localhost:8088/hotelbookingsystem/login/Details/`;
  return = '';
  user: User;
  userFromStorage: User;
  roles: Role[] = [];

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => this.return = params.return || '');
    this.userFromStorage = JSON.parse(localStorage.getItem('user'));
    this.roles = JSON.parse(localStorage.getItem('roles'));
    if (!this.roles){
      this.roles = [];
    }
    if (this.userFromStorage) {
      this.user = this.userFromStorage;
    } else {
      this.user = new User();
    }
  }

  hasRole(role: string): boolean{
    role = 'ROLE_' + role.toUpperCase();
    for (const storedRole of this.roles){
      if (role === storedRole.roleName){
        return true;
      }
    }
    return false;
  }

  logon(login: Login) {
    return this.http.post(this.loginUrl, login, { responseType: 'text' });
  }

  setUserDetails(username: string){
    return this.http.get<User>(this.userDetailsUrl + username).subscribe( data => {
      console.log(data);
      localStorage.setItem('username', data.username);
      localStorage.setItem('user', JSON.stringify(data));
      this.user = data;
      for (const role of data.roles){
        this.roles.push(role);
      }
      localStorage.setItem('roles', JSON.stringify(this.roles));
    });
  }

  register_new_user(registrationForm: RegistrationForm) {
    return this.http.post<any>(this.registrationUrl, registrationForm)
      .subscribe(data => {
          if (data) {
            this.router.navigate(['/login']).catch(error => console.error(error));
          }
        },
        error => console.log(error));
  }

  loggedOn() {
    return !!localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('user');
    localStorage.removeItem('roles');
    this.user = new User();
    this.roles = [];
    this.router.navigate(['/']).catch(
      error => console.error(error)
    );
  }

  getToken() {
    return localStorage.getItem('token');
  }


}
