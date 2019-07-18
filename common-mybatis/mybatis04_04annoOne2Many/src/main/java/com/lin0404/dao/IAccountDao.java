package com.lin0404.dao;

import com.lin0404.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/28 16:32
 * @desc :
 */
public interface IAccountDao {
    /**
     * 查询所有账户，并且获取每个账户所属的用户信息
     * 一个账号只属于一个用户
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(column = "uid", property = "user", one = @One(select = "com.lin0404.dao.IUserDao.findById", fetchType = FetchType.EAGER))
    })
    List<Account> findAll();


    /**
     * 根据用户id查询账户信息
     *
     * @param userId
     * @return
     */
    @Select("select * from account where uid = #{userId}")
    List<Account> findAccountByUid(Integer userId);
}
