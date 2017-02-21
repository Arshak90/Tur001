package touristSights;

/**
 * Created by gev on 19.02.2017.
 */
public class TouristSight {
    private Integer id;
    private String name;
    private String address;
    private String photoWay;
    private String description;

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

    public String getPhotoWay() {
        return photoWay;
    }

    public void setPhotoWay(String photoWay) {
        this.photoWay = photoWay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
