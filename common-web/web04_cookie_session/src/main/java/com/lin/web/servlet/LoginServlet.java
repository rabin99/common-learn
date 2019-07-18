package com.lin.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @auther : lin
 * @date : 2019/6/11 18:09
 * @desc :  验证码验证demo，用户登录就简单的模拟
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置request编码    resp.setContentType("text/html;charset=utf-8");这个是设置输出到页面的内容为utf-8
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 获取验证码
        String checkCode = req.getParameter("checkCode");

        HttpSession session = req.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        // 用完一次必须要先删除，这样刷新验证码才能确保之前验证码失效（验证码只能单词，短信验证码才是几分钟的实效）
        session.removeAttribute("checkCode_session");

        if (checkCode_session != null && checkCode_session.equalsIgnoreCase(checkCode)) {
            // 这里用户名登录就简单模拟下
            if ("zhangsan".equals(username) && "123".equals(password)) {
                session.setAttribute("user", username);
                // 登录成功，跳转到成功页面
                resp.sendRedirect(req.getContextPath() + "/success.jsp");
            } else {
                // 存储信息到request，给jsp页面调用获取失败原因
                req.setAttribute("login_error", "用户名或密码错误");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } else {
            // 验证码不一致
            req.setAttribute("cc_error","验证码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
