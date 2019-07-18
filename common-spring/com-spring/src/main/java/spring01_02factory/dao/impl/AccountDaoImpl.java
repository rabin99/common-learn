package spring01_02factory.dao.impl;

import spring01_02factory.dao.IAccountDao;

/**
 * @auther : lin
 * @date : 2019/5/17 17:00
 * @desc :
 */
public class AccountDaoImpl implements IAccountDao {
    public AccountDaoImpl() {
        System.out.println("accountDaoImp");
    }

    @Override
    public void saveAccount() {
        System.out.println("保存了账户");
    }
}
