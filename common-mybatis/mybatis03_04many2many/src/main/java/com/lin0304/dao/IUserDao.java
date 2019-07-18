package com.lin0304.dao;

import com.lin0304.domain.User;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/27 17:47
 * @desc :
 */
public interface IUserDao {
    /**
     * 查询所有用户，同时获取到用户下所有账户的信息
     * @return
     */
    List<User> findAll();


    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);
}
