import React, { Component } from 'react';
import {
  BrowserRouter as Router,
  Route,
} from 'react-router-dom';
import Hotels from './Hotels';
import Login from './Login';
import NavBar from './NavBar';
import axios from 'axios';
import SingleHotel from './SingleHotel';
import Account from './Account';
import Landing from './Landing';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      hotels: [],
    };

    this.findHotelById = this.findHotelById.bind(this)
  }

  findHotelById(hotelId){
    const url = "http://localhost:8088/hotelbookingsystem/hotel/SeeHotelById/" + hotelId
    return axios.get(url)
  }


  render() {
    return (
      <div className="container">
        <div className="nav-wrapper indigo lighten-4">
              <h1>Hotel Booking System</h1>
        </div>
        <Router>
        <NavBar />
        <div className="row">
          <div className="col s8">
            <Route exact path="/login" component={Login} />
            <Route exact path="/hotels" component={Hotels} />
            <Route exact path="/hotels/hotel/:hotelId" render={(props) =>{
                const hotel = this.findHotelById(props.match.params.hotelId)
              return <SingleHotel hotel={hotel}/>}}/>
            <Route exact path="/account" component={Account} />
            <Route exact path="/" component={Landing} />
          </div>
        </div>
        </Router>
      </div>
    );
  }
}

export default App;
