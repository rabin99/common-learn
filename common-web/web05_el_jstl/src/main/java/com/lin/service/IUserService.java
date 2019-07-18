package com.lin.service;

import com.lin.domain.User;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/6/14 12:55
 * @desc :
 */
public interface IUserService {
    List<User> findAll();
}
