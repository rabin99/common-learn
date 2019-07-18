package spring02_01anno_ioc.dao.impl;

import org.springframework.stereotype.Repository;
import spring02_01anno_ioc.dao.IAccountDao;

/**
 * @auther : lin
 * @date : 2019/5/22 15:26
 * @desc :
 */
@Repository("accountDao1")
public class AccountDaoImpl implements IAccountDao {
    @Override
    public void saveAccount() {
        System.out.println("保存了账户1111111111111");
    }
}
