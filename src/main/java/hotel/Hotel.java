package hotel;

/**
 * Created by ArtStyle on 23.01.2017.
 */
public class Hotel {

    private Integer id;
    private String name;
    private Integer hotelType;
    private String address;
    private String phoneNumber;
    private String mobilePhoneNumber;
    private Integer singleRoom;
    private Integer doubleRoom;
    private Integer tripleRoom;
    private Integer otherRoom;
    private Integer roomCount;
    private Integer bedCount;
    private Integer isHotel;
    private String photoWay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public Integer getSingleRoom() {
        return singleRoom;
    }

    public void setSingleRoom(Integer singleRoom) {
        this.singleRoom = singleRoom;
    }

    public Integer getDoubleRoom() {
        return doubleRoom;
    }

    public void setDoubleRoom(Integer doubleRoom) {
        this.doubleRoom = doubleRoom;
    }

    public Integer getTripleRoom() {
        return tripleRoom;
    }

    public void setTripleRoom(Integer tripleRoom) {
        this.tripleRoom = tripleRoom;
    }

    public Integer getOtherRoom() {
        return otherRoom;
    }

    public void setOtherRoom(Integer otherRoom) {
        this.otherRoom = otherRoom;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public Integer getBedCount() {
        return bedCount;
    }

    public void setBedCount(Integer bedCount) {
        this.bedCount = bedCount;
    }

    public Integer getHotel() {
        return isHotel;
    }

    public void setHotel(Integer hotel) {
        isHotel = hotel;
    }

    public String getPhotoWay() {
        return photoWay;
    }

    public void setPhotoWay(String photoWay) {
        this.photoWay = photoWay;
    }

    public Integer getHotelType() {
        return hotelType;
    }

    public void setHotelType(Integer hotelType) {
        this.hotelType = hotelType;
    }
}
