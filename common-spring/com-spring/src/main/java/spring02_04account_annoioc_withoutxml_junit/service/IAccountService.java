package spring02_04account_annoioc_withoutxml_junit.service;

import spring02_04account_annoioc_withoutxml_junit.domain.Account;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/22 18:23
 * @desc :
 */
public interface IAccountService {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param acccountId
     */
    void deleteAccount(Integer acccountId);

}
