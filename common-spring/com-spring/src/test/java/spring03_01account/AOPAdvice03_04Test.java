package spring03_01account;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring03_04adviceType.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/21 16:12
 * @desc :
 */
public class AOPAdvice03_04Test {
    public static void main(String[] args) {
        /*
        AOP执行顺序：before -->  after-return/after-throw   -> after
         */
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:bean03_04advice.xml");
        IAccountService accountService = (IAccountService) ac.getBean("accountService");
        accountService.saveAccount();
    }
}
