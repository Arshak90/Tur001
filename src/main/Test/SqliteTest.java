import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by ArtStyle on 21.01.2017.
 */
public class SqliteTest {
    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ArtStyle\\Desktop\\Project\\Project2\\Project1\\src\\main\\resources\\db.sqlite");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
//            String sql = "CREATE TABLE COMPANYS " +
//                    "(ID INT PRIMARY KEY     NOT NULL," +
//                    " NAME           TEXT    NOT NULL, " +
//                    " AGE            INT     NOT NULL, " +
//                    " ADDRESS        CHAR(50), " +
//                    " SALARY         REAL)";
//            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
