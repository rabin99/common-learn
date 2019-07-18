package com.lin.dao.impl;

import com.lin.dao.IUserDao;
import com.lin.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/23 17:32
 * @desc : 自己来实现dao层，而不是使用mybatis的dao层 ，需要传入一个SqlSessionFactory对象
 */
public class UserDaoImpl implements IUserDao {
     private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public void setFactory(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        // 1. 使用工厂创建sqlSession
        SqlSession session = factory.openSession();
        // 2. 使用session执行查询所有方法
        List<User> users = session.selectList("com.lin.dao.IUserDao.findAll");
        session.close();
        return users;
    }
}
