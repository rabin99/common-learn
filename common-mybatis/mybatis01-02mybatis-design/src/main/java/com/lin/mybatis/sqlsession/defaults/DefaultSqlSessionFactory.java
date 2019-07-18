package com.lin.mybatis.sqlsession.defaults;

import com.lin.mybatis.cfg.Configuration;
import com.lin.mybatis.sqlsession.SqlSession;
import com.lin.mybatis.sqlsession.SqlSessionFactory;

/**
 * @auther : lin
 * @date : 2019/5/24 14:59
 * @desc : SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /*
    用于创建一个新的操作数据库对象
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
