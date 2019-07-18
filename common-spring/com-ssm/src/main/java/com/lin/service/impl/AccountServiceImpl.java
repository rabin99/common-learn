package com.lin.service.impl;

import com.lin.dao.IAccountDao;
import com.lin.domain.Account;
import com.lin.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/6/28 14:26
 * @desc :
 */
@Slf4j
@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    @Override
    public List<Account> findAll() {
        log.info("业务层：查询所有账户...");
        return accountDao.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        log.info("业务层：保存账户...");
        accountDao.saveAccount(account);
    }
}
