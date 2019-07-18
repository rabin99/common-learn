package com.lin.mybatis.io;

import java.io.InputStream;

/**
 * @auther : lin
 * @date : 2019/5/24 11:36
 * @desc :使用类加载器读取配置文件的类
 */
public class Resources {
    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
