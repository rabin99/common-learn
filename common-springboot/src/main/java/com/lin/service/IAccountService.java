package com.lin.service;

import com.lin.domain.Account;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/16 13:49
 * @desc :
 */
public interface IAccountService {

    Account findById(int id);

    List<Account> list();

    int update();

    int add(Account account);

    int delete();
}
