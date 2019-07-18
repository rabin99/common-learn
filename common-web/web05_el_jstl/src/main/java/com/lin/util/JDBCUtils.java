package com.lin.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @auther : lin
 * @date : 2019/6/14 12:55
 * @desc :
 */
public class JDBCUtils {

    private static DataSource ds;

    static {
        Properties properties = new Properties();
        try (InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");) {
            properties.load(resourceAsStream);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
