package Core.Models;

public class Region {
  private Integer id;
  private String name;
  private String lat;
  private String lng;
  private Integer countryid;

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

  public Integer getCountryid() {
    return countryid;
  }

  public void setCountryid(Integer countryid) {
    this.countryid = countryid;
  }
}
