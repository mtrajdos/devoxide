import {Role} from './role';

export class User {

  userId: number;
  firstName: string;
  lastName: string;
  username: string;
  address: string;
  roles: Role[];
  token: string;
  password: string;

  constructor() {
    this.userId = 0;
    this.firstName = '';
    this.lastName = '';
    this.username = '';
    this.address = '';
    this.roles = [];
    this.token = '';
    this.password = '';
  }
}
