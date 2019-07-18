import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring04_06tx_anno.service.IAccountService;


/**
 * @auther : lin
 * @date : 2019/6/3 14:50
 * @desc : 这个案例只是为了后续几个案例能直接复制粘贴基础代码
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean04_06.xml")
public class TxManager0406Test {
    @Autowired
    @Qualifier("accountService0406")
    private IAccountService accountService;

    @Test
    public void testTransfer() {
        accountService.transfer("aaa", "bbb", 100f);
    }

    @Test
    public void testNoTransferAnnotation() {
        /**
         * 全局配置了事务requrird& false ，那么方法即使没配置，也有事务
         */
        accountService.errorTransfer();
    }

    @Test
    public void testPropagation() {

    }

    @Test
    public void testPropagationNever() {

    }

    /*
    测试有问题！！除了propagationNOT_SUPPORTED，其他因为是事务的传播行为，说明已经有事务，只是如何传播。所以测试单个没有什么意义
     */
    @Test
    public void propagationREQUIRED() {

    }

    @Test
    public void propagationNOT_SUPPORTED() {
        accountService.propagationNOT_SUPPORTED();
    }

    @Test
    public void propagationREQUIRESNEW() {

    }

    @Test
    public void propagationMANDATORY() {

    }

    @Test
    public void propagationSUPPORTS() {

    }

    @Test
    public void propagationNEVER() {

    }

    @Test
    public void propagationNESTED() {

    }
}
