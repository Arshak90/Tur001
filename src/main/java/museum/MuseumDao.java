package museum;

import Core.Interface.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gev on 24.03.2017.
 */
public class MuseumDao implements Dao<Museum> {
    @Override
    public List<Museum> getAll() {
        List<Museum> museums = new ArrayList<>();
        String sql = "SELECT * FROM Museum";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Museum museum = new Museum();
                museum.setId(res.getInt("id"));
                museum.setName(res.getString("name"));
                museum.setAddress(res.getString("address"));
                museum.setPhoneNumber(res.getString("phoneNumber"));
                museum.setSite(res.getString("site"));
                museum.setPhotoWay(res.getString("photoWay"));
                museum.setEmail(res.getString("email"));
                museum.setIsPay(res.getBoolean("isPay"));
                museum.setPay(res.getDouble("pay"));
                museums.add(museum);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return museums;
    }

    @Override
    public Museum getById(Integer id) {

        String sql = "SELECT * FROM Museum WHERE id = ?";
        ResultSet rs = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                Museum museum = new Museum();
                museum.setId(rs.getInt("id"));
                museum.setName(rs.getString("name"));
                museum.setAddress(rs.getString("address"));
                museum.setPhoneNumber(rs.getString("phoneNumber"));
                museum.setSite(rs.getString("site"));
                museum.setPhotoWay(rs.getString("photoWay"));
                museum.setEmail(rs.getString("email"));
                museum.setIsPay(rs.getBoolean("isPay"));
                museum.setPay(rs.getDouble("pay"));

                return museum;
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
    public boolean update(Museum item) {
        String sql = "UPDATE Museum SET name = ?, address = ?, phoneNumber = ?,photoWay = ?," +
                " email=?,isPay=?,pay=?, site = ?  WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getAddress());
            pstmt.setString(3, item.getPhoneNumber());
            pstmt.setString(4, item.getPhotoWay());
            pstmt.setString(5, item.getEmail());
            pstmt.setBoolean(6, item.getIsPay());
            pstmt.setString(7, String.valueOf(item.getPay()));
            pstmt.setString(8, item.getSite());
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
    public boolean insert(Museum item) {
        String sql = "INSERT INTO Museum(name,address,phoneNumber,site," +
                "photoWay,email,isPay,pay) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getAddress());
            pstmt.setString(3, item.getPhoneNumber());
            pstmt.setString(4, item.getSite());
            pstmt.setString(5, item.getPhotoWay());
            pstmt.setString(6, item.getEmail());
            pstmt.setBoolean(7, item.getIsPay());
            pstmt.setString(8, String.valueOf(item.getPay()));
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {

        String sql = "DELETE FROM Museum WHERE id = ?";

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
