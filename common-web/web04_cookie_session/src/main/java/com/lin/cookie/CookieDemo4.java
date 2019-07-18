package com.lin.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther : lin
 * @date : 2019/6/11 17:45
 * @desc :
 */
@WebServlet("/cookieDemo4")
public class CookieDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1. 创建Cookie对象
        Cookie cookie = new Cookie("msg", "setMaxAge");
//        2. 设置cookie存活时间，秒为单位，-1表示永不过期，0表示立即删除，过期后会自动删除Cookie文件
        cookie.setMaxAge(30);
//        cookie.setMaxAge(-1);  // 永不过期
//        cookie.setMaxAge(300);
//        cookie.setMaxAge(0);  //立即删除
//        设置path，让当前服务器下部署的所有项目共享cookie信息
        cookie.setPath("/");
        String path = cookie.getPath();
        System.out.println("cookie路径："+path);
        response.addCookie(cookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
