package spring01_04bean.ui;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import spring01_04bean.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auther : lin
 * @date : 2019/5/14 15:52
 * @desc :
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean01_04.xml");
//        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("E:\\bilin-learn\\common-spring\\src\\main\\resources\\bean01_04.xml");
        IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
        accountService.saveAccount();
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}
