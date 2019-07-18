package spring04_06tx_anno.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring04_06tx_anno.dao.IAccountDao;
import spring04_06tx_anno.domain.Account;
import spring04_06tx_anno.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/6/3 14:39
 * @desc :
 */
@Service("accountService0406")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false) // 全局只读事务，事务的传播含义见bean04_05.xml
public class AccountServiceImpl implements IAccountService {
    @Autowired
    @Qualifier("accountDao0406")
    private IAccountDao accountDao;

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false) // 需要的是读写型事务配置
    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer....");
        //2.1根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2.2根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //2.3转出账户减钱
        source.setMoney(source.getMoney() - money);
        //2.4转入账户加钱
        target.setMoney(target.getMoney() + money);
        //2.5更新转出账户
        accountDao.updateAccount(source);
//        int i=1/0;
        //2.6更新转入账户
        accountDao.updateAccount(target);
    }

    /**
     * 测试Propagation
     * <p>
     * REQUIRED:业务方法需要在一个容器里运行。如果方法运行时，已经处在一个事务中，那么加入到这个事务，否则自己新建一个新的事务。
     * NOT_SUPPORTED:声明方法不需要事务。如果方法没有关联到一个事务，容器不会为他开启事务，如果方法在一个事务中被调用，该事务会被挂起，调用结束后，原先的事务会恢复执行。
     * REQUIRESNEW:不管是否存在事务，该方法总汇为自己发起一个新的事务。如果方法已经运行在一个事务中，则原有事务挂起，新的事务被创建。
     * MANDATORY：该方法只能在一个已经存在的事务中执行，业务方法不能发起自己的事务。如果在没有事务的环境下被调用，容器抛出例外。
     * SUPPORTS:该方法在某个事务范围内被调用，则方法成为该事务的一部分。如果方法在该事务范围外被调用，该方法就在没有事务的环境下执行。
     * NEVER：该方法绝对不能在事务范围内执行。如果在就抛例外。只有该方法没有关联到任何事务，才正常执行。
     * NESTED:如果一个活动的事务存在，则运行在一个嵌套的事务中。如果没有活动事务，则按REQUIRED属性执行。它使用了一个单独的事务，这个事务
     * 拥有多个可以回滚的保存点。内部事务的回滚不会对外部事务造成影响。它只对DataSourceTransactionManager事务管理器起效。
     */

    /**
     * 没有注解，则没有事务，出错
     */
    @Override
    @Transactional
    public void testNoTransferAnnotation() {
        Account source = accountDao.findAccountByName("aaa");
        Account target = accountDao.findAccountByName("bbb");
        source.setMoney(source.getMoney() - 100f);
        target.setMoney(target.getMoney() + 100f);
        accountDao.updateAccount(source);
        int i = 1 / 0;
        accountDao.updateAccount(target);
    }

    public void rightTransfer() {
        System.out.println(1);
        Account source = accountDao.findAccountByName("aaa");
        Account target = accountDao.findAccountByName("bbb");
        source.setMoney(source.getMoney() - 100f);
        target.setMoney(target.getMoney() + 100f);
        accountDao.updateAccount(source);
        accountDao.updateAccount(target);
    }
    public void errorTransfer() {
        Account source = accountDao.findAccountByName("ccc");
        Account target = accountDao.findAccountByName("ddd");
        source.setMoney(source.getMoney() - 100f);
        target.setMoney(target.getMoney() + 100f);
        accountDao.updateAccount(source);
        int i = 1 / 0;
        accountDao.updateAccount(target);
    }

    @Override

    public void propagationREQUIRED() {
        this.errorTransfer();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = false)
    public void propagationNOT_SUPPORTED() {
        System.out.println("NOT_SUPPORTED");
        Account source = accountDao.findAccountByName("ccc");
        Account target = accountDao.findAccountByName("ddd");
        source.setMoney(source.getMoney() - 100f);
        target.setMoney(target.getMoney() + 100f);
        accountDao.updateAccount(source);
        int i = 1 / 0;
        accountDao.updateAccount(target);
    }

    @Override
    public void propagationREQUIRESNEW() {
        this.errorTransfer();
    }

    @Override
    public void propagationMANDATORY() {
        this.errorTransfer();
    }

    @Override
    public void propagationSUPPORTS() {
        this.errorTransfer();
    }

    @Override
    public void propagationNEVER() {
        this.errorTransfer();
    }

    @Override
    public void propagationNESTED() {
        this.errorTransfer();
    }
}
