package spring03_03springAOP.service.impl;

import org.springframework.aop.framework.AopContext;
import spring03_03springAOP.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/21 9:58
 * @desc :
 */
public class AccountServiceImpl implements IAccountService {
    @Override
    public void saveAccount() {
        System.out.println("执行了保存");
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("执行了更新" + i);

    }

    @Override
    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }

    public void sonExposeProxy() {
        System.out.println("内部的expose proxy");
    }

    /**
     * 代理的传递
     *  外层方法调用内层放，如何让内层son的代理也生效？（一般情况下，虽然你配置了切入点，但是调用的时候，并不是直接调用，而是通过方法去调用方法，那么默认切面会失效）
     */
    public void fatherExposeProxy() {
        System.out.println("外层expose proxy");
        /**
         * 这里为了让father在调用son的时候，son的切面依然生效，必须使用AopContext来获取代理对象，同时在aop中设置expose-proxy=true
         * 或者spring推荐不要出现自调用
         */
        IAccountService accountService = (IAccountService) AopContext.currentProxy();
        accountService.sonExposeProxy();
    }
}
