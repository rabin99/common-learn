package com.lin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther : lin
 * @date : 2019/6/20 14:09
 * @desc :
 */
@WebServlet("/user/findAllServlet")
public class ServletDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("findAllServlet...");
        // 跳转servlet，用于测试filter的转发规则
        System.out.println(req.getContextPath() + "/user/updateServlet");
        req.getRequestDispatcher("/user/updateServlet").forward(req, resp);
    }
}
