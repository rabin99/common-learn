package spring04_07anno_tx_withoutxml.dao;

import spring04_07anno_tx_withoutxml.domain.Account;

/**
 * @auther : lin
 * @date : 2019/6/3 14:27
 * @desc :
 */
public interface IAccountDao {
    /**
     * 根据Id查询账户
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 根据名称查询账户
     * @param accountName
     * @return
     */
    Account findAccountByName(String accountName);

    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);
}
