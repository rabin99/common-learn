package spring02_02accout_xmlioc.service.impl;

import spring02_02accout_xmlioc.dao.IAccountDao;
import spring02_02accout_xmlioc.domain.Account;
import spring02_02accout_xmlioc.service.IAccountService;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/22 15:56
 * @desc :
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return null;
    }

    @Override
    public void saveAccount(Account account) {

    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void deleteAccount(Integer acccountId) {

    }
}
