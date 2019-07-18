import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring04_02account_aoptx_xml.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/31 17:01
 * @desc : 通过是AccountServiceImpl中的tansfer中设置一个1/0抛出错误，来验证事务是否生效
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:bean04_02.xml"})
public class TxManager0402Text {
    @Autowired
    private IAccountService accountService;

    @Test
    public void test() {
        accountService.transfer("aaa", "bbb", 100F);
    }
}
