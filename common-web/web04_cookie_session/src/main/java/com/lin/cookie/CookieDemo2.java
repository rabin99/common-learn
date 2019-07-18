package com.lin.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @auther : lin
 * @date : 2019/6/11 17:40
 * @desc : 遍历所有cookie，同时测试，拿到cookie，和设置value，再获取cookie的value，是之前的值，还是刚set的值。
 */
@WebServlet("/cookieDemo2")
public class CookieDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            //  遍历所有cookie
            for (Cookie cookie : cookies) {
                if ("msg".equals(cookie.getName())) {
                    // 先获取一遍demo3中设置的值
//                    System.out.println("刚拿到cookie的值：" + cookie.getValue());

                    // 对msg这个的值，重新设置一个值，
                    LocalDateTime localDateTime = LocalDateTime.now();
                    String encode = URLEncoder.encode(localDateTime.toString(),"utf-8");
                    cookie.setValue(encode);
                    // 发送cookie到客户端
                    response.addCookie(cookie);


                    // 这里再 次获取cookie的value ，问题：：：：到底是新的值，还是之前的值
                    String value = cookie.getValue();
                    System.out.println("重新setValue后的Cookie.getValue返回值：" + value);

                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
