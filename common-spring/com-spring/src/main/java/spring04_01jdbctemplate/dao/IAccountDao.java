package spring04_01jdbctemplate.dao;

import spring04_01jdbctemplate.domain.Account;

/**
 * @auther : lin
 * @date : 2019/5/23 10:40
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
