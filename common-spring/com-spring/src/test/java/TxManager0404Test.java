import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring04_04tx.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/6/3 14:50
 * @desc : 这个案例只是为了后续几个案例能直接复制粘贴基础代码
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean04_04.xml")
public class TxManager0404Test {
    @Autowired
    private IAccountService accountService;
    @Test
    public void testTransfer(){
        accountService.transfer("aaa","bbb",100f);
    }
}
