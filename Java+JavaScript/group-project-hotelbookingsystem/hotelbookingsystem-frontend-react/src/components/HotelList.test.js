import React from 'react';
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";
import { shallow } from 'enzyme';
import HotelList from './HotelList';
import Hotels from './Hotels';

let container = null;
const mockHotel = {
    hotelId: 1,
    hotelName: "Yotel",
    numOfRooms: 5,
    address: "123 Fake street",
    postcode: "123 ABC",
    city: "Edinburgh",
    amenities: "none",
    starRating: 5,
};
const mockHotel = {
  hotelId: 1,
  hotelName: "Yotel",
  numOfRooms: 5,
  address: "123 Fake street",
  postcode: "123 ABC",
  city: "Edinburgh",
  amenities: "none",
  starRating: 5,
};
beforeEach(() => {
  // setup a DOM element as a render target
  container = document.createElement("div");
  document.body.appendChild(container);
});

afterEach(() => {
    // cleanup on exiting
    unmountComponentAtNode(container);
    container.remove();
    container = null;
  });

  it("renders with a mock hotel", () => {
    act(() => {
      render(<HotelList hotel={mockHotel}/>, container);
    });
    expect(container.textContent).toBe("Yotel Edinburgh 123 Fake street 123 ABC Rooms: 5 Amenities: none Star rating: 5 Visit this lovely hotel");
  });

describe('HotelList component', () => {
    describe('when provided with an empty array of hotels', () => {
        it('contains an empty <div> element', () => {
          render(<HotelList hotel={}/>, container);
            expect(container.textContent).toBe("    Rooms:  Amenities:  Star rating:  Visit this lovely hotel");
        })
        it('p elements have no value', () => {
            const hotelList = shallow(<HotelList hotel={mockHotel}/>);
            expect(hotelList.find('p')).toEqual({});
        })
    })
    describe('when provided with an array of hotels', () => {
        it('contains a matching number of <div> elements', () => {
            const hotelList = shallow(<HotelList hotel={mockHotel}/>);
            hotelList.update();
            expect(hotelList.find('div').length).toEqual(4);
        })
    })
})
