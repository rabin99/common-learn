package spring04_01jdbctemplate.jdbctemplate;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import spring04_01jdbctemplate.domain.Account;

import java.util.Arrays;
import java.util.List;

/**
 * @auther : lin
 * @date : 2019/5/23 12:06
 * @desc :
 */
public class JdbcTemplateDemo {
    public static void main(String[] args) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://flu03:3306/test");
        ds.setUsername("root");
        ds.setPassword("root");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        List<Integer> query = jdbcTemplate.query("select id from account", new SingleColumnRowMapper<>(Integer.class));
        System.out.println(Arrays.toString(query.toArray()));

        List<Account> query1 = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<>(Account.class));
        query1.stream().forEach(System.out::println);
    }
}
