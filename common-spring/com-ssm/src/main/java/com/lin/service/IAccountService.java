package com.lin.service;

import com.lin.domain.Account;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/6/28 14:25
 * @desc :
 */
public interface IAccountService {

    // 查询所有账户
    List<Account> findAll();

    // 保存帐户信息
    void saveAccount(Account account);
}
