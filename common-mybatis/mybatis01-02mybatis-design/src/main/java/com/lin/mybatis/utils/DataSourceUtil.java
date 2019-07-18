package com.lin.mybatis.utils;

import com.lin.mybatis.cfg.Configuration;

import javax.management.RuntimeOperationsException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @auther : lin
 * @date : 2019/5/24 11:44
 * @desc :用于创建数据源的工具类
 */
public class DataSourceUtil {
    /*
    用于获取一个连接
     */
    public static Connection getConnection(Configuration cfg){
        try {
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
