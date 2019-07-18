package com.lin.web.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * @auther : lin
 * @date : 2019/6/6 9:18
 * @desc : 使用xml配置servlet
 */
public class RequestDemo2 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取post请求的请求体 ,只能获取一次，再次调用获取Parameter或者header会报NEP异常
  /*      BufferedReader reader = request.getReader();
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }*/

        request.setCharacterEncoding("utf-8");
        // 根据参数名称获取参数值
        System.out.println("使用URLDecoder对url编码数据进行解码");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        String decode = URLDecoder.decode(username, "iso-8859-1");
        System.out.println("用户名：" + decode);
        String[] hobbies = request.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println("爱好:" + hobby);
        }

        // 根据参数名称获取参数值的数组
        System.out.println("使用request.getParameterNames");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            System.out.println(key + "---" + value);
        }

        System.out.println("使用request.getParameterMap");
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((key, value) -> {
            System.out.println(key + "..." + Arrays.toString(value));
        });


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String test = request.getParameter("test");
        System.out.println(test);
    }
}
