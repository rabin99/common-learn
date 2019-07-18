package spring04_03account_aoptx_anno.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.management.Query;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @auther : lin
 * @date : 2019/5/31 18:15
 * @desc :
 */
// @Configuration 可有可无
@ComponentScan("spring04_03account_aoptx_anno")
@PropertySource("classpath:db.properties")
@EnableAspectJAutoProxy
// 开启切面，这几个配置其实都可以单独写一个类，Config仅仅作为数据库的类，然后使用import注解，导入Config.class,
// 配置文件中使用<aop:aspectj-autoproxy></aop:aspectj-autoproxy>开启切面
public class Config {
    @Value("${jdbc.driverClass}")
    private String driver;

    @Value("${jdbc.jdbcUrl}")
    private String url;

    @Value("${jdbc.user}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        // 这里方法调错了，，，，setUser写成setJdbc导致报错查了很久，其实报错没有合适的驱动就应该从这里排查
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    // 其他数据源
    @Bean(name = "dataSource2")
    public DataSource createDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        return dataSource;
    }

    ////如果有2个datasource，参数上可以加上qulifier
    @Bean
    public QueryRunner getQueryRunner(@Qualifier("dataSource") DataSource dataSource) {
        return new QueryRunner(dataSource);
    }
}
