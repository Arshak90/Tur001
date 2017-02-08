package hotel;

import Core.Root;
import Core.Util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class HotelForm implements Serializable {
    private Root root;
    private List<Hotel> hotels;
    private Hotel hotel;
    private List<HotelType> hotelTypes;
    private Integer hotelTypeIdForFind = 1;
    private List<Hotel> hotelsWithType;

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

    public Integer getHotelTypeIdForFind() {
        return hotelTypeIdForFind;
    }

    public void setHotelTypeIdForFind(Integer hotelTypeIdForFind) {
        this.hotelTypeIdForFind = hotelTypeIdForFind;
    }

    public List<Hotel> getHotelsWithType() {
        if (this.hotelsWithType == null){
            this.hotelsWithType = getRoot().getHotelDao().getHotelsByTypeId(this.hotelTypeIdForFind);
        }
        return hotelsWithType;
    }

    public void setHotelsWithType(List<Hotel> hotelsWithType) {
        this.hotelsWithType = hotelsWithType;
    }

    public void setHotelTypes(List<HotelType> hotelTypes) {
        this.hotelTypes = hotelTypes;
    }

    public void saveHotel(){
        if (this.hotel.getId()!= null){
            if (getRoot().getHotelDao().update(this.hotel)){
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Փոփոխությունը Հաջողությամբ պահպանվել է");
                facesContext.addMessage(null, facesMessage);
            }
        }else {
            if (getRoot().getHotelDao().insert(this.hotel)){
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("Հաջողությամբ պահպանվել է");
                for (Hotel hotel: root.getHotelDao().getAll()){
                    if (hotel.getName().equals(this.hotel.getName())){
                        this.setHotel(hotel);
                        Util.getBean("hotel", Hotel.class).setId(hotel.getId());
                    }
                }
                facesContext.addMessage(null, facesMessage);
            }
        }

        this.hotelsWithType = null;
    }

    public void newHotel(){
        this.hotel = new Hotel();
    }

    public void changeHotelType(){
        this.hotelsWithType = getRoot().getHotelDao().getHotelsByTypeId(this.hotelTypeIdForFind);
//        this.hotelsWithType.clear();
//        this.hotelsWithType.addAll(getRoot().getHotelDao().getHotelsByTypeId(this.hotelTypeIdForFind));
    }

    public void editHotel(Integer id){
        this.setHotel(getRoot().getHotelDao().getById(id));
        Util.getBean("hotel", Hotel.class).setId(hotel.getId());
    }

    public void deleteHotel(Integer id){
        System.out.println(id);
    }
}
