package com.lin.druidmonitor;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther : lin
 * @date : 2019/7/8 10:29
 * @desc : 配置Druid数据库监控
 * @see `https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatViewServlet%E9%85%8D%E7%BD%AE`
 */
@Configuration
public class DruidServlet {
    @Bean
    public ServletRegistrationBean<StatViewServlet>  getServletRegistrationBean(){
        ServletRegistrationBean<StatViewServlet> beanServlet = new ServletRegistrationBean<>();
        beanServlet.setServlet(new StatViewServlet());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","admin");
        initParams.put("allow","");    /**默认就是允许所有访问*/
        beanServlet.setInitParameters(initParams);
        beanServlet.setName("DruidStatView");
        beanServlet.addUrlMappings("/druid/*");
        return beanServlet;
    }
}
