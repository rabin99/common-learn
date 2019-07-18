package spring01_01jdbc;

import com.mysql.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @auther : lin
 * @date : 2019/5/15 10:07
 * @desc :
 */
public class JdbcDemo {
    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new Driver());
    }
}
