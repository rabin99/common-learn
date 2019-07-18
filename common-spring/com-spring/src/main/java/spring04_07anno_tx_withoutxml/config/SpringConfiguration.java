package spring04_07anno_tx_withoutxml.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @auther : lin
 * @date : 2019/6/3 16:14
 * @desc :
 */
@Configuration
@ComponentScan("spring04_07anno_tx_withoutxml")
@Import({JdbcConfig.class,TransactionConfig.class})
@PropertySource("db.properties")
@EnableTransactionManagement
public class SpringConfiguration {
}
