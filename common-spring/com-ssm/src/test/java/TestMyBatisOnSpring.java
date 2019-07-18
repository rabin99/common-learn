import com.lin.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @auther : lin
 * @date : 2019/6/28 18:10
 * @desc :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TestMyBatisOnSpring {
    @Autowired
    IAccountService accountService;

    @Test
    public void testRun(){
        accountService.findAll().forEach(System.out::println);
    }
}
