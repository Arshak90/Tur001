package Core;

import Core.Dao.CountryDao;
import Core.Dao.YearDao;
import hotel.HotelDao;
import login.UserDao;
import museum.MuseumDao;
import portfolio.PortfolioDao;
import restaurant.RestaurantDao;
import tourOperator.TourOperatorDao;
import touristSights.TouristSightDao;

/**
 * Created by arshak.askaryan on 1/26/2017.
 */
public class Root {
    private YearDao yearDao;
    private HotelDao hotelDao;
    private PortfolioDao portfolioDao;
    private RestaurantDao restaurantDao;
    private MuseumDao museumDao;
    private TouristSightDao touristSightDao;
    private TourOperatorDao tourOperatorDao;
    private CountryDao countryDao;
    private home.CountryDao countryDaoForHome;
    private UserDao userDao;

    public UserDao getUserDao() {
        if(this.userDao == null){
            userDao = new UserDao();
        }
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public YearDao getYearDao() {
        if(this.yearDao == null){
            yearDao = new YearDao();
        }
        return yearDao;
    }

    public void setYearDao(YearDao yearDao) {
        this.yearDao = yearDao;
    }

    public HotelDao getHotelDao() {
        if(this.hotelDao == null){
            hotelDao = new HotelDao();
        }
        return hotelDao;
    }

    public void setHotelDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public PortfolioDao getPortfolioDao() {
        if(this.portfolioDao == null){
            this.portfolioDao = new PortfolioDao();
        }
        return portfolioDao;
    }

    public void setPortfolioDao(PortfolioDao portfolioDao) {
        this.portfolioDao = portfolioDao;
    }

    public RestaurantDao getRestaurantDao() {
        if(this.restaurantDao == null){
            restaurantDao = new RestaurantDao();
        }
        return restaurantDao;
    }

    public void setRestaurantDao(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    public MuseumDao getMuseumDao() {
        if(this.museumDao == null){
            museumDao = new MuseumDao();
        }
        return museumDao;
    }

    public void setMuseumDao(MuseumDao museumDao) {
        this.museumDao = museumDao;
    }

    public TouristSightDao getTouristSightDao() {
        if(this.touristSightDao == null){
            touristSightDao = new TouristSightDao();
        }
        return touristSightDao;
    }

    public void setTouristSightDao(TouristSightDao touristSightDao) {
        this.touristSightDao = touristSightDao;
    }

    public TourOperatorDao getTourOperatorDao() {
        if(this.tourOperatorDao == null){
            tourOperatorDao = new TourOperatorDao();
        }
        return tourOperatorDao;
    }

    public void setTourOperatorDao(TourOperatorDao tourOperatorDao) {
        this.tourOperatorDao = tourOperatorDao;
    }

    public CountryDao getCountryDao() {
        if(countryDao == null){
            countryDao = new CountryDao();
        }
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public home.CountryDao getCountryDaoForHome() {
        if(countryDaoForHome == null){
            countryDaoForHome = new home.CountryDao();
        }
        return countryDaoForHome;
    }

    public void setCountryDaoForHome(home.CountryDao countryDaoForHome) {
        this.countryDaoForHome = countryDaoForHome;
    }
}
