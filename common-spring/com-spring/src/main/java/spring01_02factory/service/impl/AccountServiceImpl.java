package spring01_02factory.service.impl;

import spring01_02factory.dao.IAccountDao;
import spring01_02factory.factory.BeanFactory;
import spring01_02factory.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/20 11:10
 * @desc :
 */
public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao = (IAccountDao) BeanFactory.getBean("accountDao");

    public AccountServiceImpl() {
        System.out.println("accountServiceImpl");
    }

    @Override
    public void saveAccount() {
        int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
