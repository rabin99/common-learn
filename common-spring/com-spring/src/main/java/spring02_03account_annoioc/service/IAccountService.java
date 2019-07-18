package spring02_03account_annoioc.service;

import spring02_03account_annoioc.domain.Account;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/22 17:54
 * @desc :
 * 账户的业务层接口
 */
public interface IAccountService {

    /**
     * 查询所有
     *
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个
     *
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存
     *
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新
     *
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     *
     * @param acccountId
     */
    void deleteAccount(Integer acccountId);


}