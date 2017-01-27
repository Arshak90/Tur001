package Models;

public class City {
  private Integer id;
  private String name;
  private String lat;
  private String lng;
  private Integer regionid;

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

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getLng() {
    return lng;
  }

  public void setLng(String lng) {
    this.lng = lng;
  }

  public Integer getRegionid() {
    return regionid;
  }

  public void setRegionid(Integer regionid) {
    this.regionid = regionid;
  }
}
