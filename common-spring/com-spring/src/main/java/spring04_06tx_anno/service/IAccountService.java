package spring04_06tx_anno.service;

import spring04_06tx_anno.domain.Account;

/**
 * @auther : lin
 * @date : 2019/6/3 14:28
 * @desc :
 */
public interface IAccountService {
    /**
     * 根据id查询账户信息
     *
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 转账
     *
     * @param sourceName 转成账户名称
     * @param targetName 转入账户名称
     * @param money      转账金额
     */
    void transfer(String sourceName, String targetName, Float money);

    void rightTransfer();

    void errorTransfer();

    /**
     * 测试没有注解的方法（注意把全局类注解被注释）
     */
    void testNoTransferAnnotation();

    void propagationREQUIRED();

    void propagationNOT_SUPPORTED();

    void propagationREQUIRESNEW();

    void propagationMANDATORY();

    void propagationSUPPORTS();

    void propagationNEVER();

    void propagationNESTED();

}
