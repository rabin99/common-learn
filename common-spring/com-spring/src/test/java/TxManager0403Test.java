import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring04_03account_aoptx_anno.config.Config;
import spring04_03account_aoptx_anno.domain.Account;
import spring04_03account_aoptx_anno.service.IAccountService;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/31 18:13
 * @desc : 使用纯注解配置ioc，自己实现的事务
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class TxManager0403Test {
    @Autowired
    private IAccountService accountService;
    @Test
    public void test(){
        List<Account> accounts = accountService.findAllAccount();
        accounts.stream().forEach(System.out::println);
    }
    @Test
    public void testTransfer(){
        accountService.transfer("aaa","bbb",100F);
    }
}
