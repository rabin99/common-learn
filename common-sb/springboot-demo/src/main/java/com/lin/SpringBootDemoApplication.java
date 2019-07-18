package com.lin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.sql.DataSource;

/**
 * @auther : lin
 * @date : 2019/7/1 17:42
 * @desc : 自己加载配置文件，通过SpringBootapplication注解内部EnableAutoConfiguration来实现，该注解通过用户引入的依赖来猜测用户需要用到该服务，那么进行自动配置
 * 该类所在的包下定义了一系列的自动配置类，如WebMvcAutoConfigration中有ViewResover视图解析器，还有RequestMappingHandlerAdapter处理器适配器
 *
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan({"com.lin.filter"})
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
