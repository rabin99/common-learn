package spring03_05annotationAOP;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @auther : lin
 * @date : 2019/5/21 20:30
 * @desc :
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("spring03_05annotationAOP")
public class Config {
}
