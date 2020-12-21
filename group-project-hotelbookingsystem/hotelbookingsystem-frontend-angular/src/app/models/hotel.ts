import { Bookings } from './bookings';
import { Room } from './room';

export class Hotel {
    hotelId: number;
    hotelName: string;
    numOfRooms: number;
    address: string;
    postcode: string;
    city: string;
    amenities: string;
    bookings: Bookings[];
    starRating: number;
    room: Room[];
    airportTransfers: boolean;
    transferPrice: number;
    verified: boolean;

}

