package portfolio;

public class Portfolio {
  private Integer id;
  private Integer quarter;
  private Integer totaltouristcount;
  private Integer armtouristcount;
  private Integer othertouristcount;
  private Double finances;
  private Integer ictouristcount;
  private Integer icmalecount;
  private Integer icfemalecount;
  private String icvisitdecription;
  private Integer socialpackagecount;
  private Integer transportid;
  private Integer column_12;
  private boolean istouroperator;
  private Integer age15;
  private Integer age30;
  private Integer age50;
  private Integer age51;
  private Integer year;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getQuarter() {
    return quarter;
  }

  public void setQuarter(Integer quarter) {
    this.quarter = quarter;
  }

  public Integer getTotaltouristcount() {
    return totaltouristcount;
  }

  public void setTotaltouristcount(Integer totaltouristcount) {
    this.totaltouristcount = totaltouristcount;
  }

  public Integer getArmtouristcount() {
    return armtouristcount;
  }

  public void setArmtouristcount(Integer armtouristcount) {
    this.armtouristcount = armtouristcount;
  }

  public Integer getOthertouristcount() {
    return othertouristcount;
  }

  public void setOthertouristcount(Integer othertouristcount) {
    this.othertouristcount = othertouristcount;
  }

  public Double getFinances() {
    return finances;
  }

  public void setFinances(Double finances) {
    this.finances = finances;
  }

  public Integer getIctouristcount() {
    return ictouristcount;
  }

  public void setIctouristcount(Integer ictouristcount) {
    this.ictouristcount = ictouristcount;
  }

  public Integer getIcmalecount() {
    return icmalecount;
  }

  public void setIcmalecount(Integer icmalecount) {
    this.icmalecount = icmalecount;
  }

  public Integer getIcfemalecount() {
    return icfemalecount;
  }

  public void setIcfemalecount(Integer icfemalecount) {
    this.icfemalecount = icfemalecount;
  }

  public String getIcvisitdecription() {
    return icvisitdecription;
  }

  public void setIcvisitdecription(String icvisitdecription) {
    this.icvisitdecription = icvisitdecription;
  }

  public Integer getSocialpackagecount() {
    return socialpackagecount;
  }

  public void setSocialpackagecount(Integer socialpackagecount) {
    this.socialpackagecount = socialpackagecount;
  }

  public Integer getTransportid() {
    return transportid;
  }

  public void setTransportid(Integer transportid) {
    this.transportid = transportid;
  }

  public Integer getColumn_12() {
    return column_12;
  }

  public void setColumn_12(Integer column_12) {
    this.column_12 = column_12;
  }

  public boolean getIstouroperator() {
    return istouroperator;
  }

  public void setIstouroperator(boolean istouroperator) {
    this.istouroperator = istouroperator;
  }

  public Integer getAge15() {
    return age15;
  }

  public void setAge15(Integer age15) {
    this.age15 = age15;
  }

  public Integer getAge30() {
    return age30;
  }

  public void setAge30(Integer age30) {
    this.age30 = age30;
  }

  public Integer getAge50() {
    return age50;
  }

  public void setAge50(Integer age50) {
    this.age50 = age50;
  }

  public Integer getAge51() {
    return age51;
  }

  public void setAge51(Integer age51) {
    this.age51 = age51;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return "Portfolio{" +
            "id=" + id +
            ", quarter=" + quarter +
            ", totaltouristcount=" + totaltouristcount +
            ", armtouristcount=" + armtouristcount +
            ", othertouristcount=" + othertouristcount +
            ", finances=" + finances +
            ", ictouristcount=" + ictouristcount +
            ", icmalecount=" + icmalecount +
            ", icfemalecount=" + icfemalecount +
            ", icvisitdecription='" + icvisitdecription + '\'' +
            ", socialpackagecount=" + socialpackagecount +
            ", transportid=" + transportid +
            ", column_12=" + column_12 +
            ", istouroperator=" + istouroperator +
            ", age15=" + age15 +
            ", age30=" + age30 +
            ", age50=" + age50 +
            ", age51=" + age51 +
            ", year=" + year +
            '}';
  }
}
