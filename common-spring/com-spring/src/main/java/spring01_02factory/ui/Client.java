package spring01_02factory.ui;

import spring01_02factory.factory.BeanFactory;
import spring01_02factory.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/20 15:44
 * @desc : 模拟一个保存服务
 */
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
            System.out.println(as);
            as.saveAccount();
        }
    }
}
