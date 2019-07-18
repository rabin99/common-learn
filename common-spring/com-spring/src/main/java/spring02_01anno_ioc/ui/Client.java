package spring02_01anno_ioc.ui;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring02_01anno_ioc.dao.IAccountDao;
import spring02_01anno_ioc.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/22 15:21
 * @desc :
 */
@ComponentScan("spring02_01anno_ioc")
@Configuration
public class Client {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean02_01.xml");
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Client.class);
        IAccountService accountService = ac.getBean("accountService", IAccountService.class);
        IAccountService accountService2 = ac.getBean("accountService", IAccountService.class);
        accountService.saveAccount();
        ac.close();
    }
}
