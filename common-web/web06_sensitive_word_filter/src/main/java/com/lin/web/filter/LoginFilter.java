package com.lin.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @auther : lin
 * @date : 2019/6/19 14:20
 * @desc : 登录验证
 */
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("登录拦截！");
        if (req != null)
            System.out.println(req);
        HttpServletRequest request = (HttpServletRequest) req;
        // 获取资源路径
        String uri = request.getRequestURI();
        System.out.println(uri);
        // 直接放行的资源
        if (uri.contains("/login.jsp") || uri.contains("/loginServlet")
                || uri.contains("/css/") || uri.contains("/js/")
                || uri.contains("fonts") || uri.contains("/checkCodeServlet")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            if (user != null) {
                // 已经登录过，放行
                chain.doFilter(req, resp);
            } else {
                // 没有登录，跳转登录页
                request.setAttribute("login_msg", "你尚未登录， 请登录");
                request.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
    public void destroy() {
    }
}
