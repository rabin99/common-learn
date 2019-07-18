package com.lin.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @auther : lin
 * @date : 2019/6/11 17:49
 * @desc : 在服务器中的Servlet判断是否有一个名为lastTime的cookie
 * 1. 有：不是第一次访问
 * 1. 响应数据：欢迎回来，您上次访问时间为:2018年6月10日11:50:20
 * 2. 写回Cookie：lastTime=2018年6月10日11:50:01
 * 2. 没有：是第一次访问
 * 1. 响应数据：您好，欢迎您首次访问
 * 2. 写回Cookie：lastTime=2018年6月10日11:50:01
 */
@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    private final static String LAST_TIME = "lastTime";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置相应的消息体的数据格式以及编码
        response.setContentType("text/html;charset=utf-8");

        // 1. 获取所有cookie
        Cookie[] cookies = request.getCookies();
        // 没有cookie为lastTime
        boolean flag = false;
        // 格式化时间使用
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

        // 2. 遍历cookie数组
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                // 判断是否有lasttime这个key的cookie
                if (LAST_TIME.equals(name)) {
                    // 存在lasttime的cookie
                    flag = true;

                    // 提前获取时间，以免被后面设置给覆盖了
                    String value = cookie.getValue();

                    // jdk8提供的java.time包
                    LocalDateTime now = LocalDateTime.now();
                    String beforeEncoderDate = dateTimeFormatter.format(now);
                    // 因为有空格等符号，所以将时间编码后存入到cookie
                    System.out.println("编码前：" + beforeEncoderDate);
                    String afterEncoderDate = URLEncoder.encode(beforeEncoderDate, "utf-8");
                    System.out.println("编码后：" + afterEncoderDate);
                    cookie.setValue(afterEncoderDate);
                    // 一个月后过期
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(cookie);

                    // 响应数据
                    // 获取cookie的value时间

                    System.out.println("解码前：" + value);
                    value = URLDecoder.decode(value, "utf-8");
                    System.out.println("解码后：" + value);
                    response.getWriter().write("<h1>欢迎回来，您上次访问时间为:" + value + ",当前时间:"+new Date()+"</h1>");
                    break;
                }
            }
        }
        // 如果是第一次访问
        if (cookies == null || cookies.length == 0 || flag == false) {
            LocalDateTime firstVisitDate = LocalDateTime.now();
            String firstVisitDateFormat = dateTimeFormatter.format(firstVisitDate);
            // 编码
            String encode = URLEncoder.encode(firstVisitDateFormat, "utf-8");
            Cookie cookie = new Cookie("lastTime", encode);

            cookie.setMaxAge(60 * 60 * 24 * 30);//一个月
            response.addCookie(cookie);

            response.getWriter().write("<h1>您好，欢迎您首次访问</h1>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}