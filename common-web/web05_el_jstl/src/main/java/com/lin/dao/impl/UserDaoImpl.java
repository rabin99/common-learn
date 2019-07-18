package com.lin.dao.impl;

import com.lin.dao.IUserDao;
import com.lin.domain.User;
import com.lin.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/6/14 12:56
 * @desc :
 */
public class UserDaoImpl implements IUserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        String sql = "select * from useraaa";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }
}
