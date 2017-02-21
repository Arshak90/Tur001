package touristSights;

import Core.Interface.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gev on 19.02.2017.
 */
public class TouristSightDao implements Dao<TouristSight> {


    @Override
    public List<TouristSight> getAll() {
        List<TouristSight> touristSights = new ArrayList<>();
        String sql = "SELECT * FROM TouristSights";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                TouristSight touristSight = new TouristSight();
                touristSight.setId(res.getInt("id"));
                touristSight.setName(res.getString("name"));
                touristSight.setAddress(res.getString("address"));
                touristSight.setPhotoWay(res.getString("photoWay"));
                touristSight.setDescription(res.getString("description"));
                touristSights.add(touristSight);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return touristSights;
    }

    @Override
    public TouristSight getById(Integer id) {

        String sql = "SELECT * FROM TouristSights WHERE id = ?";
        ResultSet rs = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                TouristSight touristSight = new TouristSight();
                touristSight.setId(rs.getInt("id"));
                touristSight.setName(rs.getString("name"));
                touristSight.setAddress(rs.getString("address"));
                touristSight.setPhotoWay(rs.getString("photoWay"));
                touristSight.setDescription(rs.getString("description"));

                return touristSight;
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
    public boolean update(TouristSight item) {
        String sql = "UPDATE TouristSights SET name = ?, address = ?, photoWay = ?," +
                " description = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getAddress());
            pstmt.setString(3, item.getPhotoWay());
            pstmt.setString(4, item.getDescription());
            pstmt.setInt(5, item.getId());


            // update
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean insert(TouristSight item) {
        String sql = "INSERT INTO TouristSights(name,address," +
                "photoWay,description) VALUES (?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getAddress());
            pstmt.setString(3, item.getPhotoWay());
            pstmt.setString(4, item.getDescription());
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {

        String sql = "DELETE FROM TouristSights WHERE id = ?";

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
