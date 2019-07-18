package com.lin.service.impl;

import com.lin.dao.IUserDao;
import com.lin.dao.impl.UserDaoImpl;
import com.lin.domain.User;
import com.lin.service.IUserService;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/6/14 13:37
 * @desc :
 */
public class UserServiceImpl implements IUserService {
    private IUserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }
}
