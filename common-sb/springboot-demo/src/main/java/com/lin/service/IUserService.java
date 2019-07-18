package com.lin.service;

import com.lin.domain.User;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/7/4 9:36
 * @desc :
 */
public interface IUserService {

    User selectById(Long id);

    List<User> findAll();

    List<User> commonSelectByExample();
}
