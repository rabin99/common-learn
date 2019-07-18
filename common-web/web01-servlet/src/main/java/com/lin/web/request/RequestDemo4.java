package com.lin.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @auther : lin
 * @date : 2019/6/6 18:01
 * @desc : 设置response编码
 */
@WebServlet(urlPatterns = "/demo4")
public class RequestDemo4 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取从demo3中serAttribute数据
        Object msg = request.getAttribute("msg");
        System.out.println(msg);
        System.out.println("demo4444被访问……");

        //获取流对象之前，设置流的默认编码：ISO-8859-1 设置为：GBK
//        response.setCharacterEncoding("utf-8");
        //告诉浏览器，服务器发送的消息体数据的编码。建议浏览器使用该编码解码
//        response.setHeader("content-type","text/html;charset=utf-8");
//
        // 直接用一个方法，代替上面2个方法的设置，效果相同
        response.setContentType("text/html;charset=utf-8");

        PrintWriter pw = response.getWriter();
        pw.write("你好啊啊啊 response");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
