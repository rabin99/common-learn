package spring04_01jdbctemplate.dao.impl;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import spring04_01jdbctemplate.dao.IAccountDao;
import spring04_01jdbctemplate.domain.Account;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/23 14:49
 * @desc : 集成了JdbcDaoSupport后直接调用getJdbcTemplate方法即可获取到JdbcTemplate对象，同时spring配置文件中也少配置了一个bean
 */
public class AccountDaoImplWithSupport extends JdbcDaoSupport implements IAccountDao {
    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where id = ?", new BeanPropertyRowMapper<Account>(Account.class), accountId);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public Account findAccountByName(String accountName) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where name = ?", new BeanPropertyRowMapper<Account>(Account.class), accountName);
        if (accounts.isEmpty()) {
            return null;
        }
        if (accounts.size() > 1) {
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        super.getJdbcTemplate().update("update account set name=?,money=? where id=?", account.getName(), account.getMoney(), account.getId());
    }
}
