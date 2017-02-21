package portfolio;

import Core.Interface.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public class PortfolioDao implements Dao<Portfolio>{
    @Override
    public List<Portfolio> getAll() {
        List<Portfolio> portfolios = new ArrayList<>();
        String sql = "SELECT * FROM Portfolio";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while ( rs.next() ) {
                Portfolio portfolio = new Portfolio();
                portfolio.setId(rs.getInt("id"));
                portfolio.setYear(rs.getInt("year"));
                portfolio.setQuarter(rs.getInt("quarter"));
                portfolios.add(portfolio);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return portfolios;
    }

    @Override
    public Portfolio getById(Integer id) {
        return null;
    }

    @Override
    public boolean insert(Portfolio item) {
       return false;
    }

    @Override
    public boolean update(Portfolio item) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
