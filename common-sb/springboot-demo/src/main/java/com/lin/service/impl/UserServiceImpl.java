package com.lin.service.impl;

import com.lin.dao.CommonUserMapper;
import com.lin.dao.UserMapper;
import com.lin.domain.User;
import com.lin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/7/2 18:33
 * @desc :
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonUserMapper commonUserMapper;

    @Override
    public User selectById(Long id) {

        User user = commonUserMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> commonSelectByExample() {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("id<3");
        return commonUserMapper.selectByExample(example);
    }
}
