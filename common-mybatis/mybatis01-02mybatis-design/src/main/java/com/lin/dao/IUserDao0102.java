package com.lin.dao;

import com.lin.domain.User;
import com.lin.mybatis.annotation.Select;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/23 16:04
 * @desc :用户的持久层接口
 */
public interface IUserDao0102 {
    /*
    查询所有
     */
    @Select("select * from user")
    List<User> findAll();
}
