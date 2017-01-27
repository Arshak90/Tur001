package Interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by arshak.askaryan on 1/25/2017.
 */
public interface Dao<T> {

    public List<T> getAll();

    public T getById(Integer id);

    public void insert(T item);

    public boolean update(T item);

    public boolean delete(Integer id);

    default Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:D:\\java\\turist\\src\\main\\resources\\db.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
