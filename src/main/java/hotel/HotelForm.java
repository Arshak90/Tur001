package hotel;

import Core.Root;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class HotelForm implements Serializable {
    private Root root;
    private List<Hotel> hotels;
    private Hotel hotel =new Hotel();
    private List<HotelType> hotelTypes;

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public List<Hotel> getHotels() {
        if(this.hotels == null){
            this.hotels = getRoot().getHotelDao().getAll();
        }
         return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<HotelType> getHotelTypes() {
        if (this.hotelTypes == null){
            this.hotelTypes = getRoot().getHotelDao().getHotelTypes();
        }
        return hotelTypes;
    }

    public void setHotelTypes(List<HotelType> hotelTypes) {
        this.hotelTypes = hotelTypes;
    }

    public void saveHotel(){
//        if (getRoot().getHotelDao().insert(this.hotel));
//            this.hotel = new Hotel();
        System.out.println(this.hotel.getAddress());
        this.hotel = new Hotel();
    }
}
