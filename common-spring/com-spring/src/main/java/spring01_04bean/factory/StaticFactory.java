package spring01_04bean.factory;

import spring01_04bean.service.IAccountService;
import spring01_04bean.service.impl.AccountServiceImpl;

/**
 * @auther : lin
 * @date : 2019/5/14 15:52
 * @desc :
 */
public class StaticFactory {
    public static IAccountService getAccountService(){
        return new AccountServiceImpl();
    }
}
