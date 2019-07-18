package spring01_04bean.factory;

import spring01_04bean.service.IAccountService;
import spring01_04bean.service.impl.AccountServiceImpl;

/**
 * @auther : lin
 * @date : 2019/5/14 15:52
 * @desc : 模拟一个工厂类（该类可能是存在于jar包中的，我们无法通过修改源码的方式来提供默认构造函数）
 */
public class InstanceFactory {
    public IAccountService getAccountService(){
        return new AccountServiceImpl();
    }
}
