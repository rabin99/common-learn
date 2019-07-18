package com.lin.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther : lin
 * @date : 2019/6/10 17:32
 * @desc :
 */
@WebServlet("/servletContextDemo1")
public class ServletContextDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /*
            ServletContext对象获取：
                1. 通过request对象获取
			        request.getServletContext();
                2. 通过HttpServlet获取
                    this.getServletContext();
         */
        // 1. 通过request对象获取
        ServletContext context1 = req.getServletContext();
        // 2. 通过HttpServlet获取
        ServletContext context2 = this.getServletContext();

        System.out.println(context1);
        System.out.println(context2);

        System.out.println(context1 == context2);// true，两个是同一个对象

         /*
            ServletContext的三个重要功能：
               1. 获取MIME类型：
                * MIME类型:在互联网通信过程中定义的一种文件数据类型
                    * 格式： 大类型/小类型   text/html		image/jpeg

                * 获取：String getMimeType(String file)

                2. 域对象：共享数据

                3. 获取文件的真实(服务器)路径
         */
        // 2. 通过HttpServlet获取
        ServletContext context = this.getServletContext();
        System.out.println("项目路径：" + context.getContextPath()); // /web03_response
        // 3. 定义文件名
        String filename = "a.jpg";// image/jpeg
        // 4. 获取MIME类型
        String mimeType = context.getMimeType(filename);
        System.out.println(mimeType);

        context.setAttribute("msg","hello world");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
