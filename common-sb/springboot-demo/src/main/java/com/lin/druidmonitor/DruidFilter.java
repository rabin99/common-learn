package com.lin.druidmonitor;

import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther : lin
 * @date : 2019/7/8 11:21
 * @desc :
 */
@Configuration
public class DruidFilter {
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean beanFilter = new FilterRegistrationBean();
        beanFilter.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        beanFilter.setInitParameters(initParams);
        beanFilter.setUrlPatterns(Arrays.asList("/*"));
        return beanFilter;
    }
}
