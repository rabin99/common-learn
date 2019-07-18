package spring03_04adviceType.service.impl;

import spring03_04adviceType.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/21 9:58
 * @desc :
 */
public class AccountServiceImpl  implements IAccountService {
    @Override
    public void saveAccount() {
        System.out.println("执行了保存");
        int i=1/0;
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("执行了更新"+i);

    }

    @Override
    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
