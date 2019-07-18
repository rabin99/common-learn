import com.lin.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auther : lin
 * @date : 2019/6/28 17:24
 * @desc :
 */
public class TestSpring {
    @Test
    public void run(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
        accountService.findAll().forEach(System.out::println);
    }
}
