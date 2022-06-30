export class HotelForm {

  public hotelName: string;
  public address: string;
  public postcode: string;
  public city: string;
  public amenities: string;
  public starRating: number;
  public airportTransfers: boolean;
  public room: string[];

  constructor() {
    this.hotelName = '';
    this.address = '';
    this.postcode = '';
    this.city = '';
    this.amenities = '';
    this.starRating = 0;
    this.airportTransfers = false;
    this.room = [];
  }
}
