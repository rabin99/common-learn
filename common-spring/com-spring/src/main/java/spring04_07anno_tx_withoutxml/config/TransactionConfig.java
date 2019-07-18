package spring04_07anno_tx_withoutxml.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @auther : lin
 * @date : 2019/6/3 16:14
 * @desc :和事务相关的配置类
 */
public class TransactionConfig {
    /**
     * 用于创建事务管理器对象
     */
    @Bean(name = "transactionManager0407")
    public PlatformTransactionManager createTransactionManager(@Qualifier("dataSource0407") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
