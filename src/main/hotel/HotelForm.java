import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class HotelForm {
    private Root root;
    private List<Hotel> hotels;

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


}
