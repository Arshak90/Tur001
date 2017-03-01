package portfolio;

import Core.Interface.Dao;
import Models.Sights;
import Models.Transport;

import java.sql.*;
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
                portfolio.setQuarter(rs.getInt("quarter"));
                portfolio.setTransportid(rs.getInt("transportId"));
                portfolio.setTotaltouristcount(rs.getInt("totalTouristCount"));
                portfolio.setArmtouristcount(rs.getInt("armTouristCount"));
                portfolio.setOthertouristcount(rs.getInt("otherTouristCount"));
                portfolio.setFinances(rs.getDouble("finances"));
                portfolio.setIctouristcount(rs.getInt("icTouristCount"));
                portfolio.setIcmalecount(rs.getInt("icMaleCount"));
                portfolio.setIcfemalecount(rs.getInt("icFemaleCount"));
                portfolio.setIcvisitdecription(rs.getString("icVisitDecription"));
                portfolio.setSocialpackagecount(rs.getInt("socialPackageCount"));
                portfolio.setColumn_12(rs.getInt("column_12"));
                portfolio.setIstouroperator(rs.getBoolean("isTourOperator"));
                portfolio.setAge15(rs.getInt("age15"));
                portfolio.setAge30(rs.getInt("age30"));
                portfolio.setAge50(rs.getInt("age50"));
                portfolio.setAge51(rs.getInt("age51"));
                portfolio.setYear(rs.getInt("year"));
                portfolios.add(portfolio);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return portfolios;
    }

    @Override
    public Portfolio getById(Integer id) {
        String sql = "SELECT * FROM Portfolio WHERE id = ?";
        ResultSet rs = null;
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                Portfolio portfolio = new Portfolio();
                portfolio.setId(rs.getInt("id"));
                portfolio.setQuarter(rs.getInt("quarter"));
                portfolio.setTransportid(rs.getInt("transportId"));
                portfolio.setTotaltouristcount(rs.getInt("totalTouristCount"));
                portfolio.setArmtouristcount(rs.getInt("armTouristCount"));
                portfolio.setOthertouristcount(rs.getInt("otherTouristCount"));
                portfolio.setFinances(rs.getDouble("finances"));
                portfolio.setIctouristcount(rs.getInt("icTouristCount"));
                portfolio.setIcmalecount(rs.getInt("icMaleCount"));
                portfolio.setIcfemalecount(rs.getInt("icFemaleCount"));
                portfolio.setIcvisitdecription(rs.getString("icVisitDecription"));
                portfolio.setSocialpackagecount(rs.getInt("socialPackageCount"));
                portfolio.setColumn_12(rs.getInt("column_12"));
                portfolio.setIstouroperator(rs.getBoolean("isTourOperator"));
                portfolio.setAge15(rs.getInt("age15"));
                portfolio.setAge30(rs.getInt("age30"));
                portfolio.setAge50(rs.getInt("age50"));
                portfolio.setAge51(rs.getInt("age51"));
                portfolio.setYear(rs.getInt("year"));

                return portfolio;
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
    public boolean insert(Portfolio item) {
        String sql = "INSERT INTO Portfolio(quarter,totaltouristcount,armtouristcount,othertouristcount,finances,ictouristcount,icmalecount," +
                "icfemalecount,icvisitdecription,socialpackagecount,transportid,column_12,istouroperator,age15,age30,age50,age51,year) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, item.getQuarter());
            pstmt.setInt(2, item.getTotaltouristcount());
            pstmt.setInt(3, item.getArmtouristcount());
            pstmt.setInt(4, item.getOthertouristcount());
            pstmt.setDouble(5, item.getFinances());
            pstmt.setInt(6, item.getIctouristcount());
            pstmt.setInt(7, item.getIcmalecount());
            pstmt.setInt(8, item.getIcfemalecount());
            pstmt.setString(9, item.getIcvisitdecription());
            pstmt.setInt(10, item.getSocialpackagecount());
            pstmt.setInt(11, item.getTransportid());
            pstmt.setInt(12, item.getColumn_12());
            pstmt.setBoolean(13, item.getIstouroperator());
            pstmt.setInt(14, item.getAge15());
            pstmt.setInt(15, item.getAge30());
            pstmt.setInt(16, item.getAge50());
            pstmt.setInt(17, item.getAge51());
            pstmt.setInt(18, item.getYear());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Portfolio item) {
        String sql = "UPDATE Portfolio SET quarter = ?, totaltouristcount = ?, armtouristcount = ?, othertouristcount = ?, finances = ?," +
                " ictouristcount = ?, icmalecount = ?, icfemalecount = ?, icvisitdecription = ?, socialpackagecount = ?, transportid = ?, column_12 = ?," +
                " istouroperator = ?, age15=?,age30=?,age50=?,age51=?,year=? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, item.getQuarter());
            pstmt.setInt(2, item.getTotaltouristcount());
            pstmt.setInt(3, item.getArmtouristcount());
            pstmt.setInt(4, item.getOthertouristcount());
            pstmt.setDouble(5, item.getFinances());
            pstmt.setInt(6, item.getIctouristcount());
            pstmt.setInt(7, item.getIcmalecount());
            pstmt.setInt(8, item.getIcfemalecount());
            pstmt.setString(9, item.getIcvisitdecription());
            pstmt.setInt(10, item.getSocialpackagecount());
            pstmt.setInt(11, item.getTransportid());
            pstmt.setInt(12, item.getColumn_12());
            pstmt.setBoolean(13, item.getIstouroperator());
            pstmt.setInt(14, item.getAge15());
            pstmt.setInt(15, item.getAge30());
            pstmt.setInt(16, item.getAge50());
            pstmt.setInt(17, item.getAge51());
            pstmt.setInt(18, item.getYear());
            pstmt.setInt(19, item.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public List<Portfoliocountry> getPortfolioCountry(){

        List<Portfoliocountry> portfoliocountries = new ArrayList<>();
        String sql = "SELECT * FROM PortfolioCountry";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Portfoliocountry portfoliocountry = new Portfoliocountry();
                portfoliocountry.setId(res.getInt("id"));
                portfoliocountry.setCountryid(res.getInt("countryId"));
                portfoliocountry.setPortfolioid(res.getInt("portfolioId"));
                portfoliocountry.setCount(res.getInt("count"));
                portfoliocountries.add(portfoliocountry);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return portfoliocountries;
    }

    public List<Portfoliomonthly> getPortfolioMonthly(){

        List<Portfoliomonthly> portfoliomonthlies = new ArrayList<>();
        String sql = "SELECT * FROM PortfolioMonthly";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Portfoliomonthly portfoliomonthly = new Portfoliomonthly();
                portfoliomonthly.setId(res.getInt("id"));
                portfoliomonthly.setPortfolioid(res.getInt("portfolioId"));
                portfoliomonthly.setTotaltouristcount(res.getInt("totalTouristCount"));
                portfoliomonthly.setArmtouristcount(res.getInt("armTouristCount"));
                portfoliomonthly.setOthertouristcount(res.getInt("otherTouristCount"));
                portfoliomonthly.setFinances(res.getDouble("finances"));
                portfoliomonthly.setMonthId(res.getInt("monthId"));
                portfoliomonthlies.add(portfoliomonthly);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return portfoliomonthlies;
    }

    public List<Portfoliosights> getPortfolioSights(){

        List<Portfoliosights> portfoliosightses = new ArrayList<>();
        String sql = "SELECT * FROM PortfolioSights";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Portfoliosights portfoliosights = new Portfoliosights();
                portfoliosights.setId(res.getInt("id"));
                portfoliosights.setPortfolioid(res.getInt("portfolioId"));
                portfoliosights.setStightsid(res.getInt("sightsId"));
                portfoliosightses.add(portfoliosights);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return portfoliosightses;
    }

    public List<Transport> getTransport(){

        List<Transport> transports = new ArrayList<>();
        String sql = "SELECT * FROM Transport";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Transport transport = new Transport();
                transport.setId(res.getInt("id"));
                transport.setName(res.getString("name"));
                transports.add(transport);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return transports;
    }

    public List<Sights> getSightses(){

        List<Sights> sightses = new ArrayList<>();
        String sql = "SELECT * FROM Sights";

        try(Connection conn = this.connect();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql)) {

            while ( res.next() ){
                Sights sights = new Sights();
                sights.setId(res.getInt("id"));
                sights.setName(res.getString("name"));
                sightses.add(sights);
            }
        }catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return sightses;
    }

    public boolean insertPortfoliosights(Integer portfolioId, Integer sightsId) {
        String sql = "INSERT INTO PortfolioSights(portfolioId,sightsId) VALUES (?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, portfolioId);
            pstmt.setInt(2, sightsId);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updatePortfoliosights(Portfoliosights item) {
        String sql = "UPDATE PortfolioSights SET portfolioId = ?, sightsId = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, item.getPortfolioid());
            pstmt.setInt(2, item.getStightsid());
            pstmt.setInt(3, item.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Portfoliosights> getPortfoliosightsByPortfolioId(Integer id){

        String sql = "SELECT * FROM PortfolioSights WHERE portfolioId = ?";
        ResultSet rs = null;
        List<Portfoliosights> portfoliosightses = new ArrayList<>();
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);

            // update
            rs  = pstmt.executeQuery();
            while ( rs.next() ) {
                Portfoliosights portfoliosights = new Portfoliosights();
                portfoliosights.setId(rs.getInt("id"));
                portfoliosights.setPortfolioid(rs.getInt("portfolioId"));
                portfoliosights.setStightsid(rs.getInt("sightsId"));
                portfoliosightses.add(portfoliosights);
            }
            return portfoliosightses;

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

    public boolean insertPortfoliomonthly(Portfoliomonthly portfoliomonthly) {
        String sql = "INSERT INTO PortfolioMonthly(portfolioId,totalTouristCount,finances, monthId) VALUES (?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, portfoliomonthly.getPortfolioid());
            pstmt.setInt(2, portfoliomonthly.getTotaltouristcount());
            pstmt.setDouble(3, portfoliomonthly.getFinances());
            pstmt.setInt(4,portfoliomonthly.getMonthId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deletePortfoliosightses(Integer id){
        String sql = "DELETE FROM PortfolioSights WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deletePortfoliomonthly(Integer id){
        String sql = "DELETE FROM PortfolioMonthly WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
