package com.lin.dao;

import com.lin.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/7/3 13:43
 * @desc :
 */
@Repository("userMapper")
@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();
}
