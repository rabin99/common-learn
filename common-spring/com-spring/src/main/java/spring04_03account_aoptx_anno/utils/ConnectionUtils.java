package spring04_03account_aoptx_anno.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @auther : lin
 * @date : 2019/5/31 16:33
 * @desc :
 */
@Component
public class ConnectionUtils {
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    @Autowired
    private DataSource dataSource;

    public Connection getThreadConnection(){
        Connection connection = threadLocal.get();
        if(connection == null){
            try {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
    /*
    将Conneciton和线程绑定，这样同一个线程的多个dao方法调用确保是同一个connection,这样同一个方法多个调用能实现统一的回滚
     */
    public void removeConnection(){
        threadLocal.remove();
    }
}
