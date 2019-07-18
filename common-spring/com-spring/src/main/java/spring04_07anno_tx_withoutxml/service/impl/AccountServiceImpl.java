package spring04_07anno_tx_withoutxml.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring04_07anno_tx_withoutxml.dao.IAccountDao;
import spring04_07anno_tx_withoutxml.domain.Account;
import spring04_07anno_tx_withoutxml.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/6/3 14:39
 * @desc :
 */
@Service("accountService0407")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) // 全局只读事务，事务的传播含义见bean04_05.xml
public class AccountServiceImpl implements IAccountService {
    @Autowired
    @Qualifier("accountDao0407")
    private IAccountDao accountDao;

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false) // 需要的是读写型事务配置
    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer....");
        //2.1根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2.2根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //2.3转出账户减钱
        source.setMoney(source.getMoney()-money);
        //2.4转入账户加钱
        target.setMoney(target.getMoney()+money);
        //2.5更新转出账户
        accountDao.updateAccount(source);

//        int i=1/0;

        //2.6更新转入账户
        accountDao.updateAccount(target);
    }
}
