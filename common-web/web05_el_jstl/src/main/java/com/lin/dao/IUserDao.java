package com.lin.dao;

import com.lin.domain.User;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/6/14 12:31
 * @desc :
 */
public interface IUserDao {

    public List<User> findAll();
}
