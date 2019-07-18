package com.lin.dao;

import com.lin.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/23 17:23
 * @desc :
 */
public interface IUserAnnotationDao {
    @Select("select * from user")
    List<User> findAll();
}
