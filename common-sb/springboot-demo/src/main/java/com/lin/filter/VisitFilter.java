package com.lin.filter;

import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @auther : lin
 * @date : 2019/7/3 16:57
 * @desc :还需要配置@ServletComponentScan({"com.lin.filter"})
 */
@WebFilter(urlPatterns="/*",filterName = "visitFilter")
public class VisitFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter~~~");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
