package spring03_05annotationAOP.service.impl;

import org.springframework.stereotype.Service;
import spring03_05annotationAOP.service.IAccountService;

/**
 * @auther : lin
 * @date : 2019/5/21 9:58
 * @desc :
 */
@Service("accountService")
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
