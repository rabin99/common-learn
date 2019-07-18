package com.lin.dao;

import com.lin.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @auther : lin
 * @date : 2019/7/3 17:51
 * @desc :
 */
@Repository("commonUserMapper")
@Mapper
public interface CommonUserMapper extends tk.mybatis.mapper.common.Mapper<User> {

}
