package spring01_05DI.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring01_05DI.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/22 14:30
 * @desc :
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean01_05.xml");
        IAccountService as = (IAccountService) ac.getBean("accountService3");
        as.saveAccount();
    }
}
