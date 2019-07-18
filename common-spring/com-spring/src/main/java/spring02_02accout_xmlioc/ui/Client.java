package spring02_02accout_xmlioc.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring02_02accout_xmlioc.domain.Account;
import spring02_02accout_xmlioc.service.IAccountService;


import java.util.Arrays;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/22 16:05
 * @desc :
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean02_02.xml");
        IAccountService accountService = applicationContext.getBean("accountService", IAccountService.class);
        List<Account> allAccount = accountService.findAllAccount();
        System.out.println(Arrays.toString(allAccount.toArray()));
    }
}
