package com.lin.dao;

import com.lin.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/6/28 14:21
 * @desc :
 */
@Repository
public interface IAccountDao {
    @Select("select * from account")
    List<Account> findAll();

    @Insert("insert into account (name,money) values (#{name},#{money})")
    void saveAccount(Account account);
}
