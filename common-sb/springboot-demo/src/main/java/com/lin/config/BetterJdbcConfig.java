package com.lin.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @auther : lin
 * @date : 2019/7/2 15:25
 * @desc : 这个配置其实是多余的，因为springboot默认用户引入了 HikariCP 或者druid，
 * 就默认会为datasource进行初始化，直接使用该对象即可 ，也就是说该类直接删除也没任何影响。
 *
 */
//@Configuration
public class BetterJdbcConfig{
//    @Bean("hikariDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.hikari")
//    public DataSource getDataSource() {
//       return new HikariDataSource();
//    }

  /*  @Bean("datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource(){
        return new DruidDataSource();
    }*/
    public static void main(String[] args) {
        System.out.println();
    }
}
