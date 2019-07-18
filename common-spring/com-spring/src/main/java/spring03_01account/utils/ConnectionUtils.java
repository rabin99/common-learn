package spring03_01account.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @auther : lin
 * @date : 2019/5/20 20:23
 * @desc : 连接工具类，用于从数据源中获取一个连接，并且实现和线程的绑定，
 * 也就是说一个线程内的操作都是用的统一个conneciton，这样在一个线程内，执行多个操作，能全部回滚。
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getThreadConnection() {
        Connection connection = threadLocal.get();
        try {
            if (connection == null) {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeConnection(){
        threadLocal.remove();
    }
}
