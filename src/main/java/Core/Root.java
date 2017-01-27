package Core;

import Core.Dao.YearDao;
import hotel.HotelDao;

/**
 * Created by arshak.askaryan on 1/26/2017.
 */
public class Root {
    private YearDao yearDao;
    private HotelDao hotelDao;

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
}
