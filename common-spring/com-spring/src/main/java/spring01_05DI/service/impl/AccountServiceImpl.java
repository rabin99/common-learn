package spring01_05DI.service.impl;

import spring01_05DI.service.IAccountService;

import java.util.Date;

/**
 * @auther : lin
 * @date : 2019/5/14 17:05
 * @desc :
 */
public class AccountServiceImpl implements IAccountService {
    //如果是经常变化的数据，并不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name,Integer age,Date birthday){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void  saveAccount(){
        System.out.println("service中的saveAccount方法执行了。。。"+name+","+age+","+birthday);
    }
}
