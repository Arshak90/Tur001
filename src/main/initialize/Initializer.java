import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Initializer implements ServletContextListener {

	public Initializer() {

	}

	private static ComboPooledDataSource dataSource;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String url = "jdbc:sqlite:D:\\java\\turist\\src\\main\\resources\\db.sqlite";

		try  {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(url);
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		dataSource.close();

	}

	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}
}
