package spring02_02accout_xmlioc.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import spring02_02accout_xmlioc.dao.IAccountDao;
import spring02_02accout_xmlioc.domain.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/22 15:47
 * @desc :
 */
public class AccountDaoImpl implements IAccountDao {

    private QueryRunner queryRunner;

    public void setQueryRunner(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public List<Account> findAllAccount() {
        try {
            List<Account> query = queryRunner.query("select * from account", new BeanListHandler<Account>(Account.class));
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Account findAccountById(Integer accountId) {
        return null;
    }

    @Override
    public void saveAccount(Account account) {

    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void deleteAccount(Integer acccountId) {

    }
}
