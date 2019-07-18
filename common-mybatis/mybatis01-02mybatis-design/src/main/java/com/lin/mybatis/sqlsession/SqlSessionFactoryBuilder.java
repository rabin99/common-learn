package com.lin.mybatis.sqlsession;

import com.lin.mybatis.cfg.Configuration;
import com.lin.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.lin.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @auther : lin
 * @date : 2019/5/24 14:57
 * @desc : 用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {
    /*
     * 根据参数的字节输入流来构建一个SqlSessionFactory工厂
     */
    public SqlSessionFactory build(InputStream config){
        Configuration configuration = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(configuration);
    }
}
