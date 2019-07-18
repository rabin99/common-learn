package com.lin0303.dao;

import com.lin0303.domain.Account;
import com.lin0303.domain.AccountUser;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/27 16:08
 * @desc :
 */
public interface IAccountDao {
    /**
     * 查询所有账户，同时还要获取到当前账户的所属用户信息
     *
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户，并且带有用户名称和地址信息
     *
     * @return
     */
    List<AccountUser> findAllAccount();
}
