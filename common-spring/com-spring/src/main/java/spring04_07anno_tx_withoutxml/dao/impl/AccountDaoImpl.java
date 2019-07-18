package spring04_07anno_tx_withoutxml.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spring04_07anno_tx_withoutxml.dao.IAccountDao;
import spring04_07anno_tx_withoutxml.domain.Account;

import java.util.List;

/**
 * @auther : lin
 * @date : 2019/6/3 14:36
 * @desc : 这里注意就不要用import org.springframework.jdbc.core.support.JdbcDaoSupport
 */
@Repository("accountDao0407")
public class AccountDaoImpl  implements IAccountDao {

    @Autowired
    @Qualifier("jdbcTemplate0407")
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> accounts = jdbcTemplate.query("select * from account where id = ?",new BeanPropertyRowMapper<Account>(Account.class),accountId);
        return accounts.isEmpty()?null:accounts.get(0);
    }

    @Override
    public Account findAccountByName(String accountName) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name = ?",new BeanPropertyRowMapper<Account>(Account.class),accountName);
        if(accounts.isEmpty()){
            return null;
        }
        if(accounts.size()>1){
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
    }
}
