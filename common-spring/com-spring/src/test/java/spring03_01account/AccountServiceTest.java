package spring03_01account;

import spring03_01account.domain.Account;
import spring03_01account.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/20 20:54
 * @desc : 此案例演示转账transfer方法中当执行多个数据库操作，要保证conneciton是同一个才能回滚。手写回滚很麻烦，每个要先setCommit（false）；
 * 如果使用动态代理，默认在每个方法自动加入回滚代理就节省了很多编码工作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean03_01.xml")
public class AccountServiceTest {
    @Autowired
    @Qualifier("proxyAccountService") // 从beanfactory获取service对象
    private IAccountService as;

    @Test
    public void testTransfer(){
        as.transfer("aaa","bbb",100f);
    }

    @Test
    public void testFindAccount(){
        Account accountById = as.findAccountById(2);
        System.out.println(accountById);
    }
    @Test
    public void testSaveAccount(){
        as.saveAccount(new Account(1,"aaa",1000f));
        as.saveAccount(new Account(2,"bbb",1000f));
    }
    @Test
    public void testFindAllAccount(){
        List<Account> allAccount = as.findAllAccount();
        allAccount.forEach(System.out::println);
        System.out.println(Arrays.toString(allAccount.toArray()));
    }
}
