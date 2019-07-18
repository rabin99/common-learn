package spring04_07anno_tx_withoutxml.service;

import spring04_07anno_tx_withoutxml.domain.Account;

/**
 * @auther : lin
 * @date : 2019/6/3 14:28
 * @desc :
 */
public interface IAccountService {
    /**
     * 根据id查询账户信息
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 转账
     * @param sourceName    转成账户名称
     * @param targetName    转入账户名称
     * @param money         转账金额
     */
    void transfer(String sourceName, String targetName, Float money);
}
