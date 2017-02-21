package restaurant;

import Core.Interface.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gev on 19.02.2017.
 */
public class RestaurantDao implements Dao<Restaurant> {


    @Override
    public List<Restaurant> getAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM Restaurant";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Restaurant restaurant = new Restaurant();
                restaurant.setId(res.getInt("id"));
                restaurant.setName(res.getString("name"));
                restaurant.setAddress(res.getString("address"));
                restaurant.setPhoneNumber(res.getString("phoneNumber"));
                restaurant.setMobilePhoneNumber(res.getString("mobilePhoneNumber"));
                restaurant.setPhotoWay(res.getString("photoWay"));
                restaurant.setEmail(res.getString("email"));
                restaurant.setSite(res.getString("site"));
                restaurant.setLegalName(res.getString("legalName"));
                restaurants.add(restaurant);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return restaurants;
    }

    @Override
    public Restaurant getById(Integer id) {

        String sql = "SELECT * FROM Restaurant WHERE id = ?";
        ResultSet rs = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getInt("id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setPhoneNumber(rs.getString("phoneNumber"));
                restaurant.setMobilePhoneNumber(rs.getString("mobilePhoneNumber"));
                restaurant.setPhotoWay(rs.getString("photoWay"));
                restaurant.setEmail(rs.getString("email"));
                restaurant.setSite(rs.getString("site"));
                restaurant.setLegalName(rs.getString("legalName"));

                return restaurant;
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

    @Override
    public boolean update(Restaurant item) {
        String sql = "UPDATE Restaurant SET name = ?, address = ?, phoneNumber = ?, mobilePhoneNumber = ?, photoWay = ?," +
                " email=?,site=?,legalName=? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getAddress());
            pstmt.setString(3, item.getPhoneNumber());
            pstmt.setString(4, item.getMobilePhoneNumber());
            pstmt.setString(5, item.getPhotoWay());
            pstmt.setString(6, item.getEmail());
            pstmt.setString(7, item.getSite());
            pstmt.setString(8, item.getLegalName());
            pstmt.setInt(9, item.getId());


            // update
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean insert(Restaurant item) {
        String sql = "INSERT INTO Restaurant(name,address,phoneNumber,mobilePhoneNumber," +
                "photoWay,email,site,legalName) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getAddress());
            pstmt.setString(3, item.getPhoneNumber());
            pstmt.setString(4, item.getMobilePhoneNumber());
            pstmt.setString(5, item.getPhotoWay());
            pstmt.setString(6, item.getEmail());
            pstmt.setString(7, item.getSite());
            pstmt.setString(8, item.getLegalName());
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {

        String sql = "DELETE FROM Restaurant WHERE id = ?";

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
