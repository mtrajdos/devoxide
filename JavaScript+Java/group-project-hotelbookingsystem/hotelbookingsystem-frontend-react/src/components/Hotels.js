import React, { Component } from 'react';
import axios from 'axios';
import ReactPaginate from 'react-paginate';
import HotelList from './HotelList';

export class Hotels extends Component {


    constructor(props) {
        super(props);
        this.state = {
            offset: 0,
            perPage: 5,
            currentPage: 0,
            error: false,
            message: ''
        };
        this.handlePageClick = this.handlePageClick.bind(this);
        this.recievedData = this.recievedData.bind(this);
    }

    recievedData() {
      const url = "http://localhost:8088/hotelbookingsystem/admin/AllHotels";
    
        axios.get(url)
        .then(response => {

          const hotels = response.data;
          const slice = hotels.slice(this.state.offset, this.state.offset + this.state.perPage)
          const postData = slice.map((hotel) => <React.Fragment key={hotel.hotelId}>
            <HotelList hotel={hotel} /></React.Fragment>)

          this.setState({
            pageCount: Math.ceil(hotels.length / this.state.perPage),

            postData
          })

          if (localStorage.getItem('username')) {
            this.setState({message: 'Hello ' + localStorage.getItem('username')})
          }

        });
        
      }
      handlePageClick = (e) =>{
        const selectedPage = e.selected;
        const offset = selectedPage * this.state.perPage;

        this.setState({
          currentPage: selectedPage,
          offset: offset
        }, () => {
          this.recievedData()
        });
      };

      changePagination = (e) =>{
        const size = e.selected;

        this.setState({
          perPage: size
        });
      };
    

    componentDidMount() {
      this.recievedData()
    }
        

    render() {
        return (
          <div className="row">
            <h4>{this.state.message}</h4>
           {this.state.postData}
           <ReactPaginate
              previousLabel={"prev"}
                    nextLabel={"next"}
                    breakLabel={"..."}
                    breakClassName={"break-me"}
                    pageCount={this.state.pageCount}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={5}
                    onPageChange={this.handlePageClick}
                    containerClassName={"pagination"}
                    subContainerClassName={"pages pagination"}
                    activeClassName={"active"}/>
          </div>
        );
      }
}

export default Hotels;
