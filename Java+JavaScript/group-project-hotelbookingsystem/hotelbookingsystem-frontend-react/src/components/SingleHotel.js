import React, { Component } from 'react'
 
class SingleHotel extends Component {
    constructor(props){
        super(props)
        this.state ={
            hotel: ''
        }
    }
 
    componentDidMount() {
        this.props.hotel.then((value) => {
            this.setState({hotel: value.data});
        });
    }
 
    render(){
        return(
            <div>
                <h3>Hotel Name: {this.state.hotel.hotelName}</h3>
                <p>City: {this.state.hotel.city}</p>
                <p>Address: {this.state.hotel.address}</p>
                <p>Postcode: {this.state.hotel.postcode}</p>
                <p>Rooms: {this.state.hotel.numOfRooms}</p>
                <p>Amenities: {this.state.hotel.amenities}</p>
                <p>Star rating: {this.state.hotel.starRating}</p>
                <a href="/hotels">Back to list</a>
            </div>
        )
    }
 
}
 
export default SingleHotel;