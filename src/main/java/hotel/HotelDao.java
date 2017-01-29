package hotel;

import Core.Interface.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArtStyle on 23.01.2017.
 */
public class HotelDao implements Dao<Hotel> {


    @Override
    public List<Hotel> getAll() {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM Hotel";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Hotel hotel = new Hotel();
                hotel.setId(res.getInt("id"));
                hotel.setHotelType(res.getInt("HotelType"));
                hotel.setName(res.getString("name"));
                hotel.setAddress(res.getString("address"));
                hotel.setPhoneNumber(res.getString("phoneNumber"));
                hotel.setMobilePhoneNumber(res.getString("mobilePhoneNumber"));
                hotel.setSingleRoom(res.getInt("singleRoom"));
                hotel.setDoubleRoom(res.getInt("doubleRoom"));
                hotel.setTripleRoom(res.getInt("tripleRoom"));
                hotel.setOtherRoom(res.getInt("otherRoom"));
                hotel.setRoomCount(res.getInt("roomCount"));
                hotel.setBedCount(res.getInt("bedCount"));
                hotel.setHotel(res.getInt("isHotel"));
                hotel.setPhotoWay(res.getString("photoWay"));
                hotels.add(hotel);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return hotels;
    }

    @Override
    public Hotel getById(Integer id) {

        String sql = "SELECT * FROM Hotel WHERE id = ?";
        ResultSet rs = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setHotelType(rs.getInt("HotelType"));
                hotel.setName(rs.getString("name"));
                hotel.setAddress(rs.getString("address"));
                hotel.setPhoneNumber(rs.getString("phoneNumber"));
                hotel.setMobilePhoneNumber(rs.getString("mobilePhoneNumber"));
                hotel.setSingleRoom(rs.getInt("singleRoom"));
                hotel.setDoubleRoom(rs.getInt("doubleRoom"));
                hotel.setTripleRoom(rs.getInt("tripleRoom"));
                hotel.setOtherRoom(rs.getInt("otherRoom"));
                hotel.setRoomCount(rs.getInt("roomCount"));
                hotel.setBedCount(rs.getInt("bedCount"));
                hotel.setHotel(rs.getInt("isHotel"));
                hotel.setPhotoWay(rs.getString("photoWay"));

                return hotel;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<HotelType> getHotelTypes(){

        List<HotelType> hotelTypes = new ArrayList<>();
        String sql = "SELECT * FROM Hotel";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                HotelType hotelType = new HotelType();
                hotelType.setId(res.getInt("id"));
                hotelType.setName(res.getString("name"));
                hotelTypes.add(hotelType);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return hotelTypes;
    }

    @Override
    public boolean update(Hotel item) {
        String sql = "UPDATE Hotel SET name = ?, address = ?, phoneNumber = ?, mobilePhoneNumber = ?, singleRoom = ?, doubleRoom = ?, tripleRoom = ?, otherRoom = ?, roomCount = ?, bedCount = ?, isHotel = ?, photoWay = ?, hotelType = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getAddress());
            pstmt.setString(3, item.getPhoneNumber());
            pstmt.setString(4, item.getMobilePhoneNumber());
            pstmt.setInt(5, item.getSingleRoom());
            pstmt.setInt(6, item.getDoubleRoom());
            pstmt.setInt(7, item.getTripleRoom());
            pstmt.setInt(8, item.getOtherRoom());
            pstmt.setInt(9, item.getRoomCount());
            pstmt.setInt(10, item.getBedCount());
            pstmt.setInt(11, item.getHotel());
            pstmt.setString(12, item.getPhotoWay());
            pstmt.setInt(13, item.getHotelType());
            pstmt.setInt(14, item.getId());

            // update
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void insert(Hotel item) {
        String sql = "INSERT INTO Hotel(name,address,phoneNumber,mobilePhoneNumber,singleRoom,doubleRoom,tripleRoom,otherRoom,roomCount,bedCount,isHotel,photoWay,hotelType) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getAddress());
            pstmt.setString(3, item.getPhoneNumber());
            pstmt.setString(4, item.getMobilePhoneNumber());
            pstmt.setInt(5, item.getSingleRoom());
            pstmt.setInt(6, item.getDoubleRoom());
            pstmt.setInt(7, item.getTripleRoom());
            pstmt.setInt(8, item.getOtherRoom());
            pstmt.setInt(9, item.getRoomCount());
            pstmt.setInt(10, item.getBedCount());
            pstmt.setInt(11, item.getHotel());
            pstmt.setString(12, item.getPhotoWay());
            pstmt.setInt(13, item.getHotelType());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) {

        String sql = "DELETE FROM Hotel WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
