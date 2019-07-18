package spring02_04account_annoioc_withoutxml_junit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring02_04account_annoioc_withoutxml_junit.dao.IAccountDao;
import spring02_04account_annoioc_withoutxml_junit.domain.Account;
import spring02_04account_annoioc_withoutxml_junit.service.IAccountService;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/22 18:24
 * @desc :
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao ;
    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        accountDao.deleteAccount(acccountId);
    }
}
