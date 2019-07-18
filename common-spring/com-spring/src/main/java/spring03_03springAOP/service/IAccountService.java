package spring03_03springAOP.service;

/**
 * @auther : lin
 * @date : 2019/5/21 9:55
 * @desc :
 */
public interface IAccountService {
    /**
     * 模拟保存账户
     */
    void saveAccount();

    /**
     * 模拟更新账户
     * @param i
     */
    void updateAccount(int i);

    /**
     * 删除账户
     * @return
     */
    int  deleteAccount();

    void sonExposeProxy();

    void fatherExposeProxy();
}
