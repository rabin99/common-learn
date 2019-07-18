package spring03_01account;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring03_03springAOP.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/21 14:26
 * @desc :
 */
public class AOPTest {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean03_03aop.xml");
        IAccountService as  = (IAccountService) ac.getBean("accountService");
//        as.saveAccount();
//        as.updateAccount(1);
//        as.deleteAccount();

        as.fatherExposeProxy();
        System.out.println("----");
        as.sonExposeProxy();

    }
}
