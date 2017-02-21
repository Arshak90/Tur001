package tourOperator;

import Core.Interface.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gev on 19.02.2017.
 */
public class TourOperatorDao implements Dao<TourOperator> {


    @Override
    public List<TourOperator> getAll() {
        List<TourOperator> tourOperators = new ArrayList<>();
        String sql = "SELECT * FROM TourOperator";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                TourOperator tourOperator = new TourOperator();
                tourOperator.setId(res.getInt("id"));
                tourOperator.setName(res.getString("name"));
                tourOperator.setAddress(res.getString("address"));
                tourOperator.setPhoneNumber(res.getString("phoneNumber"));
                tourOperator.setMobilePhoneNumber(res.getString("mobilePhoneNumber"));
                tourOperator.setPhotoWay(res.getString("photoWay"));
                tourOperator.setEmail(res.getString("email"));
                tourOperator.setSite(res.getString("site"));
                tourOperator.setLegalName(res.getString("legalName"));
                tourOperators.add(tourOperator);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return tourOperators;
    }

    @Override
    public TourOperator getById(Integer id) {

        String sql = "SELECT * FROM TourOperator WHERE id = ?";
        ResultSet rs = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                TourOperator tourOperator = new TourOperator();
                tourOperator.setId(rs.getInt("id"));
                tourOperator.setName(rs.getString("name"));
                tourOperator.setAddress(rs.getString("address"));
                tourOperator.setPhoneNumber(rs.getString("phoneNumber"));
                tourOperator.setMobilePhoneNumber(rs.getString("mobilePhoneNumber"));
                tourOperator.setPhotoWay(rs.getString("photoWay"));
                tourOperator.setEmail(rs.getString("email"));
                tourOperator.setSite(rs.getString("site"));
                tourOperator.setLegalName(rs.getString("legalName"));

                return tourOperator;
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
    public boolean update(TourOperator item) {
        String sql = "UPDATE TourOperator SET name = ?, address = ?, phoneNumber = ?, mobilePhoneNumber = ?, photoWay = ?," +
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
    public boolean insert(TourOperator item) {
        String sql = "INSERT INTO TourOperator(name,address,phoneNumber,mobilePhoneNumber," +
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

        String sql = "DELETE FROM TourOperator WHERE id = ?";

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
