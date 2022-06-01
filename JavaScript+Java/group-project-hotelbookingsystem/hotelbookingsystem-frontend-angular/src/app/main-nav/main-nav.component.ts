import { Component, Injector, OnInit } from '@angular/core';
import { BreakpointObserver} from '@angular/cdk/layout';
import { AuthenticationService} from '../services/authentication.service';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css']
})
export class MainNavComponent implements OnInit {

  auth: AuthenticationService;

  constructor(private breakpointObserver: BreakpointObserver,
              private injector: Injector) {}

  ngOnInit(){
    this.auth = this.injector.get(AuthenticationService);
  }
}
