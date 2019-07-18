package spring01_04bean.service.impl;

import spring01_04bean.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/14 15:54
 * @desc :
 */
public class AccountServiceImpl implements IAccountService {
    public AccountServiceImpl() {
        System.out.println("对象创建了");
    }

    public void  saveAccount(){
        System.out.println("service中的saveAccount方法执行了。。。");
    }

    public void  init(){
        System.out.println("对象初始化了。。。");
    }
    public void  destroy(){
        System.out.println("对象销毁了。。。");
    }
}
