package spring04_01jdbctemplate.jdbctemplate;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlFunction;
import spring04_01jdbctemplate.domain.Account;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/23 10:53
 * @desc :
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean04_01jdbctemplate.xml");
        JdbcTemplate jt = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        //3.执行操作
        //保存
//        jt.update("insert into account(name,money)values(?,?)","eee",3333f);
        //更新
//        jt.update("update account set name=?,money=? where id=?","test",4567,7);
        //删除
//        jt.update("delete from account where id=?",8);
        //查询所有
//        List<Account> accounts = jt.query("select * from account where money > ?",new AccountRowMapper(),1000f);
//        List<Account> accounts = jt.query("select * from account where money > ?",new BeanPropertyRowMapper<Account>(Account.class),1000f);
//        for(Account account : accounts){
//            System.out.println(account);
//        }
        //查询一个
//        List<Account> accounts = jt.query("select * from account where id = ?",new BeanPropertyRowMapper<Account>(Account.class),1);
//        System.out.println(accounts.isEmpty()?"没有内容":accounts.get(0));

        //查询返回一行一列（使用聚合函数，但不加group by子句）

        List<Account> query = jt.query("select * from account", new AccountRowMapper());
        System.out.println(query);

    }

    static class AccountRowMapper implements RowMapper<Account> {
        /**
         * 一般不要遍历rs，而只是处理当前一行,返回的结果还是一个list，spring会自动加上所有rs的结果到list
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setName(rs.getString("name"));
            account.setMoney(rs.getFloat("money"));
            return account;
        }
    }

    /**
     * 这种遍历rs封装返回的结果是2层list，反而错误！！
     */
    static class ListRowMapper implements RowMapper<List<Account>>{
        @Override
        public List<Account> mapRow(ResultSet rs, int rowNum) throws SQLException {
            System.out.println("current row : "+ rowNum);
            List<Account> list = new ArrayList<>(rowNum);
            while (rs.next()){
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setName(rs.getString("name"));
                account.setMoney(rs.getFloat("money"));
                list.add(account);
            }
            return list;
        }
    }
}
