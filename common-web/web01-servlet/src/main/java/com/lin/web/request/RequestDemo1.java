package com.lin.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @auther : lin
 * @date : 2019/6/5 11:36
 * @desc :
 */
@WebServlet("/requestDemo1")
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取请求方式 ：GET
        String method = request.getMethod();
        System.out.println(method);
        //2.(*)获取虚拟目录：/day14
        String contextPath = request.getContextPath();
        System.out.println(contextPath);
        //3. 获取Servlet路径: /demo1
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        //4. 获取get方式请求参数：name=zhangsan
        String queryString = request.getQueryString();
        System.out.println(queryString);
        //5.(*)获取请求URI：/day14/demo1
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURI);
        System.out.println(requestURL);
        //6. 获取协议及版本：HTTP/1.1
        String protocol = request.getProtocol();
        System.out.println(protocol);
        //7. 获取客户机的IP地址：
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);


        // 遍历所有头
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+"---"+value);
        }
        // 获取user-agent判断浏览器类型
        String agent = request.getHeader("user-agent");
        if(agent.contains("Chrome")){
            System.out.println("google");
        }else  if(agent.contains("Firefox")){
            System.out.println("ff");
        }

        String referer = request.getHeader("referer");
        System.out.println(referer);

        // 防盗链 ,测试可以新建一个html页面使用a标签，但是必须要部署到tomcat才有转发效果，注意不为null判断
        if(request!=null){
            if (referer.contains("/web-servlet")){
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("播放电影……");
            }else {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("不能播放……");
            }
        }

        // 服务器session，每个连接都是一个新的session
        HttpSession session = request.getSession();
        System.out.println(session);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 注意用super可能会报错：HTTP method POST is not supported by this URL 改为thid.doPost即可
        super.doPost(req, resp);
    }
}
