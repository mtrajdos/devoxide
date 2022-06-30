import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { LandingComponent } from './landing/landing.component';
import { HotelListComponent } from './hotel-list/hotel-list.component';
import { HotelItemComponent } from './hotel-list/hotel-item/hotel-item.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MainNavComponent } from './main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AuthGuard } from './services/auth.guard';
import { BookingsComponent } from './bookings/bookings.component';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { AuthenticationService } from './services/authentication.service';
import { HotelSingleComponent } from './hotel-single/hotel-single.component';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { ViewBookingsComponent } from './view-bookings/view-bookings.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { AccountComponent } from './account/account.component';
import { AddHotelComponent } from './add-hotel/add-hotel.component';
import { EditHotelComponent } from './edit-hotel/edit-hotel.component';
import { AddRoomComponent } from './add-room/add-room.component';
import {MatChipsModule} from '@angular/material/chips';
import { EditUserComponent } from './edit-user/edit-user.component';
import { UserListComponent } from './user-list/user-list.component';
import { ReviewListComponent } from './review-list/review-list.component';
import { AddReviewComponent } from './add-review/add-review.component';
import {MatPaginatorModule} from '@angular/material/paginator';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    LandingComponent,
    HotelListComponent,
    HotelItemComponent,
    MainNavComponent,
    BookingsComponent,
    HotelSingleComponent,
    ViewBookingsComponent,
    AccountComponent,
    AddHotelComponent,
    EditHotelComponent,
    AddRoomComponent,
    EditUserComponent,
    UserListComponent,
    ReviewListComponent,
    AddReviewComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        BrowserAnimationsModule,
        LayoutModule,
        MatToolbarModule,
        MatButtonModule,
        MatSidenavModule,
        MatIconModule,
        MatListModule,
        MatProgressSpinnerModule,
        MatNativeDateModule,
        MatDatepickerModule,
        MatFormFieldModule,
        MatSelectModule,
        MatRadioModule,
        MatCheckboxModule,
        MatSnackBarModule,
        MatInputModule,
        MatChipsModule,
        MatPaginatorModule
    ],
  providers: [AuthGuard, AuthenticationService,
  {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  },
  {provide: MAT_DATE_LOCALE, useValue: 'en-GB'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
