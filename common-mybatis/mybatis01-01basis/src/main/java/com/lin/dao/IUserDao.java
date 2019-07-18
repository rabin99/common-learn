package com.lin.dao;

import com.lin.domain.User;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/23 16:04
 * @desc :
 */
public interface IUserDao {
    /*
    查询所有
     */
    List<User> findAll();
}
