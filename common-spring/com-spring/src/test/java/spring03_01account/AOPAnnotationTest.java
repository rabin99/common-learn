package spring03_01account;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring03_05annotationAOP.Config;
import spring03_05annotationAOP.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/21 14:26
 * @desc :
 */
public class AOPAnnotationTest {
    public static void main(String[] args) {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean03_05annotationAOP.xml");
        ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        IAccountService as  = (IAccountService) ac.getBean("accountService");
        as.saveAccount();

    }
}
