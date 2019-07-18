package spring04_01jdbctemplate.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

/**
 * @auther : lin
 * @date : 2019/5/23 14:40
 * @desc : 此类用于抽取dao中的重复代码 ,使用该对象作为父类，那么子类直接在spring的xml文件中配置一个datasource就行，而不需要写jdbcTemplate
 */
public class JdbcDaoSupport {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 提供了dataSource代码会调用方法创建JdbcTemplate
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource){
        if(jdbcTemplate == null){
            jdbcTemplate = createJdbcTemplate(dataSource);
        }
    }
    public JdbcTemplate createJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
