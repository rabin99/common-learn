package com.lin.service.impl;

import com.fasterxml.jackson.core.format.InputAccessor;
import com.lin.domain.Account;
import com.lin.mapper.AccountMapper;
import com.lin.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/16 14:37
 * @desc :
 */
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findById(int id) {
        return accountMapper.findById(id);
    }

    @Override
    public List<Account> list() {
        List<Account> list = accountMapper.list();
        return list;
    }

    @Override
    public int update() {
        return 0;
    }

    @Override
    public int add(Account account) {
        return accountMapper.add(account.getId(),account.getName(),account.getMoney());
    }

    @Override
    public int delete() {
        return 0;
    }
}
