package com.lin.web.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther : lin
 * @date : 2019/6/6 16:24
 * @desc : 转发是从request出发，重定向是response出发
 */
@WebServlet(urlPatterns = "/demo3")
public class RequestDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo333 被访问了……");
        /*
        	* 重定向的特点:redirect
        	*    response.sendRedirect("/day15/responseDemo2");
				1. 地址栏发生变化
				2. 重定向可以访问其他站点(服务器)的资源
				3. 重定向是两次请求。不能使用request对象来共享数据
			* 转发的特点：forward
				1. 转发地址栏路径不变
				2. 转发只能访问当前服务器下的资源
				3. 转发是一次请求，可以使用request对象来共享数据
         */


        // 转发到demo4资源
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/demo4");
//        requestDispatcher.forward(request,response);

        // 存储数据到request域中
        request.setAttribute("msg","hello");
        request.getRequestDispatcher("/demo4").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
